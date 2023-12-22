import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import {authGuard} from "./shared/guard/auth.guard";
import {portalUserResolver} from "./core/header/components/resolver/portal-user.resolver";

const routes: Routes = [
  { path: '', component: AppComponent, canActivate: [authGuard], resolve: [portalUserResolver] },
  { path: 'management', loadChildren: () => import('./management/management.module').then(m => m.ManagementModule) },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
