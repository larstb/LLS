package at.ltb.apprenticedeliverysystem.core.product.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.product.dto.CreateProductDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.UpdateProductDTO;

import java.util.Optional;

public interface ProductManagementService {

    ResponseWrapper<ProductOverviewDTO> loadAllProducts(Integer page, Integer pageSize,
                                                        Optional<String> searchTerm, Optional<Boolean> isActive,
                                                        Optional<Boolean> isChecked, Optional<String> categoryId);

    ResponseWrapper<ProductDetailDTO> loadAllProductsForShop(Integer page, Integer pageSize,
                                                        Optional<String> searchTerm, Optional<String> categoryId);

    ProductDetailDTO loadProductById(String uuid);

    ProductDetailDTO createProduct(CreateProductDTO request);

    ProductDetailDTO updateProduct(UpdateProductDTO request);
}
