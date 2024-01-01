package at.ltb.apprenticedeliverysystem.core.category.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryDetailDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CreateCategoryDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.UpdateCategoryDTO;

import java.util.Optional;

public interface AdminModCategoryManagementService {

    ResponseWrapper<CategoryOverviewDTO> loadAllCategories(Integer page, Integer pageSize,
                                                           Optional<String> searchTerm);

    CategoryDetailDTO loadCategoryById(String uuid);

    CategoryDetailDTO createCategory(CreateCategoryDTO request);

    CategoryDetailDTO updateCategory(UpdateCategoryDTO request);
}
