import {APP_INITIALIZER, NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {ToastrModule} from "ngx-toastr";
import {HeaderModule} from "./core/header/header.module";
import {AppRoutingModule} from "./app-routing.module";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {NgxsModule} from "@ngxs/store";
import {PortalStoreState} from "./shared/store/portal-store-states";
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";

export function initializeKeycloak(
  keycloak: KeycloakService
) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:9005',
        realm: 'lls_dev',
        clientId: 'frontend',
      },
      bearerPrefix: 'Bearer',
      enableBearerInterceptor: true,
      initOptions: {
        checkLoginIframe: false,
      }
    });
}

@NgModule({
  imports: [
    CommonModule,
    HeaderModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
      progressBar: true,
    }),
    AppRoutingModule,
    KeycloakAngularModule,
    NgxsModule.forRoot([PortalStoreState], {
      developmentMode: true,
    }),
  ],
  declarations: [
    AppComponent
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
