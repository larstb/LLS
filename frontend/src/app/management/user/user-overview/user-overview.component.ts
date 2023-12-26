import { Component } from '@angular/core';
import {UserOverviewTableDataSourceService} from "./service/user-overview-table-data-source.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-user-overview',
  templateUrl: './user-overview.component.html',
  styleUrl: './user-overview.component.css'
})
export class UserOverviewComponent {

  constructor(public datasource: UserOverviewTableDataSourceService, public toastrService: ToastrService) {
  }

  public showNotImplemented(): void {
    this.toastrService.warning("this function is currently not implemented!", "NOT IMPLEMENTED")
  }

}
