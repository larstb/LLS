import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {
  AbstractMatDataSourceService
} from "../../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {MatDialog} from "@angular/material/dialog";
import {firstValueFrom} from "rxjs";
import {ProductOverviewDTO} from "../../../../../shared/model/productOverviewDTO";
import {ProductManagementService} from "../../../../../shared/service/product-management/product-management.service";
import {ProductDetailDialogComponent} from "../../../product-detail-dialog/product-detail-dialog.component";

@Component({
  selector: 'app-product-overview-table',
  templateUrl: './product-overview-table.component.html',
  styleUrl: './product-overview-table.component.css',
})
export class ProductOverviewTableComponent implements OnInit {

  @ViewChild(MatPaginator, {static: true})
  public matPaginator!: MatPaginator;

  @Input()
  public datasource!: AbstractMatDataSourceService<ProductOverviewDTO>;

  public displayedColumns = ['id', 'name', 'price', 'active', 'checked', 'actions'];

  constructor(private matDialog: MatDialog, private productManagementService: ProductManagementService) {
  }

  ngOnInit(): void {
    this.datasource.paginator = this.matPaginator;
  }

  public openDetailDialog(product: ProductOverviewDTO): void {
    firstValueFrom(this.productManagementService.loadProductById(product.id)).then((result) => {
      firstValueFrom(this.matDialog.open(ProductDetailDialogComponent, {
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
