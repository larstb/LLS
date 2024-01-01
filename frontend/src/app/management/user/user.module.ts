import {NgModule} from "@angular/core";
import {AngularIbanModule} from "angular-iban";
import {UserRoutingModule} from "./user-routing.module";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule} from "@angular/common";
import {UserOverviewComponent} from "./user-overview/user-overview.component";
import {UserOverviewTableComponent} from "./user-overview/components/user-overview-table/user-overview-table.component";
import {UserDetailDialogComponent} from "./user-detail-dialog/user-detail-dialog.component";

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    UserRoutingModule,
    AngularIbanModule
  ],
  declarations: [
    UserOverviewComponent,
    UserOverviewTableComponent,
    UserDetailDialogComponent
  ],
})
export class UserModule {}
