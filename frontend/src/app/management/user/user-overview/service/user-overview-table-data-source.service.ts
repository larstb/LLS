import { Injectable } from '@angular/core';
import {UserOverviewDTO} from "../../../../shared/model/userOverviewDTO";
import {
  AbstractMatDataSourceService
} from "../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {Observable} from "rxjs";
import {UserManagementService} from "../../../../shared/service/user-management/user-management.service";
import {ResponseWrapper} from "../../../../shared/shared-model/responseWrapper";
import {DatePipe} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class UserOverviewTableDataSourceService extends AbstractMatDataSourceService<UserOverviewDTO> {

  constructor(datePipe: DatePipe, private userManagementService: UserManagementService) {
    super(datePipe);
  }

  filter$(queryParams: any): Observable<ResponseWrapper<UserOverviewDTO>> {
    return this.userManagementService.loadAllUsers(true, queryParams);
  }
}
