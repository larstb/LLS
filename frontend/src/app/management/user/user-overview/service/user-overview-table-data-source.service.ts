import { Injectable } from '@angular/core';
import {UserOverviewDTO} from "../../../../shared/model/userOverviewDTO";
import {
  AbstractMatDataSourceService
} from "../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {Observable} from "rxjs";
import {UserManagementService} from "../../../../shared/service/user-management/user-management.service";
import {ResponseWrapper} from "../../../../shared/shared-model/responseWrapper";

@Injectable({
  providedIn: 'root'
})
export class UserOverviewTableDataSourceService extends AbstractMatDataSourceService<UserOverviewDTO> {

  constructor(private userManagementService: UserManagementService) {
    super();
  }

  filter$(queryParams: any): Observable<ResponseWrapper<UserOverviewDTO>> {
    return this.userManagementService.loadAllUsers(queryParams);
  }
}
