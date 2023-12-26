import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-management-dashboard-container',
  templateUrl: './management-dashboard-container.component.html',
  styleUrl: './management-dashboard-container.component.css'
})
export class ManagementDashboardContainerComponent {

  @Input()
  public title?: string;

  @Input()
  public iconName?: string;

  @Input()
  public url?: string[];

}
