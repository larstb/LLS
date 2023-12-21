package at.ltb.apprenticedeliverysystem.core.user.api;

import at.ltb.apprenticedeliverysystem.core.user.dto.PortalUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;

public interface UserService {

    PortalUserDTO loadPortalUser();

    UserDetailDTO loadUser();

    UserDetailDTO updateUser(UpdateUserDTO request);
}
