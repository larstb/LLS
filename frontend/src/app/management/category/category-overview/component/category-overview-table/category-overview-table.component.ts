import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {
  AbstractMatDataSourceService
} from "../../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {MatDialog} from "@angular/material/dialog";
import {firstValueFrom} from "rxjs";
import {CategoryManagementService} from "../../../../../shared/service/category-management/category-management.service";
import {CategoryDetailDialogComponent} from "../../../category-detail-dialog/category-detail-dialog.component";
import {CategoryDetailDTO} from "../../../../../shared/model/categoryDetailDTO";

@Component({
  selector: 'app-category-overview-table',
  templateUrl: './category-overview-table.component.html',
  styleUrl: './category-overview-table.component.css'
})
export class CategoryOverviewTableComponent implements OnInit {

  @ViewChild(MatPaginator, {static: true})
  public matPaginator!: MatPaginator;

  @Input()
  public datasource!: AbstractMatDataSourceService<CategoryDetailDTO>;

  public displayedColumns = ['id', 'name', 'description', 'actions'];

  constructor(private matDialog: MatDialog, private categoryManagementService: CategoryManagementService) {
  }

  ngOnInit(): void {
    this.datasource.paginator = this.matPaginator;
  }

  public openDetailDialog(category: CategoryDetailDTO): void {
    firstValueFrom(this.categoryManagementService.loadCategoryById(category.id)).then((result) => {
      firstValueFrom(this.matDialog.open(CategoryDetailDialogComponent, {
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
