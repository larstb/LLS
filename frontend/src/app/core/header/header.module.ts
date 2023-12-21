import {NgModule} from "@angular/core";
import {HeaderComponent} from "./header.component";
import {CommonModule} from "@angular/common";
import {LogoHeaderComponent} from "./components/logo-header/logo-header.component";
import {UserHeaderComponent} from "./components/user-header/user-header.component";
import {ManagementHeaderComponent} from "./components/management-header/management-header.component";
import {ProfileHeaderComponent} from "./components/profile-header/profile-header.component";
import {MaterialModule} from "../../shared/materialmodule/material.module";
import {ActionHeaderComponent} from "./components/action-header/action-header.component";

@NgModule({
  imports: [
    CommonModule,
    MaterialModule
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
