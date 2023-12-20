package at.ltb.apprenticedeliverysystem.core.user.controller;

import at.ltb.apprenticedeliverysystem.core.user.dto.LoggedInUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Transactional(readOnly = true)
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/current-user", produces = "application/json")
    public LoggedInUserDTO loadLoggedInUser() {
        logger.info("API loadLoggedInUser was called!");
        return userService.loadUserShortInfosForLoggedInUser();
    }

    @GetMapping(value = "/", produces = "application/json")
    public UserDetailDTO loadUser() {
        logger.info("API loadUser was called!");
        return userService.loadUserForLoggedInUser();
    }

    @PutMapping(value = "/", produces = "application/json")
    @Transactional
    public UserDetailDTO updateUser(@RequestBody UpdateUserDTO request) {
        logger.info("API updateUser was called!");
        return userService.updateUser(request);
    }
}
