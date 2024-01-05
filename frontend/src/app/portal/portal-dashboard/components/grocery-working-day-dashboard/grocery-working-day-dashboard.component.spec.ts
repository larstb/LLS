import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceryWorkingDayDashboardComponent } from './grocery-working-day-dashboard.component';

describe('GroceryWorkingDayDashboardComponent', () => {
  let component: GroceryWorkingDayDashboardComponent;
  let fixture: ComponentFixture<GroceryWorkingDayDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroceryWorkingDayDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GroceryWorkingDayDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
