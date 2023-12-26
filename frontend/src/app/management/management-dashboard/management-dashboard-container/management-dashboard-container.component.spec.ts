import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementDashboardContainerComponent } from './management-dashboard-container.component';

describe('ManagementDashboardContainerComponent', () => {
  let component: ManagementDashboardContainerComponent;
  let fixture: ComponentFixture<ManagementDashboardContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagementDashboardContainerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManagementDashboardContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
