package at.ltb.apprenticedeliverysystem.core.user.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdatePortalUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Tag(name = "AdminModUserManagementResource", description = "Apis to manage user / Only for Admin and Mod")
public interface AdminModUserManagementResource {

    @Operation(description = "load all users with pagination and filter")
    @GetMapping(value = "/", produces = "application/json", consumes = "application/json")
    ResponseWrapper<UserOverviewDTO> loadAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm);

    @Operation(description = "load user by id")
    @GetMapping(value = "/{id}", produces = "application/json")
    UserDetailDTO loadUserById(@PathVariable("id") String id);

    @Operation(description = "create new user")
    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    UserDetailDTO createUser(@RequestBody CreateUserDTO request);

    @Operation(description = "update user")
    @PutMapping(value = "/", produces = "application/json", consumes = "application/json")
    UserDetailDTO updateUser(@RequestBody UpdatePortalUserDTO request);
}
