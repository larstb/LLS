import { Component } from '@angular/core';
import {UserOverviewTableDataSourceService} from "./service/user-overview-table-data-source.service";

@Component({
  selector: 'app-user-overview',
  templateUrl: './user-overview.component.html',
  styleUrl: './user-overview.component.css'
})
export class UserOverviewComponent {

  constructor(public datasource: UserOverviewTableDataSourceService) {
  }

}
