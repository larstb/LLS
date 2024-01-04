import {Component, EventEmitter, Input, Output} from '@angular/core';
import {SearchTermFilter} from "../shared-model/filter/searchtermFilter";

@Component({
  selector: 'app-search-term-filter',
  templateUrl: './search-term-filter.component.html',
  styleUrl: './search-term-filter.component.css'
})
export class SearchTermFilterComponent {

  @Input()
  public filter: SearchTermFilter = { searchTerm: '' } as SearchTermFilter;

  @Output()
  public filterChange: EventEmitter<SearchTermFilter> = new EventEmitter<SearchTermFilter>();
}
