package at.ltb.apprenticedeliverysystem.core.user.controller;

import at.ltb.apprenticedeliverysystem.core.user.api.UserResource;
import at.ltb.apprenticedeliverysystem.core.user.api.UserService;
import at.ltb.apprenticedeliverysystem.core.user.dto.PortalUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Transactional(readOnly = true)
public class UserResourceImpl implements UserResource {

    private final Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);

    private final UserService userService;

    public UserResourceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public PortalUserDTO loadPortalUser() {
        logger.info("API loadLoggedInUser was called!");
        return userService.loadPortalUser();
    }

    @Override
    public UserDetailDTO loadUser() {
        logger.info("API loadUser was called!");
        return userService.loadUser();
    }

    @Override
    @Transactional
    public UserDetailDTO updateUser(@RequestBody UpdateUserDTO request) {
        logger.info("API updateUser was called!");
        return userService.updateUser(request);
    }
}
