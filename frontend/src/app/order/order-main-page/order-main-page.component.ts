import {Component, OnInit} from '@angular/core';
import {OrderProcessService} from "../../shared/service/order-process/order-process.service";
import {OrderProcessDataSourceService} from "../service/order-process-data-source.service";

@Component({
  selector: 'app-order-main-page',
  templateUrl: './order-main-page.component.html',
  styleUrl: './order-main-page.component.css'
})
export class OrderMainPageComponent implements OnInit {

  constructor(private orderProcessService: OrderProcessService,
              public productDataSource: OrderProcessDataSourceService) {
  }

  ngOnInit(): void {
  }

}
