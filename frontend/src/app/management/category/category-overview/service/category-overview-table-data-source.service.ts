import { Injectable } from '@angular/core';
import {
  AbstractMatDataSourceService
} from "../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../../../shared/shared-model/responseWrapper";
import {UserOverviewDTO} from "../../../../shared/model/userOverviewDTO";
import {CategoryManagementService} from "../../../../shared/service/category-management/category-management.service";
import {CategoryDetailDTO} from "../../../../shared/model/categoryDetailDTO";
import {DatePipe} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class CategoryOverviewTableDataSourceService  extends AbstractMatDataSourceService<CategoryDetailDTO> {

  constructor(datePipe: DatePipe, private categoryManagementService: CategoryManagementService) {
    super(datePipe);
  }

  filter$(queryParams: any): Observable<ResponseWrapper<UserOverviewDTO>> {
    return this.categoryManagementService.loadAllCategories(true, queryParams);
  }
}
