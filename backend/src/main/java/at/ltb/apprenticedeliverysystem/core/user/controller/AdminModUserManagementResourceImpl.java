package at.ltb.apprenticedeliverysystem.core.user.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.user.api.AdminModUserManagementResource;
import at.ltb.apprenticedeliverysystem.core.user.api.AdminModUserManagementService;
import at.ltb.apprenticedeliverysystem.core.user.dto.CreateUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UpdatePortalUserDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserDetailDTO;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/portal/user")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class AdminModUserManagementResourceImpl implements AdminModUserManagementResource {

    private final Logger logger = LoggerFactory.getLogger(AdminModUserManagementResourceImpl.class);

    private final AdminModUserManagementService adminModUserManagementService;

    public AdminModUserManagementResourceImpl(AdminModUserManagementService adminModUserManagementService) {
        this.adminModUserManagementService = adminModUserManagementService;
    }

    @Override
    public ResponseWrapper<UserOverviewDTO> loadAllUsers(Integer page, Integer pageSize, Optional<String> searchTerm) {
        logger.info("API loadAllUsers was called!");
        return adminModUserManagementService.loadAllUsers(page, pageSize, searchTerm);
    }

    @Override
    public UserDetailDTO loadUserById(String id) {
        logger.info("API loadUserById was called!");
        return adminModUserManagementService.loadUserById(id);
    }

    @Override
    @Transactional
    public UserDetailDTO createUser(CreateUserDTO request) {
        logger.info("API createUser was called!");
        return adminModUserManagementService.createUser(request);
    }

    @Override
    @Transactional
    public UserDetailDTO updateUser(UpdatePortalUserDTO request) {
        logger.info("API updateUser was called!");
        return adminModUserManagementService.updateUserPortal(request);
    }
}
