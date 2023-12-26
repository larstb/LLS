import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserOverviewTableComponent } from './user-overview-table.component';

describe('UserOverviewTableComponent', () => {
  let component: UserOverviewTableComponent;
  let fixture: ComponentFixture<UserOverviewTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserOverviewTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserOverviewTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
