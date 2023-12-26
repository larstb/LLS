import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {
  AbstractMatDataSourceService
} from "../../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {UserOverviewDTO} from "../../../../../shared/model/userOverviewDTO";

@Component({
  selector: 'app-user-overview-table',
  templateUrl: './user-overview-table.component.html',
  styleUrl: './user-overview-table.component.css'
})
export class UserOverviewTableComponent implements OnInit {

  @ViewChild(MatPaginator, {static: true})
  public matPaginator!: MatPaginator;

  @Input()
  public datasource!: AbstractMatDataSourceService<UserOverviewDTO>;

  public displayedColumns = ['id', 'firstname', 'lastname', 'email'];

  constructor() {
  }

  ngOnInit(): void {
    this.datasource.paginator = this.matPaginator;
  }

}
