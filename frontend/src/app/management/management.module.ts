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
import {CommonModule} from "@angular/common";
import {UserDetailDialogComponent} from "./user/user-detail-dialog/user-detail-dialog.component";
import {AngularIbanModule} from "angular-iban";
import {CategoryOverviewComponent} from "./category/category-overview/category-overview.component";
import {CategoryDetailDialogComponent} from "./category/category-detail-dialog/category-detail-dialog.component";
import {
  CategoryOverviewTableComponent
} from "./category/category-overview/component/category-overview-table/category-overview-table.component";

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    ManagementRoutingModule,
    AngularIbanModule
  ],
  declarations: [
    ManagementDashboardComponent,
    ManagementDashboardContainerComponent,
    UserOverviewComponent,
    UserOverviewTableComponent,
    UserDetailDialogComponent,
    CategoryOverviewComponent,
    CategoryOverviewTableComponent,
    CategoryDetailDialogComponent
  ],
})
export class ManagementModule {}
