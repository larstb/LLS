package at.ltb.apprenticedeliverysystem.core.user.api;

import at.ltb.apprenticedeliverysystem.core.user.dto.PortalUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "UserResource", description = "Apis for the logged in user")
public interface UserResource {

    @Operation(description = "load portal user (short info)")
    @GetMapping(value = "/current-user", produces = "application/json")
    PortalUserDTO loadPortalUser();

    @Operation(description = "load logged in user")
    @GetMapping(value = "/", produces = "application/json")
    UserDetailDTO loadUser();

    @Operation(description = "update the current logged in user")
    @PutMapping(value = "/", produces = "application/json", consumes = "application/json")
    UserDetailDTO updateUser(@RequestBody UpdateUserDTO request);

}
