import {PortalUserDTO} from "../model/portalUserDTO";
import {Injectable} from "@angular/core";
import {Action, Selector, State, StateContext} from "@ngxs/store";
import {PortalStoreActions} from "./portal-actions";
import {UserService} from "../service/user.service";
import {map} from "rxjs/operators";

export interface PortalStoreModel {
  portalUser: PortalUserDTO | null;
}

@State<PortalStoreModel>({
  name: 'portalStore',
  defaults: {
    portalUser: null,
  },
})
@Injectable()
export class PortalStoreState {

  constructor(private userService: UserService) {
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

  @Action(PortalStoreActions.LoadPortalUser)
  public loadPortalUser(ctx: StateContext<PortalStoreModel>) {
    return this.userService.loadLoggedInUser().pipe(map((portalUser) => ctx.patchState({portalUser})));
  }
}
