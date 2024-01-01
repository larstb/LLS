import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {firstValueFrom} from "rxjs";
import {CategoryManagementService} from "../../../shared/service/category-management/category-management.service";
import {CategoryDetailDTO} from "../../../shared/model/categoryDetailDTO";

@Component({
  selector: 'app-category-detail-dialog',
  templateUrl: './category-detail-dialog.component.html',
  styleUrl: './category-detail-dialog.component.css'
})
export class CategoryDetailDialogComponent implements OnInit {

  public fg?: FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public category: CategoryDetailDTO,
              private formBuilder: FormBuilder,
              private categoryManagementService: CategoryManagementService,
              private matDialogRef: MatDialogRef<CategoryDetailDialogComponent>,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.fg = this.formBuilder.group({
      id: [this.category.id],
      version: [this.category.version],
      name: [this.category.name, [Validators.required, Validators.minLength(2), Validators.maxLength(200)]],
      description: [this.category.description, [Validators.minLength(2), Validators.maxLength(100)]],
    });
  }

  public save(): void {
    const request = this.fg?.getRawValue();
    if(this.category.id) {
      firstValueFrom(this.categoryManagementService.updateCategory(request))
        .then(() => {
            this.matDialogRef.close(true);
            this.toastrService.success('Kategorie wurde erfolgreich geupdated!', 'Kategorie update');
          }
        );
    } else {
      firstValueFrom(this.categoryManagementService.createCategory(request))
        .then(() => {
            this.matDialogRef.close(true);
            this.toastrService.success('Kategorie wurde erfolgreich angelegt!', 'Kategorie anlegen');
          }
        );
    }
  }
}
