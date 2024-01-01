package at.ltb.apprenticedeliverysystem.core.category.service;

import at.ltb.apprenticedeliverysystem.core._common.exception.CustomEntityNotFoundException;
import at.ltb.apprenticedeliverysystem.core._common.pagination.PaginationUtil;
import at.ltb.apprenticedeliverysystem.core._common.response.QueryDslOverviewResponse;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.category._persistence.CategoryCrudRepository;
import at.ltb.apprenticedeliverysystem.core.category._persistence.CategoryEntity;
import at.ltb.apprenticedeliverysystem.core.category._persistence.CategoryQueryDSLRepository;
import at.ltb.apprenticedeliverysystem.core.category.api.AdminModCategoryManagementService;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryDetailDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CreateCategoryDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.UpdateCategoryDTO;
import at.ltb.apprenticedeliverysystem.core.category.exception.CategoryNameNotUniqueException;
import at.ltb.apprenticedeliverysystem.core.category.exception.CategoryUpdateException;
import at.ltb.apprenticedeliverysystem.core.category.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminModCategoryManagementServiceImpl implements AdminModCategoryManagementService {

    private final Logger logger = LoggerFactory.getLogger(AdminModCategoryManagementServiceImpl.class);

    private final CategoryQueryDSLRepository categoryQueryDSLRepository;

    private final CategoryCrudRepository categoryCrudRepository;

    private final CategoryMapper categoryMapper;

    public AdminModCategoryManagementServiceImpl(CategoryQueryDSLRepository categoryQueryDSLRepository,
                                                 CategoryCrudRepository categoryCrudRepository,
                                                 CategoryMapper categoryMapper) {
        this.categoryQueryDSLRepository = categoryQueryDSLRepository;
        this.categoryCrudRepository = categoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ResponseWrapper<CategoryOverviewDTO> loadAllCategories(Integer page, Integer pageSize,
                                                                  Optional<String> searchTerm) {
        QueryDslOverviewResponse<CategoryEntity> response = categoryQueryDSLRepository
                .loadCategory(searchTerm, PaginationUtil.getPagination(page, pageSize));
        logger.info("loadAllCategories: count: " + response.getTotalElements());
        return new ResponseWrapper<>(categoryMapper.mapCategoryEntityToOverview(response.getContent()),
                response.getTotalElements());
    }

    @Override
    public CategoryDetailDTO loadCategoryById(String uuid) {
        CategoryEntity foundedEntity = categoryQueryDSLRepository.loadCategoryByUuid(uuid);

        if(Objects.isNull(foundedEntity)) {
            logger.error("CategoryEntity not found: " + uuid);
            throw new CustomEntityNotFoundException(CategoryEntity.class.getSimpleName() + " not found");
        }

        logger.info("CategoryEntity was found!");
        return categoryMapper.mapCategoryEntityToDetail(foundedEntity);
    }

    @Override
    @Transactional
    public CategoryDetailDTO createCategory(CreateCategoryDTO request) {
        if(Objects.nonNull(categoryQueryDSLRepository.loadCategoryByName(request.name()))) {
            logger.error("CategoryEntity with duplicated name: " + request.name());
            throw new CategoryNameNotUniqueException("category name already found");
        }
        CategoryEntity userToCreate = categoryMapper.mapCreateCategoryToEntity(request);
        logger.info("CategoryEntity:Create Save is called!");
        categoryCrudRepository.save(userToCreate);
        logger.info("CategoryEntity:Create successfully!");
        return categoryMapper.mapCategoryEntityToDetail(userToCreate);
    }

    @Override
    @Transactional
    public CategoryDetailDTO updateCategory(UpdateCategoryDTO request) {
        if(Objects.isNull(request.id())) {
            logger.error("CategoryEntity with no id updated");
            throw new CategoryUpdateException("id is emtpy");
        }
        CategoryEntity categoryToUpdate = categoryQueryDSLRepository.loadCategoryByUuid(request.id());
        if(Objects.isNull(categoryToUpdate)) {
            logger.error("CategoryEntity not found");
            throw new CategoryUpdateException("category not found with id: " + request.id());
        }
        categoryToUpdate = categoryMapper.mapUpdateCategoryToEntity(request, categoryToUpdate);
        logger.info("CategoryEntity:Update Update is called!");
        categoryCrudRepository.save(categoryToUpdate);
        logger.info("CategoryEntity:Update successfully!");
        return categoryMapper.mapCategoryEntityToDetail(categoryToUpdate);
    }
}
