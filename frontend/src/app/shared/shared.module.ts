import {NgModule} from "@angular/core";
import {MaterialModule} from "./materialmodule/material.module";
import {ToastrModule} from "ngx-toastr";
import {SearchTermFilterComponent} from "./search-term-filter/search-term-filter.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    MaterialModule,
    ToastrModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    SearchTermFilterComponent,
  ],
  exports: [
    MaterialModule,
    ToastrModule,
    SearchTermFilterComponent,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SharedModule { }
