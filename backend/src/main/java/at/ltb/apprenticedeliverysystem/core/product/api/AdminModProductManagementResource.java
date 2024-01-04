package at.ltb.apprenticedeliverysystem.core.product.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.product.dto.CreateProductDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.UpdateProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Tag(name = "AdminModProductManagementResource", description = "Apis to manage products / Only for Admin and Mod")
public interface AdminModProductManagementResource {
    @Operation(description = "load all products with pagination and filter")
    @GetMapping(value = "/", produces = "application/json")
    ResponseWrapper<ProductOverviewDTO> loadAllProducts(@RequestParam(value = "page", required = false) Integer page,
                                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                        @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm,
                                                        @RequestParam(value = "isActive", required = false) Optional<Boolean> isActive,
                                                        @RequestParam(value = "isChecked", required = false) Optional<Boolean> isChecked,
                                                        @RequestParam(value = "categoryId", required = false) Optional<String> categoryId);

    @Operation(description = "load product by id")
    @GetMapping(value = "/{id}", produces = "application/json")
    ProductDetailDTO loadProductById(@PathVariable("id") String id);

    @Operation(description = "create new product")
    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    ProductDetailDTO createProduct(@RequestBody CreateProductDTO request);

    @Operation(description = "update category")
    @PutMapping(value = "/", produces = "application/json", consumes = "application/json")
    ProductDetailDTO updateProduct(@RequestBody UpdateProductDTO request);
}
