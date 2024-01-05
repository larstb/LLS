import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {
  AbstractMatDataSourceService
} from "../../../../../shared/service/abstract-material-datasource/abstract-mat-data-source.service";
import {MatDialog} from "@angular/material/dialog";
import {firstValueFrom} from "rxjs";
import {GroceryWorkingDayDetailDTO} from "../../../../../shared/model/groceryWorkingDayDetailDTO";
import {
  GroceryWorkingDayDetailDialogComponent
} from "../../../grocery-working-day-detail-dialog/grocery-working-day-detail-dialog.component";

@Component({
  selector: 'app-grocery-working-day-overview-table',
  templateUrl: './grocery-working-day-overview-table.component.html',
  styleUrl: './grocery-working-day-overview-table.component.css'
})
export class GroceryWorkingDayOverviewTableComponent implements OnInit {

  @ViewChild(MatPaginator, {static: true})
  public matPaginator!: MatPaginator;

  @Input()
  public datasource!: AbstractMatDataSourceService<GroceryWorkingDayDetailDTO>;

  public displayedColumns = ['id', 'date', 'goingUsers', 'payingUser', 'actions'];

  constructor(private matDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.datasource.paginator = this.matPaginator;
  }

  public openDetailDialog(groceryWorkingDay: GroceryWorkingDayDetailDTO): void {
    firstValueFrom(this.matDialog.open(GroceryWorkingDayDetailDialogComponent, {
      data: groceryWorkingDay,
      panelClass: 'overlay',
      autoFocus: false,
    }).afterClosed()).then((res) => {
      if(res) {
        this.datasource.load();
      }
    });
  }

}
