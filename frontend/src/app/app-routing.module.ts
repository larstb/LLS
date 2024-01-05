import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {authGuard} from "./shared/guard/auth/auth.guard";
import {portalUserResolver} from "./core/header/components/resolver/portal-user.resolver";
import {PortalDashboardComponent} from "./portal/portal-dashboard/portal-dashboard.component";
import {adminModGuard} from "./shared/guard/admin-mod/admin-mod.guard";
import {
  groceryWorkingDayResolver
} from "./portal/portal-dashboard/components/grocery-working-day-dashboard/resolver/grocery-working-day.resolver";

const routes: Routes = [
  {
    path: '',
    component: PortalDashboardComponent,
    canActivate: [authGuard],
    resolve: [portalUserResolver, groceryWorkingDayResolver],
  },
  {
    path: 'management',
    loadChildren: () => import('./management/management.module').then(m => m.ManagementModule),
    canActivate: [authGuard, adminModGuard],
    resolve: [portalUserResolver],
  },
  {
    path: 'order',
    loadChildren: () => import('./order/order.module').then(m => m.OrderModule),
    canActivate: [authGuard],
    resolve: [portalUserResolver],
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
