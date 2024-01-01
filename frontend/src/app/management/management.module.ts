import {NgModule} from "@angular/core";
import {SharedModule} from "../shared/shared.module";
import {ManagementRoutingModule} from "./management-routing.module";
import {ManagementDashboardComponent} from "./management-dashboard/management-dashboard.component";
import {
  ManagementDashboardContainerComponent
} from "./management-dashboard/management-dashboard-container/management-dashboard-container.component";
import {CommonModule} from "@angular/common";
import {AngularIbanModule} from "angular-iban";
import {CategoryModule} from "./category/category.module";
import {UserRoutingModule} from "./user/user-routing.module";

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    ManagementRoutingModule,
    AngularIbanModule,
    CategoryModule,
    UserRoutingModule
  ],
  declarations: [
    ManagementDashboardComponent,
    ManagementDashboardContainerComponent,
  ],
})
export class ManagementModule {}
