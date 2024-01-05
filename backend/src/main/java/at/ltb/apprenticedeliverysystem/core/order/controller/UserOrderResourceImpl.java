package at.ltb.apprenticedeliverysystem.core.order.controller;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.order.api.UserOrderResource;
import at.ltb.apprenticedeliverysystem.core.product.api.ProductManagementService;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@Transactional(readOnly = true)
public class UserOrderResourceImpl implements UserOrderResource {

    private final Logger logger = LoggerFactory.getLogger(UserOrderResourceImpl.class);

    private final ProductManagementService productManagementService;

    public UserOrderResourceImpl(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @Override
    public ResponseWrapper<ProductDetailDTO> loadAllProductsForShop(Integer page, Integer pageSize,
                                                                    Optional<String> searchTerm,
                                                                    Optional<String> categoryId) {

        logger.info("API loadAllProductsForShop was called!");
        return productManagementService.loadAllProductsForShop(page, pageSize, searchTerm, categoryId);
    }
}
