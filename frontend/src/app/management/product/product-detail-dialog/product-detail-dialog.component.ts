import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {firstValueFrom, Observable} from "rxjs";
import {ProductDetailDTO} from "../../../shared/model/productDetailDTO";
import {ProductManagementService} from "../../../shared/service/product-management/product-management.service";
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../../shared/store/portal-store-states";
import {CategoryOverviewDTO} from "../../../shared/model/categoryOverviewDTO";

@Component({
  selector: 'app-product-detail-dialog',
  templateUrl: './product-detail-dialog.component.html',
  styleUrl: './product-detail-dialog.component.css'
})
export class ProductDetailDialogComponent implements OnInit {

  @Select(PortalStoreState.categories)
  public categories$?: Observable<CategoryOverviewDTO[]>;

  public fg?: FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public product: ProductDetailDTO,
              private formBuilder: FormBuilder,
              private productManagementService: ProductManagementService,
              private matDialogRef: MatDialogRef<ProductDetailDialogComponent>,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.fg = this.formBuilder.group({
      id: [this.product.id],
      version: [this.product.version],
      name: [this.product.name, [Validators.required, Validators.minLength(2), Validators.maxLength(200)]],
      producer: [this.product.producer, [Validators.required, Validators.minLength(2), Validators.maxLength(200)]],
      quantity: [this.product.quantity, [Validators.required, Validators.maxLength(20)]],
      price: [this.product.price || 0, [Validators.required, Validators.min(0)]],
      isActive: [this.product.isActive, []],
      isChecked: [this.product.isChecked, []],
      categoryId: [this.product.category?.id, [Validators.required]],
    });
  }

  public save(): void {
    const request = this.fg?.getRawValue();
    if(this.product.id) {
      firstValueFrom(this.productManagementService.updateProduct(request))
        .then(() => {
            this.matDialogRef.close(true);
            this.toastrService.success('Produkt wurde erfolgreich geupdated!', 'Produkt update');
          }
        );
    } else {
      firstValueFrom(this.productManagementService.createProduct(request))
        .then(() => {
            this.matDialogRef.close(true);
            this.toastrService.success('Produkt wurde erfolgreich angelegt!', 'Produkt anlegen');
          }
        );
    }
  }
}
