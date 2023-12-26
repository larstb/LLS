import {NgModule} from "@angular/core";
import {SharedModule} from "../shared/shared.module";
import {ManagementRoutingModule} from "./management-routing.module";
import {ManagementDashboardComponent} from "./management-dashboard/management-dashboard.component";
import {
  ManagementDashboardContainerComponent
} from "./management-dashboard/management-dashboard-container/management-dashboard-container.component";

@NgModule({
  imports: [
    SharedModule,
    ManagementRoutingModule,
  ],
  declarations: [
    ManagementDashboardComponent,
    ManagementDashboardContainerComponent
  ],
})
export class ManagementModule {}
