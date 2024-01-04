import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {firstValueFrom} from "rxjs";
import {ProductOverviewTableDataSourceService} from "./service/product-overview-table-data-source.service";
import {ProductDetailDialogComponent} from "../product-detail-dialog/product-detail-dialog.component";
import {CreateProductDTO} from "../../../shared/model/createProductDTO";
import {ProductOverviewFilter} from "../../../shared/shared-model/filter/product-overview-filter";

@Component({
  selector: 'app-product-overview',
  templateUrl: './product-overview.component.html',
  styleUrl: './product-overview.component.css'
})
export class ProductOverviewComponent implements OnInit {

  public filter?: ProductOverviewFilter = {isChecked: true, isActive: true};

  constructor(public datasource: ProductOverviewTableDataSourceService,
              private matDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.datasource.filter = this.filter;
  }

  public openCreateDialog(): void {
    firstValueFrom(this.matDialog.open(ProductDetailDialogComponent, {
      data: {} as CreateProductDTO,
      panelClass: 'overlay',
      autoFocus: false,
    }).afterClosed()).then((res) => {
      if(res) {
        this.datasource.load();
      }
    });
  }

  public mergeFilter(filter: ProductOverviewFilter | undefined): void {
    this.filter = Object.assign({}, this.filter, filter);
    console.log('FILTER: ', this.filter);
  }
}
