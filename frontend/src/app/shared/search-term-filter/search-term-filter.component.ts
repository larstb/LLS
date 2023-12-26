import {Component, Input} from '@angular/core';
import {SearchTermFilter} from "../shared-model/filter/searchtermFilter";
import {AbstractMatDataSourceService} from "../service/abstract-material-datasource/abstract-mat-data-source.service";

@Component({
  selector: 'app-search-term-filter',
  templateUrl: './search-term-filter.component.html',
  styleUrl: './search-term-filter.component.css'
})
export class SearchTermFilterComponent {

  @Input()
  public filter: SearchTermFilter = {searchTerm: '' };

  @Input()
  public datasource!: AbstractMatDataSourceService<any>;
}
