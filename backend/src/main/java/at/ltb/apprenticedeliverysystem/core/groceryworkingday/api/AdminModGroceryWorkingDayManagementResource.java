package at.ltb.apprenticedeliverysystem.core.groceryworkingday.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.CreateGroceryWorkingDayDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.UpdateGroceryWorkingDayDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Tag(name = "AdminModGroceryWorkingDayManagementResource", description = "Apis to manage groceryWorkingDays / Only for Admin and Mod")
public interface AdminModGroceryWorkingDayManagementResource {

    @Operation(description = "load all grocery working days with pagination and filter")
    @GetMapping(value = "/", produces = "application/json")
    ResponseWrapper<GroceryWorkingDayDetailDTO> loadAllGroceryWorkingDays(
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam(value = "goingUserId", required = false) Optional<String> goingUserId,
                                                    @RequestParam(value = "payingUserId", required = false) Optional<String> payingUserId,
                                                    @RequestParam(value = "date", required = false) Optional<LocalDate> date);

    @Operation(description = "create new grocery working day")
    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    GroceryWorkingDayDetailDTO createGroceryWorkingDay(@RequestBody CreateGroceryWorkingDayDTO request);

    @Operation(description = "update grocery working day")
    @PutMapping(value = "/", produces = "application/json", consumes = "application/json")
    GroceryWorkingDayDetailDTO updateGroceryWorkingDay(@RequestBody UpdateGroceryWorkingDayDTO request);
}
