import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTermFilterComponent } from './search-term-filter.component';

describe('SearchTermFilterComponent', () => {
  let component: SearchTermFilterComponent;
  let fixture: ComponentFixture<SearchTermFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchTermFilterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchTermFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
