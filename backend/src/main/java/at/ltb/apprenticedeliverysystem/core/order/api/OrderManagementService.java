package at.ltb.apprenticedeliverysystem.core.order.api;

import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.order.dto.CreateOrderDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderDetailDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderOverviewDTO;

import java.time.LocalDate;
import java.util.Optional;

public interface OrderManagementService {

    ResponseWrapper<OrderOverviewDTO> loadAllOrders(Integer page, Integer pageSize,
                                                    Optional<String> searchTerm,
                                                    Optional<Boolean> isPayed,
                                                    Optional<PaymentTypeEnum> paymentType,
                                                    Optional<LocalDate> orderDateFrom,
                                                    Optional<LocalDate> orderDateTo);

    OrderDetailDTO loadOrderById(String uuid);

    OrderDetailDTO createOrder(CreateOrderDTO request);

}
