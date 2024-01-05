package at.ltb.apprenticedeliverysystem.core.order.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Tag(name = "UserOrderResource", description = "Apis for webshop")
public interface UserOrderResource {

    @Operation(description = "load all products for shop")
    @GetMapping(value = "/", produces = "application/json")
    ResponseWrapper<ProductDetailDTO> loadAllProductsForShop(@RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm,
                                                    @RequestParam(value = "categoryId", required = false) Optional<String> categoryId);
}
