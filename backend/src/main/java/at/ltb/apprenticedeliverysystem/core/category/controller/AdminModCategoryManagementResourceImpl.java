package at.ltb.apprenticedeliverysystem.core.category.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.category.api.AdminModCategoryManagementResource;
import at.ltb.apprenticedeliverysystem.core.category.api.AdminModCategoryManagementService;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryDetailDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CreateCategoryDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.UpdateCategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/portal/category")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class AdminModCategoryManagementResourceImpl implements AdminModCategoryManagementResource {

    private final Logger logger = LoggerFactory.getLogger(AdminModCategoryManagementResourceImpl.class);

    private final AdminModCategoryManagementService adminModUserManagementService;

    public AdminModCategoryManagementResourceImpl(AdminModCategoryManagementService adminModUserManagementService) {
        this.adminModUserManagementService = adminModUserManagementService;
    }

    @Override
    public ResponseWrapper<CategoryOverviewDTO> loadAllCategories(Integer page, Integer pageSize, Optional<String> searchTerm) {
        logger.info("API loadAllCategories was called!");
        return adminModUserManagementService.loadAllCategories(page, pageSize, searchTerm);
    }

    @Override
    public ResponseWrapper<CategoryOverviewDTO> loadAllCategoriesWithoutPagination(Optional<String> searchTerm) {
        logger.info("API loadAllCategoriesWithoutPagination was called!");
        return adminModUserManagementService.loadAllCategoriesWithoutPagination(searchTerm);
    }

    @Override
    public CategoryDetailDTO loadCategoryById(String id) {
        logger.info("API loadCategoryById was called!");
        return adminModUserManagementService.loadCategoryById(id);
    }

    @Override
    @Transactional
    public CategoryDetailDTO createCategory(CreateCategoryDTO request) {
        logger.info("API createUser was called!");
        return adminModUserManagementService.createCategory(request);
    }

    @Override
    @Transactional
    public CategoryDetailDTO updateCategory(UpdateCategoryDTO request) {
        logger.info("API updateUser was called!");
        return adminModUserManagementService.updateCategory(request);
    }
}
