package at.ltb.apprenticedeliverysystem.core.product.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.product.api.AdminModProductManagementResource;
import at.ltb.apprenticedeliverysystem.core.product.api.AdminModProductManagementService;
import at.ltb.apprenticedeliverysystem.core.product.dto.CreateProductDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.UpdateProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/portal/product")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class AdminModProductManagementResourceImpl implements AdminModProductManagementResource {

    private final Logger logger = LoggerFactory.getLogger(AdminModProductManagementResourceImpl.class);

    private final AdminModProductManagementService adminModProductManagementService;

    public AdminModProductManagementResourceImpl(AdminModProductManagementService adminModProductManagementService) {
        this.adminModProductManagementService = adminModProductManagementService;
    }

    @Override
    public ResponseWrapper<ProductOverviewDTO> loadAllProducts(Integer page, Integer pageSize, Optional<String> searchTerm,
                                                               Optional<Boolean> isActive, Optional<Boolean> isChecked,
                                                               Optional<String> categoryId) {
        logger.info("API loadAllProducts was called!");
        return adminModProductManagementService.loadAllProducts(page, pageSize, searchTerm, isActive, isChecked, categoryId);
    }

    @Override
    public ProductDetailDTO loadProductById(String id) {
        logger.info("API loadProductById was called!");
        return adminModProductManagementService.loadProductById(id);
    }

    @Override
    @Transactional
    public ProductDetailDTO createProduct(CreateProductDTO request) {
        logger.info("API createProduct was called!");
        return adminModProductManagementService.createProduct(request);
    }

    @Override
    @Transactional
    public ProductDetailDTO updateProduct(UpdateProductDTO request) {
        logger.info("API updateProduct was called!");
        return adminModProductManagementService.updateProduct(request);
    }
}
