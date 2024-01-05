package at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateGroceryWorkingDayDTO(
        @NotNull LocalDate date,
        List<String> goingUserIds,
        String payingUserId) implements BaseRequest
{ }
