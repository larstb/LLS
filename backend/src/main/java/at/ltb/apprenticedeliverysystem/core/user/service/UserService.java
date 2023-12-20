package at.ltb.apprenticedeliverysystem.core.user.service;

import at.ltb.apprenticedeliverysystem.core._common.auth.AuthUserHelper;
import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import at.ltb.apprenticedeliverysystem.core._common.pagination.PaginationUtil;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.keycloak.KeyCloakService;
import at.ltb.apprenticedeliverysystem.core.user.UserMapper;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.user.dto.*;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserEmailNotUniqueException;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserQueryDSLRepository userQueryDSLRepository;

    private final UserCrudRepository userCrudRepository;

    private final UserMapper userMapper;

    private final KeyCloakService keyCloakService;

    private final AuthUserHelper authUserHelper;

    public UserService(UserQueryDSLRepository userQueryDSLRepository, UserCrudRepository userCrudRepository,
                       UserMapper userMapper, KeyCloakService keyCloakService, AuthUserHelper authUserHelper) {
        this.userQueryDSLRepository = userQueryDSLRepository;
        this.userCrudRepository = userCrudRepository;
        this.userMapper = userMapper;
        this.keyCloakService = keyCloakService;
        this.authUserHelper = authUserHelper;
    }

    public ResponseWrapper<UserOverviewPortalDTO> loadAllUsers(Integer page, Integer pageSize,
                                                               Optional<String> searchTerm) {
        QueryDslOverviewResponse<UserEntity> response = userQueryDSLRepository
                .loadUsers(searchTerm, PaginationUtil.getPagination(page, pageSize));
        logger.info("loadAllUsers: count: " + response.getTotalElements());
        return new ResponseWrapper<>(userMapper.mapUserEntityToOverviewPortal(response.getContent()),
                response.getTotalElements());
    }

    public UserDetailPortalDTO loadUserById(String uuid) {
        UserEntity foundedEntity = userQueryDSLRepository.loadUserByUuid(uuid);

        if(Objects.isNull(foundedEntity)) {
            logger.error("UserEntity not found: " + uuid);
            throw new CustomEntityNotFoundException(UserEntity.class.getSimpleName() + " not found");
        }

        logger.info("UserEntity was found!");
        return userMapper.mapUserEntityToDetailPortal(foundedEntity);
    }

    public UserDetailDTO loadUserForLoggedInUser() {
        UserEntity foundedEntity = userQueryDSLRepository.loadUserByKeyCloakReference(authUserHelper.getCurrentUser());

        if(Objects.isNull(foundedEntity)) {
            logger.error("UserEntity for user not found: " + authUserHelper.getCurrentUser());
            throw new CustomEntityNotFoundException(UserEntity.class.getSimpleName() + " not found");
        }

        logger.info("UserEntity for user was found!");
        return userMapper.mapUserEntityToDetail(foundedEntity);
    }

    @Transactional
    public UserDetailPortalDTO createUser(CreateUserPortalDTO request) {
        if(Objects.nonNull(userQueryDSLRepository.loadUserByEmail(request.email()))) {
            logger.error("UserEntity with duplicated email: " + request.email());
            throw new UserEmailNotUniqueException("user email already found");
        }
        UserEntity userToCreate = userMapper.mapCreateUserToEntityPortal(request);
        logger.info("UserEntity:Create KeyCloakService is called!");
        userToCreate.setKeycloakReference(keyCloakService.createKeyCloakUser(request));
        logger.info("UserEntity:Create Save is called!");
        userCrudRepository.save(userToCreate);
        logger.info("UserEntity:Create successfully!");
        return userMapper.mapUserEntityToDetailPortal(userToCreate);
    }

    @Transactional
    public UserDetailPortalDTO updateUserPortal(UpdateUserPortalDTO request) {
        if(Objects.isNull(request.id())) {
            logger.error("UserEntity with no id updated");
            throw new UserUpdateException("id is emtpy");
        }
        UserEntity userToUpdate = userQueryDSLRepository.loadUserByUuid(request.id());
        userToUpdate = userMapper.mapUpdateUserToEntityPortal(request, userToUpdate);
        logger.info("UserEntity:Update KeyCloakService is called!");
        keyCloakService.updateKeyCloakUserPortal(request, userToUpdate);
        logger.info("UserEntity:Update Update is called!");
        userCrudRepository.save(userToUpdate);
        logger.info("UserEntity:Update successfully!");
        return userMapper.mapUserEntityToDetailPortal(userToUpdate);
    }

    @Transactional
    public UserDetailDTO updateUser(UpdateUserDTO request) {
        if(Objects.isNull(request.id())) {
            logger.error("UserEntity with no id updated");
            throw new UserUpdateException("id is emtpy");
        }
        UserEntity userToUpdate = userQueryDSLRepository.loadUserByUuid(request.id());
        if(!userToUpdate.getKeycloakReference().equals(authUserHelper.getCurrentUser())) {
            logger.error("UserEntity is not logged in user");
            throw new UserUpdateException("UserEntity is not logged in user");
        }
        userToUpdate = userMapper.mapUpdateUserToEntity(request, userToUpdate);
        logger.info("UserEntity:Update KeyCloakService is called!");
        keyCloakService.updateKeyCloakUser(request, userToUpdate);
        logger.info("UserEntity:Update Update is called!");
        userCrudRepository.save(userToUpdate);
        logger.info("UserEntity:Update successfully!");
        return userMapper.mapUserEntityToDetail(userToUpdate);
    }

}
