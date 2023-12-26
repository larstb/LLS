import {NgModule} from "@angular/core";
import {SharedModule} from "../shared/shared.module";
import {ManagementRoutingModule} from "./management-routing.module";
import {ManagementDashboardComponent} from "./management-dashboard/management-dashboard.component";
import {
  ManagementDashboardContainerComponent
} from "./management-dashboard/management-dashboard-container/management-dashboard-container.component";
import {UserOverviewComponent} from "./user/user-overview/user-overview.component";
import {
  UserOverviewTableComponent
} from "./user/user-overview/components/user-overview-table/user-overview-table.component";
import {NgIf} from "@angular/common";

@NgModule({
  imports: [
    SharedModule,
    ManagementRoutingModule,
    NgIf,
  ],
  declarations: [
    ManagementDashboardComponent,
    ManagementDashboardContainerComponent,
    UserOverviewComponent,
    UserOverviewTableComponent
  ],
})
export class ManagementModule {}
