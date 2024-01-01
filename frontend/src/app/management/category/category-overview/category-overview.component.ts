import {Component} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {firstValueFrom} from "rxjs";
import {CategoryOverviewTableDataSourceService} from "./service/category-overview-table-data-source.service";
import {CreateCategoryDTO} from "../../../shared/model/createCategoryDTO";
import {CategoryDetailDialogComponent} from "../category-detail-dialog/category-detail-dialog.component";

@Component({
  selector: 'app-category-overview',
  templateUrl: './category-overview.component.html',
  styleUrl: './category-overview.component.css'
})
export class CategoryOverviewComponent {
  constructor(public datasource: CategoryOverviewTableDataSourceService,
              private matDialog: MatDialog) {
  }

  public openCreateDialog(): void {
    firstValueFrom(this.matDialog.open(CategoryDetailDialogComponent, {
      data: {} as CreateCategoryDTO,
      panelClass: 'overlay',
      autoFocus: false,
    }).afterClosed()).then((res) => {
      if(res) {
        this.datasource.load();
      }
    });
  }
}
