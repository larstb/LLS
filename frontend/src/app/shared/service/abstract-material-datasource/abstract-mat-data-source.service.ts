import { Injectable } from '@angular/core';
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject, catchError, finalize, Observable, of, Subscription} from "rxjs";
import {MatPaginator} from "@angular/material/paginator";
import {ResponseWrapper} from "../../shared-model/responseWrapper";

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractMatDataSourceService<T> extends DataSource<T>{

  private _dataSubject = new BehaviorSubject<T[]>([]);
  private _loadingSubject = new BehaviorSubject<boolean>(false);
  private _subscriptions: Subscription[] = [];
  private _response?: ResponseWrapper<T>;
  private _paginator?: MatPaginator;
  private _page: number = 0;
  private _pageSize: number = 10;

  protected constructor() {
    super();
  }

  public set paginator(matPaginator: MatPaginator) {
    this._paginator = matPaginator;
    this._paginator.pageIndex = this._page;
    this._paginator.pageSize = this._pageSize;
    this._subscriptions.push(this._paginator.page.subscribe((newPage) => {
      this._page = newPage.pageIndex;
      this._pageSize = newPage.pageSize;
      this.load(this._page, this._pageSize);
    }));
    this.load(this._page, this._pageSize);
  }

  public get response(): ResponseWrapper<T> | undefined {
    return this._response;
  }

  connect(collectionViewer: CollectionViewer): Observable<T[]> {
    return this._dataSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this._dataSubject.complete();
    this._loadingSubject.complete();
    this._subscriptions.forEach((sub) => sub.unsubscribe());
  }

  load(page: number, pageSize: number, filter?: any) {
    const queryParams = {...filter, page: page ? page : this._page, pageSize: pageSize ? pageSize : this._pageSize};
    this._loadingSubject.next(true);
    this._subscriptions.push(
      this.filter$(queryParams)
      .pipe(
        catchError(() => of({content: [{} as T], totalElements: 0} as ResponseWrapper<T>)),
        finalize(() => this._loadingSubject.next(false)),
      )
      .subscribe((result) => {
        this._dataSubject.next(result?.content || []);
        this._response = result;
      })
    );
  }

  public abstract filter$(queryParams: any): Observable<ResponseWrapper<T>>;
}
