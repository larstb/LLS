import {NgModule} from "@angular/core";
import {SharedModule} from "../shared/shared.module";
import {ManagementRoutingModule} from "./management-routing.module";
import {ManagementDashboardComponent} from "./management-dashboard/management-dashboard.component";

@NgModule({
  imports: [
    SharedModule,
    ManagementRoutingModule
  ],
  declarations: [
    ManagementDashboardComponent
  ],
})
export class ManagementModule {}
