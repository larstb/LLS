import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderProductSelectionComponent } from './order-product-selection.component';

describe('OrderProductSelectionComponent', () => {
  let component: OrderProductSelectionComponent;
  let fixture: ComponentFixture<OrderProductSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderProductSelectionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrderProductSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
