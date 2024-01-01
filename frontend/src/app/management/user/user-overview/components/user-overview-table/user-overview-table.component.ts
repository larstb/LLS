import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {
  AbstractMatDataSourceService
} from "../../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {UserOverviewDTO} from "../../../../../shared/model/userOverviewDTO";
import {MatDialog} from "@angular/material/dialog";
import {UserDetailDialogComponent} from "../../../user-detail-dialog/user-detail-dialog.component";
import {UserManagementService} from "../../../../../shared/service/user-management/user-management.service";
import {firstValueFrom} from "rxjs";

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

  public displayedColumns = ['id', 'firstname', 'lastname', 'email', 'actions'];

  constructor(private matDialog: MatDialog, private userManagementService: UserManagementService) {
  }

  ngOnInit(): void {
    this.datasource.paginator = this.matPaginator;
  }

  public openDetailDialog(user: UserOverviewDTO): void {
    firstValueFrom(this.userManagementService.loadUserById(user.id)).then((result) => {
      firstValueFrom(this.matDialog.open(UserDetailDialogComponent, {
        data: result,
        panelClass: 'overlay',
        autoFocus: false,
      }).afterClosed()).then((res) => {
        if(res) {
          this.datasource.load();
        }
      });
    });
  }

}
