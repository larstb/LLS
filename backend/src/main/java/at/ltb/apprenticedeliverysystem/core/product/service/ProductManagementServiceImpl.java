package at.ltb.apprenticedeliverysystem.core.product.service;

import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core._common.pagination.PaginationUtil;
import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.category._persistence.CategoryEntity;
import at.ltb.apprenticedeliverysystem.core.category._persistence.CategoryQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductCrudRepository;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductEntity;
import at.ltb.apprenticedeliverysystem.core.product._persistence.ProductQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.product.api.ProductManagementService;
import at.ltb.apprenticedeliverysystem.core.product.dto.CreateProductDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductDetailDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.ProductOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.product.dto.UpdateProductDTO;
import at.ltb.apprenticedeliverysystem.core.product.exception.ProductCreateCategoryIdException;
import at.ltb.apprenticedeliverysystem.core.product.exception.ProductNameNotUniqueException;
import at.ltb.apprenticedeliverysystem.core.product.exception.ProductUpdateException;
import at.ltb.apprenticedeliverysystem.core.product.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductManagementServiceImpl implements ProductManagementService {

    private final Logger logger = LoggerFactory.getLogger(ProductManagementServiceImpl.class);

    private final ProductQueryDSLRepository productQueryDSLRepository;

    private final CategoryQueryDSLRepository categoryQueryDSLRepository;

    private final ProductCrudRepository productCrudRepository;

    private final ProductMapper productMapper;

    public ProductManagementServiceImpl(ProductQueryDSLRepository productQueryDSLRepository,
                                        CategoryQueryDSLRepository categoryQueryDSLRepository,
                                        ProductCrudRepository productCrudRepository, ProductMapper productMapper) {
        this.productQueryDSLRepository = productQueryDSLRepository;
        this.categoryQueryDSLRepository = categoryQueryDSLRepository;
        this.productCrudRepository = productCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseWrapper<ProductOverviewDTO> loadAllProducts(Integer page, Integer pageSize,
                                                                 Optional<String> searchTerm, Optional<Boolean> isActive,
                                                               Optional<Boolean> isChecked, Optional<String> categoryId) {
        QueryDslOverviewResponse<ProductEntity> response = productQueryDSLRepository
                .loadProductsOverview(searchTerm, isActive, isChecked, categoryId,
                        PaginationUtil.getPagination(page, pageSize));
        logger.info("loadAllProducts: count: " + response.getTotalElements());
        return new ResponseWrapper<>(productMapper.mapProductEntityToOverview(response.getContent()),
                response.getTotalElements());
    }

    @Override
    public ProductDetailDTO loadProductById(String uuid) {
        ProductEntity foundedEntity = productQueryDSLRepository.loadProductByUuid(uuid);

        if(Objects.isNull(foundedEntity)) {
            logger.error("ProductEntity not found: " + uuid);
            throw new CustomEntityNotFoundException(ProductEntity.class.getSimpleName() + " not found");
        }

        logger.info("ProductEntity was found!");
        return productMapper.mapProductEntityToDetail(foundedEntity);
    }

    @Override
    @Transactional
    public ProductDetailDTO createProduct(CreateProductDTO request) {
        if(Objects.nonNull(productQueryDSLRepository.loadProductByName(request.name()))) {
            logger.error("ProductEntity with duplicated name: " + request.name());
            throw new ProductNameNotUniqueException("category name already found");
        }
        CategoryEntity category = categoryQueryDSLRepository.loadCategoryByUuid(request.categoryId());
        if(Objects.isNull(category)) {
            logger.error("ProductEntity with wrong category id: " + request.categoryId());
            throw new ProductCreateCategoryIdException("category id not found");
        }
        ProductEntity productToCreate = productMapper.mapCreateProductToEntity(request);
        productToCreate.setCategory(category);
        logger.info("ProductEntity:Create Save is called!");
        productCrudRepository.save(productToCreate);
        logger.info("ProductEntity:Create successfully!");
        return productMapper.mapProductEntityToDetail(productToCreate);
    }

    @Override
    @Transactional
    public ProductDetailDTO updateProduct(UpdateProductDTO request) {
        if(Objects.isNull(request.id())) {
            logger.error("ProductEntity with no id updated");
            throw new ProductUpdateException("id is emtpy");
        }
        ProductEntity productToUpdate = productQueryDSLRepository.loadProductByUuid(request.id());
        if(Objects.isNull(productToUpdate)) {
            logger.error("ProductEntity not found: " + request.id());
            throw new ProductUpdateException("product not found");
        }
        CategoryEntity category = categoryQueryDSLRepository.loadCategoryByUuid(request.categoryId());
        if(Objects.isNull(category)) {
            logger.error("ProductEntity with wrong category id: " + request.categoryId());
            throw new ProductUpdateException("category id not found");
        }
        productToUpdate = productMapper.mapUpdateProductToEntity(request, productToUpdate);
        productToUpdate.setCategory(category);
        logger.info("ProductEntity:Update Update is called!");
        productCrudRepository.save(productToUpdate);
        logger.info("ProductEntity:Update successfully!");
        return productMapper.mapProductEntityToDetail(productToUpdate);
    }
}
