package at.ltb.apprenticedeliverysystem.core.order.controller;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.order.api.OrderManagementService;
import at.ltb.apprenticedeliverysystem.core.order.api.UserOrderResource;
import at.ltb.apprenticedeliverysystem.core.order.dto.CreateOrderDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderDetailDTO;
import at.ltb.apprenticedeliverysystem.core.product.api.ProductManagementService;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@Transactional(readOnly = true)
public class UserOrderResourceImpl implements UserOrderResource {

    private final Logger logger = LoggerFactory.getLogger(UserOrderResourceImpl.class);

    private final ProductManagementService productManagementService;

    private final OrderManagementService orderManagementService;

    public UserOrderResourceImpl(ProductManagementService productManagementService, OrderManagementService orderManagementService) {
        this.productManagementService = productManagementService;
        this.orderManagementService = orderManagementService;
    }

    @Override
    public ResponseWrapper<ProductDetailDTO> loadAllProductsForShop(Integer page, Integer pageSize,
                                                                    Optional<String> searchTerm,
                                                                    Optional<String> categoryId) {

        logger.info("API loadAllProductsForShop was called!");
        return productManagementService.loadAllProductsForShop(page, pageSize, searchTerm, categoryId);
    }

    @Override
    public ResponseWrapper<OrderDetailDTO> loadAllOrdersForPayingUser(Integer page, Integer pageSize) {
        logger.info("API loadAllOrdersForPayingUser was called!");
        return orderManagementService.loadAllOrdersForPayingUser(page, pageSize);
    }

    @Override
    public List<OrderDetailDTO> loadTodayOrderForPayingUser() {
        logger.info("API loadTodayOrderForPayingUser was called!");
        return orderManagementService.loadTodayOrderForPayingUser();
    }

    @Override
    public OrderDetailDTO createOrderForCurrentCustomer(CreateOrderDTO request) {
        logger.info("API createOrderForCurrentCustomer was called!");
        return orderManagementService.createOrder(request);
    }
}
