import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderSideBarComponent } from './order-side-bar.component';

describe('OrderSideBarComponent', () => {
  let component: OrderSideBarComponent;
  let fixture: ComponentFixture<OrderSideBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderSideBarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrderSideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
