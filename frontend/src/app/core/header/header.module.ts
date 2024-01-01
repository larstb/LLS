import {NgModule} from "@angular/core";
import {HeaderComponent} from "./header.component";
import {LogoHeaderComponent} from "./components/logo-header/logo-header.component";
import {UserHeaderComponent} from "./components/user-header/user-header.component";
import {ManagementHeaderComponent} from "./components/management-header/management-header.component";
import {ProfileHeaderComponent} from "./components/profile-header/profile-header.component";
import {ActionHeaderComponent} from "./components/action-header/action-header.component";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
  ],
  declarations: [
    HeaderComponent,
    LogoHeaderComponent,
    ActionHeaderComponent,
    UserHeaderComponent,
    ManagementHeaderComponent,
    ProfileHeaderComponent
  ],
  exports: [
    HeaderComponent
  ]
})
export class HeaderModule {}
