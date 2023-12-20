package at.ltb.apprenticedeliverysystem.core.user.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseWrapper<UserOverviewDTO> loadAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm) {
        return userService.loadAllUsers(page, pageSize, searchTerm);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserDetailDTO loadUserById(@PathVariable("id") String id) {
        return userService.loadUserById(id);
    }

    @PostMapping(value = "/", produces = "application/json")
    @Transactional
    public UserDetailDTO createUser(@RequestBody CreateUserDTO request) {
        return userService.createUser(request);
    }

    @PutMapping(value = "/", produces = "application/json")
    @Transactional
    public UserDetailDTO createUser(@RequestBody UpdateUserDTO request) {
        return userService.updateUser(request);
    }
}
