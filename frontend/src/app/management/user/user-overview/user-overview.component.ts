import { Component } from '@angular/core';
import {UserOverviewTableDataSourceService} from "./service/user-overview-table-data-source.service";
import {firstValueFrom} from "rxjs";
import {UserDetailDialogComponent} from "../user-detail-dialog/user-detail-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {CreateUserDTO} from "../../../shared/model/createUserDTO";

@Component({
  selector: 'app-user-overview',
  templateUrl: './user-overview.component.html',
  styleUrl: './user-overview.component.css'
})
export class UserOverviewComponent {

  constructor(public datasource: UserOverviewTableDataSourceService,
              private matDialog: MatDialog) {
  }

  public openCreateDialog(): void {
    firstValueFrom(this.matDialog.open(UserDetailDialogComponent, {
      data: {} as CreateUserDTO,
      panelClass: 'overlay',
      autoFocus: false,
    }).afterClosed()).then((res) => {
      if(res) {
        this.datasource.load();
      }
    });
  }
}
