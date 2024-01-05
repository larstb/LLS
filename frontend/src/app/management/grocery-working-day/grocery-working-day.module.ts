import {NgModule} from "@angular/core";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule} from "@angular/common";
import {GroceryWorkingDayRoutingModule} from "./grocery-working-day-routing.module";
import {
  GroceryWorkingDayOverviewComponent
} from "./grocery-working-day-overview/grocery-working-day-overview.component";
import {
  GroceryWorkingDayOverviewTableComponent
} from "./grocery-working-day-overview/components/grocery-working-day-overview-table/grocery-working-day-overview-table.component";
import {
  GroceryWorkingDayOverviewFilterComponent
} from "./grocery-working-day-overview/components/grocery-working-day-overview-filter/grocery-working-day-overview-filter.component";
import {
  GroceryWorkingDayDetailDialogComponent
} from "./grocery-working-day-detail-dialog/grocery-working-day-detail-dialog.component";

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    GroceryWorkingDayRoutingModule
  ],
  declarations: [
    GroceryWorkingDayOverviewComponent,
    GroceryWorkingDayOverviewTableComponent,
    GroceryWorkingDayOverviewFilterComponent,
    GroceryWorkingDayDetailDialogComponent
  ],
})
export class GroceryWorkingDayModule {}
