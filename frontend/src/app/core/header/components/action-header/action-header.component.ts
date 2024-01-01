import { Component } from '@angular/core';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-action-header',
  templateUrl: './action-header.component.html',
  styleUrl: './action-header.component.css'
})
export class ActionHeaderComponent {

  constructor(private toastrService: ToastrService) {
  }

  public showNotImplemented(): void {
    this.toastrService.warning("this function is currently not implemented!", "NOT IMPLEMENTED")
  }
}
