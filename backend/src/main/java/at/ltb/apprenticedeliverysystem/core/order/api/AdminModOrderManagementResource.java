package at.ltb.apprenticedeliverysystem.core.order.api;

import at.ltb.apprenticedeliverysystem.core._common.paymenttype.PaymentTypeEnum;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderDetailDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderOverviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Tag(name = "AdminModOrderManagementResource", description = "Apis to manage orders / Only for Admin and Mod")
public interface AdminModOrderManagementResource {
    @Operation(description = "load all orders with pagination and filter")
    @GetMapping(value = "/", produces = "application/json")
    ResponseWrapper<OrderOverviewDTO> loadAllOrders(@RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm,
                                                    @RequestParam(value = "isPayed", required = false) Optional<Boolean> isPayed,
                                                    @RequestParam(value = "paymentType", required = false) Optional<PaymentTypeEnum> paymentType,
                                                    @RequestParam(value = "orderDateFrom", required = false) Optional<LocalDate> orderDateFrom,
                                                    @RequestParam(value = "orderDateTo", required = false) Optional<LocalDate> orderDateTo);

    @Operation(description = "load order by id")
    @GetMapping(value = "/{id}", produces = "application/json")
    OrderDetailDTO loadOrderById(@PathVariable("id") String id);
}
