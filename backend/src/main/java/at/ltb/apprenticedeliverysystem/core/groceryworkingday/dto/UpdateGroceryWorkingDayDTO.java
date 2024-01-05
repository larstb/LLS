package at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateGroceryWorkingDayDTO(
        @NotBlank String id,
        @Min(0) Integer version,
        List<String> goingUserIds,
        String payingUserId,
        @NotNull GroceryWorkingDayStatusEnum status) implements BaseRequest
{ }
