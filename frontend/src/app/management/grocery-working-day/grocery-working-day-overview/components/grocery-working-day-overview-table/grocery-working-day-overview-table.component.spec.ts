import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceryWorkingDayOverviewTableComponent } from './grocery-working-day-overview-table.component';

describe('GroceryWorkingDayOverviewTableComponent', () => {
  let component: GroceryWorkingDayOverviewTableComponent;
  let fixture: ComponentFixture<GroceryWorkingDayOverviewTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroceryWorkingDayOverviewTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GroceryWorkingDayOverviewTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
