import {PortalUserDTO} from "../model/portalUserDTO";
import {Injectable} from "@angular/core";
import {Action, Selector, State, StateContext} from "@ngxs/store";
import {PortalStoreActions} from "./portal-actions";
import {UserService} from "../service/user/user.service";
import {map} from "rxjs/operators";
import {CategoryManagementService} from "../service/category-management/category-management.service";
import LoadAllCategoriesForProduct = PortalStoreActions.LoadAllCategoriesForProduct;
import {CategoryDetailDTO} from "../model/categoryDetailDTO";

export interface PortalStoreModel {
  portalUser: PortalUserDTO | null;
  categories: CategoryDetailDTO[] | null;
}

@State<PortalStoreModel>({
  name: 'portalStore',
  defaults: {
    portalUser: null,
    categories: []
  },
})
@Injectable()
export class PortalStoreState {

  constructor(private userService: UserService, private categoryManagementService: CategoryManagementService) {
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

  @Action(PortalStoreActions.LoadPortalUser)
  public loadPortalUser(ctx: StateContext<PortalStoreModel>) {
    return this.userService.loadLoggedInUser().pipe(map((portalUser) => ctx.patchState({portalUser})));
  }

  @Action(PortalStoreActions.LoadAllCategoriesForProduct)
  public loadAllCategoriesForProduct(ctx: StateContext<PortalStoreModel>, action: LoadAllCategoriesForProduct) {
    return this.categoryManagementService.loadAllCategories(false, action.queryParams)
      .pipe(map((categories) => ctx.patchState({categories: categories.content})));
  }
}
