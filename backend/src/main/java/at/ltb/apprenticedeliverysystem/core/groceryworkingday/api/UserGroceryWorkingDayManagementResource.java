package at.ltb.apprenticedeliverysystem.core.groceryworkingday.api;

import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "UserGroceryWorkingDayManagementResource", description = "Apis for groceryWorkingDays")
public interface UserGroceryWorkingDayManagementResource {

    @Operation(description = "load grocery working day from today")
    @GetMapping(value = "/today", produces = "application/json")
    GroceryWorkingDayDetailDTO loadOrCreateGroceryWorkingDayToday();

    @Operation(description = "add current logged in user to going users")
    @PostMapping(value = "/add/going-users/", produces = "application/json")
    GroceryWorkingDayDetailDTO addLoggedInUserToGoingUsers();

    @Operation(description = "remove current logged in user to going users")
    @PostMapping(value = "/remove/going-users/", produces = "application/json")
    GroceryWorkingDayDetailDTO removeLoggedInUserToGoingUsers();

    @Operation(description = "set current logged in user as paying users")
    @PostMapping(value = "/add/paying-users/", produces = "application/json")
    GroceryWorkingDayDetailDTO setCurrentLoggedInUserAsPayingUser();

    @Operation(description = "remove current logged in user as paying users")
    @PostMapping(value = "/remove/paying-users/", produces = "application/json")
    GroceryWorkingDayDetailDTO removeCurrentLoggedInUserAsPayingUser();
}
