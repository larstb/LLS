import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceryWorkingDayDetailDialogComponent } from './grocery-working-day-detail-dialog.component';

describe('GroceryWorkingDayDetailDialogComponent', () => {
  let component: GroceryWorkingDayDetailDialogComponent;
  let fixture: ComponentFixture<GroceryWorkingDayDetailDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroceryWorkingDayDetailDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GroceryWorkingDayDetailDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
