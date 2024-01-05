import { Component } from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../../../shared/store/portal-store-states";
import {Observable} from "rxjs";
import {GroceryWorkingDayDetailDTO} from "../../../../shared/model/groceryWorkingDayDetailDTO";

@Component({
  selector: 'app-action-header',
  templateUrl: './action-header.component.html',
  styleUrl: './action-header.component.css'
})
export class ActionHeaderComponent {

  @Select(PortalStoreState.groceryWorkingDayForToday)
  public groceryWorkingDayForTay$?: Observable<GroceryWorkingDayDetailDTO>;

  constructor(private toastrService: ToastrService) {
  }

  public showNotImplemented(): void {
    this.toastrService.warning("this function is currently not implemented!", "NOT IMPLEMENTED")
  }
}
