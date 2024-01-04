import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductOverviewFilterComponent } from './product-overview-filter.component';

describe('ProductOverviewFilterComponent', () => {
  let component: ProductOverviewFilterComponent;
  let fixture: ComponentFixture<ProductOverviewFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductOverviewFilterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProductOverviewFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
