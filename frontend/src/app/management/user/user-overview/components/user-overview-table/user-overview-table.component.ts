import {Component, OnInit, ViewChild} from '@angular/core';
import {UserOverviewTableDataSourceService} from "../../service/user-overview-table-data-source.service";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-user-overview-table',
  templateUrl: './user-overview-table.component.html',
  styleUrl: './user-overview-table.component.css'
})
export class UserOverviewTableComponent implements OnInit {

  @ViewChild(MatPaginator, {static: true})
  public matPaginator!: MatPaginator;

  public displayedColumns = ['id', 'firstname', 'lastname', 'email'];

  constructor(public userOverviewDatasource: UserOverviewTableDataSourceService) {
  }

  ngOnInit(): void {
    this.userOverviewDatasource.paginator = this.matPaginator;
  }

}
