import { Injectable } from '@angular/core';
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject, catchError, finalize, Observable, of} from "rxjs";
import {MatPaginator} from "@angular/material/paginator";
import {ResponseWrapper} from "../../shared-model/responseWrapper";
import {DatePipe} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractMatDataSourceService<T> extends DataSource<T>{

  private _dataSubject = new BehaviorSubject<T[]>([]);
  private _loadingSubject = new BehaviorSubject<boolean>(false);
  private _response?: ResponseWrapper<T>;
  private _paginator?: MatPaginator;
  private _page: number = 0;
  private _pageSize: number = 10;
  private _filter: any;

  protected constructor(public datePipe: DatePipe) {
    super();
  }

  public set paginator(matPaginator: MatPaginator) {
    this._paginator = matPaginator;
    this._paginator.pageIndex = this._page;
    this._paginator.pageSize = this._pageSize;
    this._paginator.page.subscribe((newPage) => {
      this._page = newPage.pageIndex;
      this._pageSize = newPage.pageSize;
      this.load();
    });
    this.load();
  }

  public set filter(filter: any) {
    this._filter = filter;
  }

  public get response(): ResponseWrapper<T> | undefined {
    return this._response;
  }

  connect(collectionViewer: CollectionViewer): Observable<T[]> {
    return this._dataSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    // don't disconnect
  }

  load(filter?: any) {
    this._filter = filter ? filter : this._filter;
    const queryParams = {...this.prepareRequest(this._filter), page: this._page, pageSize: this._pageSize};
    this._loadingSubject.next(true);
    this.filter$(queryParams)
    .pipe(
      catchError(() => of({content: [{} as T], totalElements: 0} as ResponseWrapper<T>)),
      finalize(() => this._loadingSubject.next(false)),
    )
    .subscribe((result) => {
      this._dataSubject.next(result?.content || []);
      this._response = result;
      if(this._paginator) {
        this._paginator.length = this.response?.totalElements;
      }
    });
  }

  public abstract filter$(queryParams: any): Observable<ResponseWrapper<T>>;

  private prepareRequest(filter: any = {}): any {
    return Object.fromEntries(
      Object.entries(filter)
        .filter(([_, v]) => v !== null)
        .filter(([_, v]) => typeof(v) === 'string' ? v.trim() !== '' : true)
        .map(([_, v]) => v instanceof Date ? [_, this.datePipe.transform(v, 'yyyy-MM-dd')] : [_, v])
    );
  }
}
