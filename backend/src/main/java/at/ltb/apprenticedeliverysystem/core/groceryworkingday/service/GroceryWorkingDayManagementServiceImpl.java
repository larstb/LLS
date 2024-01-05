package at.ltb.apprenticedeliverysystem.core.groceryworkingday.service;

import at.ltb.apprenticedeliverysystem.core._common.auth.AuthUserHelper;
import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core._common.pagination.PaginationUtil;
import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayCrudRepository;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayEntity;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.api.GroceryWorkingDayManagementService;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.CreateGroceryWorkingDayDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.UpdateGroceryWorkingDayDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.exception.GroceryWorkingDayDateNotUniqueException;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.exception.GroceryWorkingUpdateException;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.exception.GroceryWorkingUserException;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.mapper.GroceryWorkingDayMapper;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserQueryDSLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GroceryWorkingDayManagementServiceImpl implements GroceryWorkingDayManagementService {

    private final Logger logger = LoggerFactory.getLogger(GroceryWorkingDayManagementServiceImpl.class);

    private final GroceryWorkingDayQueryDSLRepository groceryWorkingDayQueryDSLRepository;

    private final GroceryWorkingDayCrudRepository groceryWorkingDayCrudRepository;

    private final GroceryWorkingDayMapper groceryWorkingDayMapper;

    private final UserQueryDSLRepository userQueryDSLRepository;

    private AuthUserHelper authUserHelper;

    public GroceryWorkingDayManagementServiceImpl(GroceryWorkingDayQueryDSLRepository groceryWorkingDayQueryDSLRepository,
                                                  GroceryWorkingDayCrudRepository groceryWorkingDayCrudRepository,
                                                  GroceryWorkingDayMapper groceryWorkingDayMapper,
                                                  UserQueryDSLRepository userQueryDSLRepository,
                                                  AuthUserHelper authUserHelper) {
        this.groceryWorkingDayQueryDSLRepository = groceryWorkingDayQueryDSLRepository;
        this.groceryWorkingDayCrudRepository = groceryWorkingDayCrudRepository;
        this.groceryWorkingDayMapper = groceryWorkingDayMapper;
        this.userQueryDSLRepository = userQueryDSLRepository;
        this.authUserHelper = authUserHelper;
    }

    @Override
    public ResponseWrapper<GroceryWorkingDayDetailDTO> loadAllGroceryWorkingDays(Integer page, Integer pageSize,
                                                                                 Optional<String> goingUserId,
                                                                                 Optional<String> payingUserId,
                                                                                 Optional<LocalDate> date) {
        QueryDslOverviewResponse<GroceryWorkingDayEntity> response = groceryWorkingDayQueryDSLRepository
                .loadGroceryWorkingDays(goingUserId, payingUserId, date, PaginationUtil.getPagination(page, pageSize));
        logger.info("loadAllGroceryWorkingDays: count: " + response.getTotalElements());
        return new ResponseWrapper<>(groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(response.getContent()),
                response.getTotalElements());
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO loadOrCreateGroceryWorkingDayToday() {
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(loadOrCreateEntity());
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO createGroceryWorkingDay(CreateGroceryWorkingDayDTO request) {
        if(Objects.nonNull(groceryWorkingDayQueryDSLRepository.loadGroceryWorkingDayByDate(request.date()))) {
            logger.error("GroceryWorkingDay with duplicated date: " + request.date());
            throw new GroceryWorkingDayDateNotUniqueException("groceryWorkingDay date already found");
        }

        GroceryWorkingDayEntity groceryWorkingDayToSave = groceryWorkingDayMapper.mapCreateGroceryWorkingDayToEntity(request);

        request.goingUserIds().forEach(value -> {
            UserEntity user = userQueryDSLRepository.loadUserByUuid(value);
            if(Objects.isNull(user)) {
                logger.error("GroceryWorkingDay requested going user not found: " + value);
                throw new GroceryWorkingUserException("requested going user not found " + value);
            }
            groceryWorkingDayToSave.getGoingUsers().add(user);
        });

        if(Objects.nonNull(request.payingUserId())) {
            UserEntity user = userQueryDSLRepository.loadUserByUuid(request.payingUserId());
            if(Objects.isNull(user)) {
                logger.error("GroceryWorkingDay requested paying user not found: " + request.payingUserId());
                throw new GroceryWorkingUserException("requested paying user not found " + request.payingUserId());
            }
            groceryWorkingDayToSave.setPayingUser(user);
        }

        logger.info("GroceryWorkingDayEntity:Create Save is called!");
        groceryWorkingDayCrudRepository.save(groceryWorkingDayToSave);
        logger.info("GroceryWorkingDayEntity:Create successfully!");
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(groceryWorkingDayToSave);
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO updateGroceryWorkingDay(UpdateGroceryWorkingDayDTO request) {
        if(Objects.isNull(request.id())) {
            logger.error("GroceryWorkingDayEntity with no id updated");
            throw new GroceryWorkingUpdateException("id is emtpy");
        }

        GroceryWorkingDayEntity groceryWorkingDayToUpdate = groceryWorkingDayQueryDSLRepository.loadGroceryWorkingDayByUuid(request.id());

        if(Objects.isNull(groceryWorkingDayToUpdate)) {
            logger.error("GroceryWorkingDayEntity not found");
            throw new GroceryWorkingUpdateException("groceryWorkingDay not found with id: " + request.id());
        }

        groceryWorkingDayToUpdate = groceryWorkingDayMapper.mapUpdateGroceryWorkingDayToEntity(request, groceryWorkingDayToUpdate);

        if(Objects.nonNull(request.goingUserIds()) && !request.goingUserIds().isEmpty()) {
            for (String value : request.goingUserIds()) {
                UserEntity user = userQueryDSLRepository.loadUserByUuid(value);
                if (Objects.isNull(user)) {
                    logger.error("GroceryWorkingDay requested going user not found: " + value);
                    throw new GroceryWorkingUpdateException("requested going user not found " + value);
                }
                groceryWorkingDayToUpdate.getGoingUsers().add(user);
            }
        }

        if(Objects.nonNull(request.payingUserId())) {
            UserEntity user = userQueryDSLRepository.loadUserByUuid(request.payingUserId());
            if(Objects.isNull(user)) {
                logger.error("GroceryWorkingDay requested paying user not found: " + request.payingUserId());
                throw new GroceryWorkingUpdateException("requested paying user not found " + request.payingUserId());
            }
            groceryWorkingDayToUpdate.setPayingUser(user);
        }

        logger.info("GroceryWorkingDayEntity:Update Update is called!");
        groceryWorkingDayCrudRepository.save(groceryWorkingDayToUpdate);
        logger.info("GroceryWorkingDayEntity:Update successfully!");
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(groceryWorkingDayToUpdate);
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO addLoggedInUserToGoingUsers() {
        UserEntity user = getCurrentLoggedInUser();
        GroceryWorkingDayEntity groceryWorkingDay = loadOrCreateEntity();
        groceryWorkingDay.getGoingUsers().add(user);
        groceryWorkingDayCrudRepository.save(groceryWorkingDay);
        logger.info("added user - going user - " + user.getId() + " to groceryWorkingDay " + groceryWorkingDay.getDate());
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(groceryWorkingDay);
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO removeLoggedInUserToGoingUsers() {
        UserEntity user = getCurrentLoggedInUser();
        GroceryWorkingDayEntity groceryWorkingDay = loadOrCreateEntity();

        boolean goingUserContainsCurrentUser = groceryWorkingDay
                .getGoingUsers()
                .stream()
                .anyMatch(userDay -> userDay.getId().equals(user.getId()));

        if(!goingUserContainsCurrentUser) {
            logger.error("grocery working day doesn't contain current logged in user as going user");
            throw new GroceryWorkingUserException("grocery working day doesn't contain current logged in user as going user");
        }

        List<UserEntity> newGoingUsers = groceryWorkingDay
                .getGoingUsers()
                .stream()
                .filter(workingDay -> !workingDay.getId().equals(user.getId()))
                .collect(Collectors.toList());

        groceryWorkingDay.setGoingUsers(newGoingUsers);

        groceryWorkingDayCrudRepository.save(groceryWorkingDay);
        logger.info("removed user - going user - " + user.getId() + " from groceryWorkingDay " + groceryWorkingDay.getDate());
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(groceryWorkingDay);
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO setCurrentLoggedInUserAsPayingUser() {
        UserEntity user = getCurrentLoggedInUser();
        GroceryWorkingDayEntity groceryWorkingDay = loadOrCreateEntity();

        if(Objects.nonNull(groceryWorkingDay.getPayingUser())) {
            logger.error("grocery working day already contains paying user");
            throw new GroceryWorkingUserException("grocery working day already contains paying user");
        }

        groceryWorkingDay.setPayingUser(user);
        groceryWorkingDayCrudRepository.save(groceryWorkingDay);
        logger.info("added user - paying user - " + user.getId() + " to groceryWorkingDay " + groceryWorkingDay.getDate());
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(groceryWorkingDay);
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO removeCurrentLoggedInUserAsPayingUser() {
        UserEntity user = getCurrentLoggedInUser();
        GroceryWorkingDayEntity groceryWorkingDay = loadOrCreateEntity();

        if(Objects.isNull(groceryWorkingDay.getPayingUser())) {
            logger.error("grocery working day contains no paying user");
            throw new GroceryWorkingUserException("grocery working day contains no paying user");
        }

        groceryWorkingDay.setPayingUser(null);
        groceryWorkingDayCrudRepository.save(groceryWorkingDay);
        logger.info("removed user - paying user - " + user.getId() + " from groceryWorkingDay " + groceryWorkingDay.getDate());
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(groceryWorkingDay);
    }

    private UserEntity getCurrentLoggedInUser() {
        logger.info("Loaded get current logged in user for groceryWorkingDay");
        UserEntity foundedEntity = userQueryDSLRepository.loadUserByKeyCloakReference(authUserHelper.getCurrentUser());

        if(Objects.isNull(foundedEntity)) {
            logger.error("current logged in User not found: " + authUserHelper.getCurrentUser());
            throw new CustomEntityNotFoundException(UserEntity.class.getSimpleName() + " not found");
        }

        return foundedEntity;
    }

    @Transactional
    protected GroceryWorkingDayEntity loadOrCreateEntity() {
        logger.info("GroceryWorkingDayEntity:LoadOrCreate is called!");
        GroceryWorkingDayEntity groceryWorkingDay = groceryWorkingDayQueryDSLRepository.loadGroceryWorkingDayToday();

        if(Objects.isNull(groceryWorkingDay)) {
            logger.info("created new groceryWorkingDay for today");
            groceryWorkingDay = groceryWorkingDayCrudRepository.save(new GroceryWorkingDayEntity());
        }

        return groceryWorkingDay;
    }
}
