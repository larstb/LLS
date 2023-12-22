import { Component } from '@angular/core';
import {Select} from "@ngxs/store";
import {PortalStoreState} from "../../shared/store/portal-store-states";
import {Observable} from "rxjs";
import {PortalUserDTO} from "../../shared/model/portalUserDTO";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  @Select(PortalStoreState.hasAdminRole)
  public hasAdminRole$?: Observable<boolean>;

  @Select(PortalStoreState.hasModRole)
  public hasModRole$?: Observable<boolean>;

  @Select(PortalStoreState.hasUserRole)
  public hasUserRole$?: Observable<boolean>;

  @Select(PortalStoreState.portalUser)
  public portalUser$?: Observable<PortalUserDTO>;

  constructor(private toastrService: ToastrService) {
  }

  public showNotImplemented(): void {
    this.toastrService.warning("this function is currently not implemented!", "NOT IMPLEMENTED")
  }
}
