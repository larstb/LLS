import {Component, EventEmitter, Input, Output} from '@angular/core';
import {
  AbstractMatDataSourceService
} from "../../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {GroceryWorkingDayDetailDTO} from "../../../../../shared/model/groceryWorkingDayDetailDTO";
import {
  GroceryWorkingDayOverviewFilter
} from "../../../../../shared/shared-model/filter/grocery-working-day-overview-filter";
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../../../../shared/store/portal-store-states";
import {Observable} from "rxjs";
import {UserOverviewDTO} from "../../../../../shared/model/userOverviewDTO";

@Component({
  selector: 'app-grocery-working-day-overview-filter',
  templateUrl: './grocery-working-day-overview-filter.component.html',
  styleUrl: './grocery-working-day-overview-filter.component.css'
})
export class GroceryWorkingDayOverviewFilterComponent {

  @Select(PortalStoreState.users)
  public users$?: Observable<UserOverviewDTO[]>;

  @Input()
  public datasource!: AbstractMatDataSourceService<GroceryWorkingDayDetailDTO>;

  @Output()
  public filterChange: EventEmitter<GroceryWorkingDayOverviewFilter> = new EventEmitter<GroceryWorkingDayOverviewFilter>();
}
