package at.ltb.apprenticedeliverysystem.core.groceryworkingday.controller;

import at.ltb.apprenticedeliverysystem.configuration.permission.ModeratorOrAdminPermission;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.api.GroceryWorkingDayManagementService;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.api.UserGroceryWorkingDayManagementResource;
import at.ltb.apprenticedeliverysystem.core.groceryworkingday.dto.GroceryWorkingDayDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grocery-working-day")
@Transactional(readOnly = true)
@ModeratorOrAdminPermission
public class UserGroceryWorkingDayManagementResourceImpl implements UserGroceryWorkingDayManagementResource {

    private final Logger logger = LoggerFactory.getLogger(UserGroceryWorkingDayManagementResourceImpl.class);

    private final GroceryWorkingDayManagementService groceryWorkingDayManagementService;

    public UserGroceryWorkingDayManagementResourceImpl(
            GroceryWorkingDayManagementService groceryWorkingDayManagementService) {
        logger.info("API loadAllGroceryWorkingDays was called!");
        this.groceryWorkingDayManagementService = groceryWorkingDayManagementService;
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO loadOrCreateGroceryWorkingDayToday() {
        logger.info("API loadOrCreateGroceryWorkingDayToday was called!");
        return groceryWorkingDayManagementService.loadOrCreateGroceryWorkingDayToday();
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO addLoggedInUserToGoingUsers() {
        logger.info("API addLoggedInUserToGoingUsers was called!");
        return groceryWorkingDayManagementService.addLoggedInUserToGoingUsers();
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO removeLoggedInUserToGoingUsers() {
        logger.info("API removeLoggedInUserToGoingUsers was called!");
        return groceryWorkingDayManagementService.removeLoggedInUserToGoingUsers();
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO setCurrentLoggedInUserAsPayingUser() {
        logger.info("API setCurrentLoggedInUserAsPayingUser was called!");
        return groceryWorkingDayManagementService.setCurrentLoggedInUserAsPayingUser();
    }

    @Override
    @Transactional
    public GroceryWorkingDayDetailDTO removeCurrentLoggedInUserAsPayingUser() {
        logger.info("API removeCurrentLoggedInUserAsPayingUser was called!");
        return groceryWorkingDayManagementService.removeCurrentLoggedInUserAsPayingUser();
    }
}
