import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {firstValueFrom} from "rxjs";
import {GroceryWorkingDayOverviewFilter} from "../../../shared/shared-model/filter/grocery-working-day-overview-filter";
import {GroceryWorkingDayTableDataSourceService} from "./service/grocery-working-day-table-data-source.service";
import {
  GroceryWorkingDayDetailDialogComponent
} from "../grocery-working-day-detail-dialog/grocery-working-day-detail-dialog.component";
import {CreateGroceryWorkingDayDTO} from "../../../shared/model/createGroceryWorkingDayDTO";

@Component({
  selector: 'app-grocery-working-day-overview',
  templateUrl: './grocery-working-day-overview.component.html',
  styleUrl: './grocery-working-day-overview.component.css'
})
export class GroceryWorkingDayOverviewComponent implements OnInit {

  public filter?: GroceryWorkingDayOverviewFilter = {} as GroceryWorkingDayOverviewFilter;

  constructor(public datasource: GroceryWorkingDayTableDataSourceService,
              private matDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.datasource.filter = this.filter;
  }

  public openCreateDialog(): void {
    firstValueFrom(this.matDialog.open(GroceryWorkingDayDetailDialogComponent, {
      data: {} as CreateGroceryWorkingDayDTO,
      panelClass: 'overlay',
      autoFocus: false,
    }).afterClosed()).then((res) => {
      if(res) {
        this.datasource.load();
      }
    });
  }

  public mergeFilter(filter: GroceryWorkingDayOverviewFilter | undefined): void {
    this.filter = Object.assign({}, this.filter, filter);
  }
}
