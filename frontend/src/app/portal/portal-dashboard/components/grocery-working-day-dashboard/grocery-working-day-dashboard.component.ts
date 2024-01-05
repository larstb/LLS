import { Component } from '@angular/core';
import {Select, Store} from "@ngxs/store";
import {PortalStoreState} from "../../../../shared/store/portal-store-states";
import {firstValueFrom, Observable} from "rxjs";
import {GroceryWorkingDayDetailDTO} from "../../../../shared/model/groceryWorkingDayDetailDTO";
import {PortalUserDTO} from "../../../../shared/model/portalUserDTO";
import {GroceryWorkingDayService} from "../../../../shared/service/grocery-working-day/grocery-working-day.service";
import {PortalStoreActions} from "../../../../shared/store/portal-actions";
import SetGroceryWorkingDayToday = PortalStoreActions.SetGroceryWorkingDayToday;

@Component({
  selector: 'app-grocery-working-day-dashboard',
  templateUrl: './grocery-working-day-dashboard.component.html',
  styleUrl: './grocery-working-day-dashboard.component.css'
})
export class GroceryWorkingDayDashboardComponent {

  @Select(PortalStoreState.groceryWorkingDayForToday)
  public groceryWorkingDayForToday$?: Observable<GroceryWorkingDayDetailDTO>;

  @Select(PortalStoreState.portalUser)
  public portalUser$?: Observable<PortalUserDTO>;

  constructor(private groceryWorkingDayService: GroceryWorkingDayService, private store: Store) {
  }

  public currentUserIsGoingUser(portalUser: PortalUserDTO | null, groceryWorkingDay: GroceryWorkingDayDetailDTO | null): boolean {
    return groceryWorkingDay?.goingUsers?.find(val => val.id === portalUser?.id) !== undefined;
  }

  public addGoingUser(): void {
    firstValueFrom(this.groceryWorkingDayService.addLoggedInUserToGoingUsers())
      .then((result) => this.store.dispatch(new SetGroceryWorkingDayToday(result)));
  }

  public removeGoingUser(): void {
    firstValueFrom(this.groceryWorkingDayService.removeLoggedInUserToGoingUsers())
      .then((result) => this.store.dispatch(new SetGroceryWorkingDayToday(result)));
  }

  public addPayingUser(): void {
    firstValueFrom(this.groceryWorkingDayService.setCurrentLoggedInUserAsPayingUser())
      .then((result) => this.store.dispatch(new SetGroceryWorkingDayToday(result)));
  }

  public removePayingUser(): void {
    firstValueFrom(this.groceryWorkingDayService.removeCurrentLoggedInUserAsPayingUser())
      .then((result) => this.store.dispatch(new SetGroceryWorkingDayToday(result)));
  }

}
