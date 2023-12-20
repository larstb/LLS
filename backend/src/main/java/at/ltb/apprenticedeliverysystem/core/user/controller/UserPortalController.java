package at.ltb.apprenticedeliverysystem.core.user.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserPortalDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserPortalDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailPortalDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewPortalDTO;
import at.ltb.apprenticedeliverysystem.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/portal/user")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class UserPortalController {

    private final Logger logger = LoggerFactory.getLogger(UserPortalController.class);

    private final UserService userService;

    public UserPortalController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseWrapper<UserOverviewPortalDTO> loadAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                                               @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                               @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm) {
        logger.info("API loadAllUsers was called!");
        return userService.loadAllUsers(page, pageSize, searchTerm);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserDetailPortalDTO loadUserById(@PathVariable("id") String id) {
        logger.info("API loadUserById was called!");
        return userService.loadUserById(id);
    }

    @PostMapping(value = "/", produces = "application/json")
    @Transactional
    public UserDetailPortalDTO createUser(@RequestBody CreateUserPortalDTO request) {
        logger.info("API createUser was called!");
        return userService.createUser(request);
    }

    @PutMapping(value = "/", produces = "application/json")
    @Transactional
    public UserDetailPortalDTO updateUser(@RequestBody UpdateUserPortalDTO request) {
        logger.info("API updateUser was called!");
        return userService.updateUserPortal(request);
    }
}
