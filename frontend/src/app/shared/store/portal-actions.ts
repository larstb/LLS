export namespace PortalStoreActions {
  export class LoadPortalUser {
    static readonly type = 'Load Portal User'
  }

  export class LoadAllCategoriesForProduct {
    static readonly type = 'Load All Categories for Product'

    constructor(public queryParams?: any) {
    }
  }
}
