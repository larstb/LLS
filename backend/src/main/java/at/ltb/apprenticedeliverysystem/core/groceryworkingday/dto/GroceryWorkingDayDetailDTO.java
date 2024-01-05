package at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto;

import at.ltb.apprenticedeliverysystem.core._common.response.BaseResponse;
import at.ltb.apprenticedeliverysystem.core.user.dto.UserOverviewDTO;

import java.time.LocalDate;
import java.util.List;

public record GroceryWorkingDayDetailDTO(
        String id,
        Integer version,
        LocalDate date,
        List<UserOverviewDTO> goingUsers,
        UserOverviewDTO payingUser) implements BaseResponse
{ }
