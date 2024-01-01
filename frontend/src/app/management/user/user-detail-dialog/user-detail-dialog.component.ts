import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {UserDetailDTO} from "../../../shared/model/userDetailDTO";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import RolesEnum = UserDetailDTO.RolesEnum;
import {MatCheckboxChange} from "@angular/material/checkbox";
import {UserManagementService} from "../../../shared/service/user-management/user-management.service";
import {firstValueFrom} from "rxjs";
import {ToastrService} from "ngx-toastr";
import {ValidatorService} from "angular-iban";

@Component({
  selector: 'app-user-detail-dialog',
  templateUrl: './user-detail-dialog.component.html',
  styleUrl: './user-detail-dialog.component.css'
})
export class UserDetailDialogComponent implements OnInit {

  public fg?: FormGroup;
  public selectedRoles: RolesEnum[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public user: UserDetailDTO,
              private formBuilder: FormBuilder,
              private userManagementService: UserManagementService,
              private matDialogRef: MatDialogRef<UserDetailDialogComponent>,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.fg = this.formBuilder.group({
      id: [this.user.id],
      version: [this.user.version],
      firstname: [this.user.firstname, [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      lastname: [this.user.lastname, [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      status: [this.user.status, [Validators.maxLength(100)]],
      location: [this.user.location, [Validators.maxLength(100)]],
      phoneNumber: [this.user.phoneNumber, [Validators.maxLength(255)]],
      iban: [this.user.iban, [ValidatorService.validateIban]],
      paypalLink: [this.user.paypalLink, [Validators.minLength(10), Validators.maxLength(255)]],
      email: [this.user.email, [Validators.required, Validators.email]],
      enabled: [this.user.enabled]
    });
    this.selectedRoles = this.user.roles || [];
  }

  public changeRole(role: RolesEnum, $event: MatCheckboxChange): void {
    if ($event.checked) {
      this.selectedRoles.push(role);
    } else {
      this.selectedRoles = this.selectedRoles.filter(role => role !== role);
    }
  }

  public save(): void {
    const request = { ...this.fg?.getRawValue(), roles: this.selectedRoles};
    if(this.user.id) {
      firstValueFrom(this.userManagementService.updateUser(request))
        .then(() => {
          this.matDialogRef.close(true);
          this.toastrService.success('User wurde erfolgreich geupdated!', 'User update');
        }
      );
    } else {
      firstValueFrom(this.userManagementService.createUser(request))
        .then(() => {
          this.matDialogRef.close(true);
          this.toastrService.success('User wurde erfolgreich angelegt!', 'User anlegen');
        }
      );
    }
  }
}
