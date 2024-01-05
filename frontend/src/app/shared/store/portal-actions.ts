import {GroceryWorkingDayDetailDTO} from "../model/groceryWorkingDayDetailDTO";

export namespace PortalStoreActions {
  export class LoadPortalUser {
    static readonly type = 'Load Portal User'
  }

  export class LoadGroceryWorkingDayToday {
    static readonly type = 'Load GroceryWorkingDay for today'
  }

  export class SetGroceryWorkingDayToday {
    static readonly type = 'Set GroceryWorkingDay for today'

    constructor(public groceryWorkingDay?: GroceryWorkingDayDetailDTO) {
    }
  }

  export class LoadAllCategoriesForProduct {
    static readonly type = 'Load All Categories for Product'

    constructor(public queryParams?: any) {
    }
  }

  export class LoadAllUsersForGroceryWorkingDay {
    static readonly type = 'Load All Users for Grocery Working Day'

    constructor(public queryParams?: any) {
    }
  }
}
