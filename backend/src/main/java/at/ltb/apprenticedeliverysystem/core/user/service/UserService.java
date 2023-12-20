package at.ltb.apprenticedeliverysystem.core.user.service;

import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import at.ltb.apprenticedeliverysystem.core._common.pagination.PaginationUtil;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.keycloak.KeyCloakService;
import at.ltb.apprenticedeliverysystem.core.user.UserMapper;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserEntity;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserEmailNotUniqueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserQueryDSLRepository userQueryDSLRepository;

    private final UserCrudRepository userCrudRepository;

    private final UserMapper userMapper;

    private final KeyCloakService keyCloakService;

    public UserService(UserQueryDSLRepository userQueryDSLRepository, UserCrudRepository userCrudRepository,
                       UserMapper userMapper, KeyCloakService keyCloakService) {
        this.userQueryDSLRepository = userQueryDSLRepository;
        this.userCrudRepository = userCrudRepository;
        this.userMapper = userMapper;
        this.keyCloakService = keyCloakService;
    }

    public ResponseWrapper<UserOverviewDTO> loadAllUsers(Integer page, Integer pageSize,
                                                         Optional<String> searchTerm) {
        QueryDslOverviewResponse<UserEntity> response = userQueryDSLRepository
                .loadUsers(searchTerm, PaginationUtil.getPagination(page, pageSize));
        return new ResponseWrapper<>(userMapper.mapUserEntityToOverview(response.getContent()),
                response.getTotalElements());
    }

    public UserDetailDTO loadUserById(String uuid) {
        UserEntity foundedEntity = userQueryDSLRepository.loadUserByUuid(uuid);

        if(Objects.isNull(foundedEntity)) {
            throw new CustomEntityNotFoundException(UserEntity.class.getSimpleName() + " not found");
        }

        return userMapper.mapUserEntityToDetail(foundedEntity);
    }

    @Transactional
    public UserDetailDTO createUser(CreateUserDTO request) {
        if(Objects.nonNull(userQueryDSLRepository.loadUserByEmail(request.email()))) {
            throw new UserEmailNotUniqueException("user email already found");
        }
        UserEntity userToCreate = userMapper.mapCreateUserToEntity(request);
        userToCreate.setKeycloakReference(keyCloakService.createKeyCloakUser(request));
        userCrudRepository.save(userToCreate);
        return userMapper.mapUserEntityToDetail(userToCreate);
    }

    @Transactional
    public UserDetailDTO updateUser(UpdateUserDTO request) {
        UserEntity userToUpdate = userQueryDSLRepository.loadUserByUuid(request.id());
        userToUpdate = userMapper.mapUpdateUserToEntity(request, userToUpdate);
        keyCloakService.updateKeyCloakUser(request, userToUpdate);
        userCrudRepository.save(userToUpdate);
        return userMapper.mapUserEntityToDetail(userToUpdate);
    }

}
