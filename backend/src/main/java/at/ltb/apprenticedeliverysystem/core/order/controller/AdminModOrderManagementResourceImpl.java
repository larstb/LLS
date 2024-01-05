package at.ltb.apprenticedeliverysystem.core.order.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.order.api.AdminModOrderManagementResource;
import at.ltb.apprenticedeliverysystem.core.order.api.OrderManagementService;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderDetailDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderOverviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/portal/order")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class AdminModOrderManagementResourceImpl implements AdminModOrderManagementResource {

    private final Logger logger = LoggerFactory.getLogger(AdminModOrderManagementResourceImpl.class);

    private final OrderManagementService orderManagementService;

    public AdminModOrderManagementResourceImpl(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }

    @Override
    public ResponseWrapper<OrderOverviewDTO> loadAllOrders(Integer page, Integer pageSize, Optional<String> searchTerm,
                                                           Optional<Boolean> isPayed, Optional<PaymentTypeEnum> paymentType,
                                                           Optional<LocalDate> orderDateFrom, Optional<LocalDate> orderDateTo) {
        logger.info("API loadAllOrders was called!");
        return orderManagementService.loadAllOrders(page, pageSize, searchTerm, isPayed, paymentType, orderDateFrom, orderDateTo);
    }

    @Override
    public OrderDetailDTO loadOrderById(String id) {
        logger.info("API loadOrderById was called!");
        return orderManagementService.loadOrderById(id);
    }
}
