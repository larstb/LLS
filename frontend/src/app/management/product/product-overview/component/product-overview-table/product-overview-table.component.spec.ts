import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductOverviewTableComponent } from './product-overview-table.component';

describe('ProductOverviewTableComponent', () => {
  let component: ProductOverviewTableComponent;
  let fixture: ComponentFixture<ProductOverviewTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductOverviewTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProductOverviewTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
