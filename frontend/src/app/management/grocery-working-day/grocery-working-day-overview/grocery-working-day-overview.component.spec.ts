import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceryWorkingDayOverviewComponent } from './grocery-working-day-overview.component';

describe('GroceryWorkingDayOverviewComponent', () => {
  let component: GroceryWorkingDayOverviewComponent;
  let fixture: ComponentFixture<GroceryWorkingDayOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroceryWorkingDayOverviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GroceryWorkingDayOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
