import {Component, Input} from '@angular/core';
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../../../shared/store/portal-store-states";
import {Observable} from "rxjs";
import {PortalUserDTO} from "../../../../shared/model/portalUserDTO";
import {ToastrService} from "ngx-toastr";
import {MatSidenav} from "@angular/material/sidenav";

@Component({
  selector: 'app-profile-header',
  templateUrl: './profile-header.component.html',
  styleUrl: './profile-header.component.css'
})
export class ProfileHeaderComponent {

  @Select(PortalStoreState.portalUser)
  public portalUser$?: Observable<PortalUserDTO>;

  constructor(private toastrService: ToastrService) {
  }

  public showNotImplemented(): void {
    this.toastrService.warning("this function is currently not implemented!", "NOT IMPLEMENTED")
  }
}
