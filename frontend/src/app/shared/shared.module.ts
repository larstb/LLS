import {NgModule} from "@angular/core";
import {MaterialModule} from "./materialmodule/material.module";
import {ToastrModule} from "ngx-toastr";
import {SearchTermFilterComponent} from "./search-term-filter/search-term-filter.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    MaterialModule,
    ToastrModule,
    FormsModule,
  ],
  declarations: [
    SearchTermFilterComponent,
  ],
  exports: [
    MaterialModule,
    ToastrModule,
    SearchTermFilterComponent,
    FormsModule,
  ]
})
export class SharedModule { }
