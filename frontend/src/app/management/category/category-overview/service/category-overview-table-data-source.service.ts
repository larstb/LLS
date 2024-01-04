import { Injectable } from '@angular/core';
import {
  AbstractMatDataSourceService
} from "../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {CategoryOverviewDTO} from "../../../../shared/model/categoryOverviewDTO";
import {Observable} from "rxjs";
import {ResponseWrapper} from "../../../../shared/shared-model/responseWrapper";
import {UserOverviewDTO} from "../../../../shared/model/userOverviewDTO";
import {CategoryManagementService} from "../../../../shared/service/category-management/category-management.service";

@Injectable({
  providedIn: 'root'
})
export class CategoryOverviewTableDataSourceService  extends AbstractMatDataSourceService<CategoryOverviewDTO>{

  constructor(private categoryManagementService: CategoryManagementService) {
    super();
  }

  filter$(queryParams: any): Observable<ResponseWrapper<UserOverviewDTO>> {
    return this.categoryManagementService.loadAllCategories(true, queryParams);
  }
}
