package at.ltb.apprenticedeliverysystem.core.order.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.order.dto.CreateOrderDTO;
import at.ltb.apprenticedeliverysystem.core.order.dto.OrderDetailDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Tag(name = "UserOrderResource", description = "Apis for webshop")
public interface UserOrderResource {

    @Operation(description = "load all products for shop")
    @GetMapping(value = "/products", produces = "application/json")
    ResponseWrapper<ProductDetailDTO> loadAllProductsForShop(@RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm,
                                                    @RequestParam(value = "categoryId", required = false) Optional<String> categoryId);

    @Operation(description = "load all order for the paying user with pagination and filter")
    @GetMapping(value = "/paying-user/orders", produces = "application/json")
    ResponseWrapper<OrderDetailDTO> loadAllOrdersForPayingUser(@RequestParam(value = "page", required = false) Integer page,
                                                               @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @Operation(description = "load all order for the paying user with pagination and filter")
    @GetMapping(value = "/today/orders", produces = "application/json")
    List<OrderDetailDTO> loadTodayOrderForPayingUser();

    @Operation(description = "create order for the current customer")
    @PostMapping(value = "/today/orders", produces = "application/json")
    OrderDetailDTO createOrderForCurrentCustomer(@RequestBody CreateOrderDTO request);
}
