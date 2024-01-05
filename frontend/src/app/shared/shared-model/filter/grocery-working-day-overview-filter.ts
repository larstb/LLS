import {SearchTermFilter} from "./searchtermFilter";

export interface GroceryWorkingDayOverviewFilter extends SearchTermFilter {
    goingUserId?: string;
    payingUserId?: string;
    date?: string | Date;
}
