import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryOverviewTableComponent } from './category-overview-table.component';

describe('CategoryOverviewTableComponent', () => {
  let component: CategoryOverviewTableComponent;
  let fixture: ComponentFixture<CategoryOverviewTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryOverviewTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CategoryOverviewTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
