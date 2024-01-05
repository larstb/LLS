import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../../../shared/shared-model/responseWrapper";
import {UserOverviewDTO} from "../../../../shared/model/userOverviewDTO";
import {
  GroceryWorkingDayManagementService
} from "../../../../shared/service/grocery-working-day-management/grocery-working-day-management.service";
import {
  AbstractMatDataSourceService
} from "../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {GroceryWorkingDayDetailDTO} from "../../../../shared/model/groceryWorkingDayDetailDTO";
import {DatePipe} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class GroceryWorkingDayTableDataSourceService extends AbstractMatDataSourceService<GroceryWorkingDayDetailDTO> {

  constructor(datePipe: DatePipe, private groceryWorkingDayService: GroceryWorkingDayManagementService) {
    super(datePipe);
  }

  filter$(queryParams: any): Observable<ResponseWrapper<UserOverviewDTO>> {
    return this.groceryWorkingDayService.loadAllGroceryWorkingDays(queryParams);
  }
}
