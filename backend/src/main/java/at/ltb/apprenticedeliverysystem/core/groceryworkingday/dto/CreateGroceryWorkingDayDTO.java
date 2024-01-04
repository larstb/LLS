package at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto;

import at.ltb.apprenticedeliverysystem.core._common.request.BaseRequest;

import java.time.LocalDate;
import java.util.List;

public record CreateGroceryWorkingDayDTO(LocalDate date, List<String> goingUserIds, String payingUserId) implements BaseRequest
{ }
