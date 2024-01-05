import { TestBed } from '@angular/core/testing';

import { OrderProcessDataSourceService } from './order-process-data-source.service';

describe('OrderProcessDataSourceService', () => {
  let service: OrderProcessDataSourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderProcessDataSourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
