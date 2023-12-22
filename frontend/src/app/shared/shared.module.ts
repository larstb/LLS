import {NgModule} from "@angular/core";
import {MaterialModule} from "./materialmodule/material.module";
import {ToastrModule} from "ngx-toastr";

@NgModule({
  imports: [
    MaterialModule,
    ToastrModule
  ],
  exports: [
    MaterialModule,
    ToastrModule,
  ]
})
export class SharedModule { }
