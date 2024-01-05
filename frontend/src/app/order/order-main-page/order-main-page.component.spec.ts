import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderMainPageComponent } from './order-main-page.component';

describe('OrderMainPageComponent', () => {
  let component: OrderMainPageComponent;
  let fixture: ComponentFixture<OrderMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderMainPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrderMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
