package at.ltb.apprenticedeliverysystem.core.user.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdatePortalUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;

import java.util.Optional;

public interface AdminModUserManagementService {
    ResponseWrapper<UserOverviewDTO> loadAllUsers(Integer page, Integer pageSize,
                                                  Optional<String> searchTerm);

    UserDetailDTO loadUserById(String uuid);

    UserDetailDTO createUser(CreateUserDTO request);

    UserDetailDTO updateUserPortal(UpdatePortalUserDTO request);
}
