package at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday._persistence.GroceryWorkingDayStatusEnum;

import java.util.List;

public record UpdateGroceryWorkingDayDTO(
        String id,
        Integer version,
        List<String> goingUserIds,
        String payingUserId,
        GroceryWorkingDayStatusEnum status) implements BaseRequest
{ }
