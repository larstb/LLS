package at.ltb.apprenticedeliverysystem.core.groceryworkingday.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core._common.response.ResponseWrapper;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.api.AdminModGroceryWorkingDayManagementResource;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.api.GroceryWorkingDayManagementService;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.CreateGroceryWorkingDayDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.UpdateGroceryWorkingDayDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/portal/grocery-working-day")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class AdminModGroceryWorkingDayManagementResourceImpl implements AdminModGroceryWorkingDayManagementResource {

    private final Logger logger = LoggerFactory.getLogger(AdminModGroceryWorkingDayManagementResourceImpl.class);

    private final GroceryWorkingDayManagementService groceryWorkingDayManagementService;

    public AdminModGroceryWorkingDayManagementResourceImpl(
            GroceryWorkingDayManagementService groceryWorkingDayManagementService) {
        this.groceryWorkingDayManagementService = groceryWorkingDayManagementService;
    }

    @Override
    public ResponseWrapper<GroceryWorkingDayDetailDTO> loadAllGroceryWorkingDays(Integer page, Integer pageSize,
                                                                                 Optional<String> goingUserId,
                                                                                 Optional<String> payingUserId,
                                                                                 Optional<LocalDate> date) {
        logger.info("API loadAllGroceryWorkingDays was called!");
        return groceryWorkingDayManagementService
                .loadAllGroceryWorkingDays(page, pageSize, goingUserId, payingUserId, date);
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO createGroceryWorkingDay(CreateGroceryWorkingDayDTO request) {
        logger.info("API createGroceryWorkingDay was called!");
        return groceryWorkingDayManagementService.createGroceryWorkingDay(request);
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO updateGroceryWorkingDay(UpdateGroceryWorkingDayDTO request) {
        logger.info("API updateGroceryWorkingDay was called!");
        return groceryWorkingDayManagementService.updateGroceryWorkingDay(request);
    }
}
