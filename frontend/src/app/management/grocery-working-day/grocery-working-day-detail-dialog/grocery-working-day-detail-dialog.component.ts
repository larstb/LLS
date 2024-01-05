import {ChangeDetectionStrategy, Component, Inject, OnInit} from '@angular/core';
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../../shared/store/portal-store-states";
import {firstValueFrom, Observable} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {UserOverviewDTO} from "../../../shared/model/userOverviewDTO";
import {
  GroceryWorkingDayManagementService
} from "../../../shared/service/grocery-working-day-management/grocery-working-day-management.service";
import {GroceryWorkingDayDetailDTO} from "../../../shared/model/groceryWorkingDayDetailDTO";
import {UpdateGroceryWorkingDayDTO} from "../../../shared/model/updateGroceryWorkingDayDTO";
import {DatePipe} from "@angular/common";
import {MatCheckboxChange} from "@angular/material/checkbox";

@Component({
  selector: 'app-grocery-working-day-detail-dialog',
  templateUrl: './grocery-working-day-detail-dialog.component.html',
  styleUrl: './grocery-working-day-detail-dialog.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GroceryWorkingDayDetailDialogComponent implements OnInit {

  @Select(PortalStoreState.users)
  public users$?: Observable<UserOverviewDTO[]>;

  public fg?: FormGroup;
  public minDate = new Date(Date.now());
  public selectedUsersGoingUser?: UserOverviewDTO[] | undefined;
  public selectedPayingUser?: UserOverviewDTO;

  constructor(@Inject(MAT_DIALOG_DATA) public groceryWorkingDay: GroceryWorkingDayDetailDTO,
              private formBuilder: FormBuilder,
              private datePipe: DatePipe,
              private groceryWorkingDayManagementService: GroceryWorkingDayManagementService,
              private matDialogRef: MatDialogRef<GroceryWorkingDayDetailDialogComponent>,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.fg = this.formBuilder.group({
      id: [this.groceryWorkingDay.id],
      version: [this.groceryWorkingDay.version],
      date: [new Date(this.groceryWorkingDay.date || new Date()), [Validators.required]],
    });

    if(this.groceryWorkingDay.date) {
      this.fg.controls['date'].disable();
    }

    this.selectedUsersGoingUser = this.groceryWorkingDay.goingUsers || [];
    this.selectedPayingUser = this.groceryWorkingDay.payingUser;
  }

  public isUserGoingUser(userId: string | undefined): boolean {
    return this.selectedUsersGoingUser?.find(val => val.id === userId) !== undefined;
  }

  public changeGoingUser(user: UserOverviewDTO, $event: MatCheckboxChange): void {
    if ($event.checked) {
      this.selectedUsersGoingUser?.push(user);
    } else {
      this.selectedUsersGoingUser = this.selectedUsersGoingUser?.filter(us => us.id !== user.id);
    }
  }

  public changePayingUser(user: UserOverviewDTO, $event: MatCheckboxChange): void {
    if ($event.checked) {
      this.selectedPayingUser = user;
    } else {
      this.selectedPayingUser = undefined;
    }
  }

  public save(): void {
    const request = { ...this.fg?.getRawValue(),
      date: this.datePipe.transform(this.fg?.getRawValue().date, 'yyyy-MM-dd'),
      payingUserId: this.selectedPayingUser?.id,
      goingUserIds: this.selectedUsersGoingUser?.map(val => val.id) } as UpdateGroceryWorkingDayDTO;

    if(this.groceryWorkingDay.id) {
      firstValueFrom(this.groceryWorkingDayManagementService.updateGroceryWorkingDay(request))
        .then(() => {
            this.matDialogRef.close(true);
            this.toastrService.success('Tag wurde erfolgreich geupdated!', 'Tag update');
          }
        );
    } else {
      firstValueFrom(this.groceryWorkingDayManagementService.createGroceryWorkingDay(request))
        .then(() => {
            this.matDialogRef.close(true);
            this.toastrService.success('Tag wurde erfolgreich angelegt!', 'Tag anlegen');
          }
        );
    }
  }
}
