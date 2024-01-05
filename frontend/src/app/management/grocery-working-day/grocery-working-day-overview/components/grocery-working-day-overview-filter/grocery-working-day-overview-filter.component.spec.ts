import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceryWorkingDayOverviewFilterComponent } from './grocery-working-day-overview-filter.component';

describe('GroceryWorkingDayOverviewFilterComponent', () => {
  let component: GroceryWorkingDayOverviewFilterComponent;
  let fixture: ComponentFixture<GroceryWorkingDayOverviewFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroceryWorkingDayOverviewFilterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GroceryWorkingDayOverviewFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
