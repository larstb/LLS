package at.ltb.apprenticedeliverysystem.core.groceryworkingday.api;

import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.CreateGroceryWorkingDayDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.UpdateGroceryWorkingDayDTO;

import java.time.LocalDate;
import java.util.Optional;

public interface GroceryWorkingDayManagementService {

    ResponseWrapper<GroceryWorkingDayDetailDTO> loadAllGroceryWorkingDays(Integer page, Integer pageSize,
                                                                          Optional<String> goingUserId,
                                                                          Optional<String> payingUserId,
                                                                          Optional<LocalDate> date);

    GroceryWorkingDayDetailDTO loadOrCreateGroceryWorkingDayToday();

    GroceryWorkingDayDetailDTO createGroceryWorkingDay(CreateGroceryWorkingDayDTO request);

    GroceryWorkingDayDetailDTO updateGroceryWorkingDay(UpdateGroceryWorkingDayDTO request);
}
