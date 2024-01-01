package at.ltb.apprenticedeliverysystem.core.category.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryDetailDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CategoryOverviewDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.CreateCategoryDTO;
import at.ltb.apprenticedeliverysystem.core.category.dto.UpdateCategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Tag(name = "AdminModCategoryManagementResource", description = "Apis to manage categories / Only for Admin and Mod")
public interface AdminModCategoryManagementResource {
    @Operation(description = "load all categories with pagination and filter")
    @GetMapping(value = "/", produces = "application/json")
    ResponseWrapper<CategoryOverviewDTO> loadAllCategories(@RequestParam(value = "page", required = false) Integer page,
                                                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                           @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm);

    @Operation(description = "load all categories with filter")
    @GetMapping(value = "/all", produces = "application/json")
    ResponseWrapper<CategoryOverviewDTO> loadAllCategoriesWithoutPagination(@RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm);

    @Operation(description = "load category by id")
    @GetMapping(value = "/{id}", produces = "application/json")
    CategoryDetailDTO loadCategoryById(@PathVariable("id") String id);

    @Operation(description = "create new category")
    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    CategoryDetailDTO createCategory(@RequestBody CreateCategoryDTO request);

    @Operation(description = "update category")
    @PutMapping(value = "/", produces = "application/json", consumes = "application/json")
    CategoryDetailDTO updateCategory(@RequestBody UpdateCategoryDTO request);
}
