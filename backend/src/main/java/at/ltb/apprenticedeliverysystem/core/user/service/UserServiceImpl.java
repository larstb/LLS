package at.ltb.apprenticedeliverysystem.core.user.service;

import at.ltb.apprenticedeliverysystem.core._common.auth.AuthUserHelper;
import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core._common.role.RoleEnum;
import at.ltb.apprenticedeliverysystem.core.keycloak.KeyCloakService;
import at.ltb.apprenticedeliverysystem.core.user.mapper.UserMapper;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.user.api.UserService;
import at.ltb.apprenticedeliverysystem.core.user.dto.PortalUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserQueryDSLRepository userQueryDSLRepository;

    private final UserCrudRepository userCrudRepository;

    private final UserMapper userMapper;

    private final KeyCloakService keyCloakService;

    private final AuthUserHelper authUserHelper;

    public UserServiceImpl(UserQueryDSLRepository userQueryDSLRepository, UserCrudRepository userCrudRepository,
                           UserMapper userMapper, KeyCloakService keyCloakService, AuthUserHelper authUserHelper) {
        this.userQueryDSLRepository = userQueryDSLRepository;
        this.userCrudRepository = userCrudRepository;
        this.userMapper = userMapper;
        this.keyCloakService = keyCloakService;
        this.authUserHelper = authUserHelper;
    }

    @Override
    public PortalUserDTO loadPortalUser() {
        UserEntity foundedEntity = userQueryDSLRepository.loadUserByKeyCloakReference(authUserHelper.getCurrentUser());

        if(Objects.isNull(foundedEntity)) {
            logger.error("PortalUser not found: " + authUserHelper.getCurrentUser());
            throw new CustomEntityNotFoundException(UserEntity.class.getSimpleName() + " not found");
        }

        logger.info("PortalUser was found!");
        PortalUserDTO loggedInUser = userMapper.mapUserEntityToPortalUser(foundedEntity);
        loggedInUser.setRoles(RoleEnum.findListByKeyCloakName(
                keyCloakService.loadGroupNamesByKeyCloakId(authUserHelper.getCurrentUser())));
        return loggedInUser;
    }

    @Override
    public UserDetailDTO loadUser() {
        UserEntity foundedEntity = userQueryDSLRepository.loadUserByKeyCloakReference(authUserHelper.getCurrentUser());

        if(Objects.isNull(foundedEntity)) {
            logger.error("UserDetail not found: " + authUserHelper.getCurrentUser());
            throw new CustomEntityNotFoundException(UserEntity.class.getSimpleName() + " not found");
        }

        logger.info("UserDetail was found!");
        return userMapper.mapUserEntityToDetailForUser(foundedEntity);
    }

    @Override
    @Transactional
    public UserDetailDTO updateUser(UpdateUserDTO request) {
        if(Objects.isNull(request.id())) {
            logger.error("UserEntity with no id updated");
            throw new UserUpdateException("id is empty");
        }
        UserEntity userToUpdate = userQueryDSLRepository.loadUserByUuid(request.id());
        if(Objects.isNull(userToUpdate)) {
            logger.error("UserEntity loggedIn user not found");
            throw new UserUpdateException("UserEntity not found for id " + request.id());
        }
        if(!userToUpdate.getKeycloakReference().equals(authUserHelper.getCurrentUser())) {
            logger.error("UserEntity is not loggedIn user");
            throw new UserUpdateException("UserEntity is not loggedIn user");
        }
        userToUpdate = userMapper.mapUpdateUserToEntityForUser(request, userToUpdate);
        logger.info("UserEntity:Update KeyCloakService is called!");
        keyCloakService.updateKeyCloakUser(request, userToUpdate);
        logger.info("UserEntity:Update Update is called!");
        userCrudRepository.save(userToUpdate);
        logger.info("UserEntity:Update successfully!");
        return userMapper.mapUserEntityToDetailForUser(userToUpdate);
    }

}
