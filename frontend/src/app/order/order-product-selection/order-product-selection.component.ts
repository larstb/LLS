import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {
  AbstractMatDataSourceService
} from "../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {ProductDetailDTO} from "../../shared/model/productDetailDTO";

@Component({
  selector: 'app-order-product-selection',
  templateUrl: './order-product-selection.component.html',
  styleUrl: './order-product-selection.component.css'
})
export class OrderProductSelectionComponent implements OnInit {

  @Input()
  public dataSource!: AbstractMatDataSourceService<ProductDetailDTO>;

  @ViewChild(MatPaginator, {static: true})
  public matPaginator!: MatPaginator;

  ngOnInit(): void {
    this.dataSource.paginator = this.matPaginator;
  }

}
