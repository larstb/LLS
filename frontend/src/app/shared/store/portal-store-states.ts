import {PortalUserDTO} from "../model/portalUserDTO";
import {Injectable} from "@angular/core";
import {Action, Selector, State, StateContext} from "@ngxs/store";
import {PortalStoreActions} from "./portal-actions";
import {UserService} from "../service/user/user.service";
import {map} from "rxjs/operators";
import {CategoryManagementService} from "../service/category-management/category-management.service";
import LoadAllCategoriesForProduct = PortalStoreActions.LoadAllCategoriesForProduct;
import {CategoryDetailDTO} from "../model/categoryDetailDTO";
import {UserOverviewDTO} from "../model/userOverviewDTO";
import {UserManagementService} from "../service/user-management/user-management.service";
import LoadAllUsersForGroceryWorkingDay = PortalStoreActions.LoadAllUsersForGroceryWorkingDay;
import {GroceryWorkingDayDetailDTO} from "../model/groceryWorkingDayDetailDTO";
import {GroceryWorkingDayService} from "../service/grocery-working-day/grocery-working-day.service";

export interface PortalStoreModel {
  portalUser: PortalUserDTO | null;
  categories: CategoryDetailDTO[] | null;
  users: UserOverviewDTO[] | null;
  groceryWorkingDayForToday: GroceryWorkingDayDetailDTO | null;
}

@State<PortalStoreModel>({
  name: 'portalStore',
  defaults: {
    portalUser: null,
    categories: [],
    users: [],
    groceryWorkingDayForToday: null,
  },
})
@Injectable()
export class PortalStoreState {

  constructor(private userService: UserService,
              private categoryManagementService: CategoryManagementService,
              private userManagementService: UserManagementService,
              private groceryWorkingDayService: GroceryWorkingDayService) {
  }

  @Selector()
  public static portalUser(state: PortalStoreModel): PortalUserDTO | null {
    return state.portalUser;
  }

  @Selector()
  public static hasAdminRole(state: PortalStoreModel): boolean {
    return !!state.portalUser?.roles?.includes('ADMIN');
  }

  @Selector()
  public static hasModRole(state: PortalStoreModel): boolean {
    return !!state.portalUser?.roles?.includes('MOD');
  }

  @Selector()
  public static hasUserRole(state: PortalStoreModel): boolean {
    return !!state.portalUser?.roles?.includes('USER');
  }

  @Selector()
  public static categories(state: PortalStoreModel): CategoryDetailDTO[] | null {
    return state.categories;
  }

  @Selector()
  public static users(state: PortalStoreModel): UserOverviewDTO[] | null {
    return state.users;
  }

  @Selector()
  public static groceryWorkingDayForToday(state: PortalStoreModel): GroceryWorkingDayDetailDTO | null {
    return state.groceryWorkingDayForToday;
  }

  @Action(PortalStoreActions.LoadPortalUser)
  public loadPortalUser(ctx: StateContext<PortalStoreModel>) {
    return this.userService.loadLoggedInUser().pipe(map((portalUser) => ctx.patchState({portalUser})));
  }

  @Action(PortalStoreActions.LoadAllCategoriesForProduct)
  public loadAllCategoriesForProduct(ctx: StateContext<PortalStoreModel>, action: LoadAllCategoriesForProduct) {
    return this.categoryManagementService.loadAllCategories(false, action.queryParams)
      .pipe(map((categories) => ctx.patchState({categories: categories.content})));
  }

  @Action(PortalStoreActions.LoadAllUsersForGroceryWorkingDay)
  public loadAllUsersForGroceryWorkingDay(ctx: StateContext<PortalStoreModel>, action: LoadAllUsersForGroceryWorkingDay) {
    return this.userManagementService.loadAllUsers(false, action.queryParams)
      .pipe(map((users) => ctx.patchState({users: users.content})));
  }

  @Action(PortalStoreActions.LoadGroceryWorkingDayToday)
  public loadGroceryWorkingDayToday(ctx: StateContext<PortalStoreModel>) {
    return this.groceryWorkingDayService.loadOrCreateGroceryWorkingDayToday()
      .pipe(map((groceryWorkingDayForToday) => ctx.patchState({groceryWorkingDayForToday})));
  }

  @Action(PortalStoreActions.SetGroceryWorkingDayToday)
  public setGroceryWorkingDayToday(ctx: StateContext<PortalStoreModel>, action: PortalStoreActions.SetGroceryWorkingDayToday) {
    return ctx.patchState({groceryWorkingDayForToday: action.groceryWorkingDay});
  }
}
