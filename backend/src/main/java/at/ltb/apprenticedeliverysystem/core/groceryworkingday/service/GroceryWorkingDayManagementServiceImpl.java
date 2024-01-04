package at.ltb.apprenticedeliverysystem.core.groceryworkingday.service;

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
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroceryWorkingDayManagementServiceImpl implements GroceryWorkingDayManagementService {

    private final Logger logger = LoggerFactory.getLogger(GroceryWorkingDayManagementServiceImpl.class);

    private final GroceryWorkingDayQueryDSLRepository groceryWorkingDayQueryDSLRepository;

    private final GroceryWorkingDayCrudRepository groceryWorkingDayCrudRepository;

    private final GroceryWorkingDayMapper groceryWorkingDayMapper;

    private final UserQueryDSLRepository userQueryDSLRepository;

    public GroceryWorkingDayManagementServiceImpl(GroceryWorkingDayQueryDSLRepository groceryWorkingDayQueryDSLRepository,
                                                  GroceryWorkingDayCrudRepository groceryWorkingDayCrudRepository,
                                                  GroceryWorkingDayMapper groceryWorkingDayMapper, UserQueryDSLRepository userQueryDSLRepository) {
        this.groceryWorkingDayQueryDSLRepository = groceryWorkingDayQueryDSLRepository;
        this.groceryWorkingDayCrudRepository = groceryWorkingDayCrudRepository;
        this.groceryWorkingDayMapper = groceryWorkingDayMapper;
        this.userQueryDSLRepository = userQueryDSLRepository;
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
    public GroceryWorkingDayDetailDTO loadGroceryWorkingDayToday() {
        GroceryWorkingDayEntity groceryWorkingDay = groceryWorkingDayQueryDSLRepository.loadGroceryWorkingDayToday();
        return groceryWorkingDayMapper.mapGroceryWorkingDayEntityToDetail(groceryWorkingDay);
    }

    @Override
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
}
