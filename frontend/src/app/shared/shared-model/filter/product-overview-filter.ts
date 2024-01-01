import {SearchTermFilter} from "./searchtermFilter";

export interface ProductOverviewFilter extends SearchTermFilter{
    categoryId?: string;
    isChecked?: boolean;
    isActive?: boolean;
}
