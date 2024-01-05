import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderSummaryBarComponent } from './order-summary-bar.component';

describe('OrderSummaryBarComponent', () => {
  let component: OrderSummaryBarComponent;
  let fixture: ComponentFixture<OrderSummaryBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderSummaryBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrderSummaryBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
