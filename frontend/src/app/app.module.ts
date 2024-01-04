import {APP_INITIALIZER, DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {ToastrModule} from "ngx-toastr";
import {HeaderModule} from "./core/header/header.module";
import {AppRoutingModule} from "./app-routing.module";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {NgxsModule} from "@ngxs/store";
import {PortalStoreState} from "./shared/store/portal-store-states";
import {CommonModule, registerLocaleData} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {PortalDashboardComponent} from "./portal/portal-dashboard/portal-dashboard.component";
import localePt from '@angular/common/locales/pt';
import {LOCAL_STORAGE_ENGINE, NgxsStoragePluginModule} from "@ngxs/storage-plugin";

export function initializeKeycloak(keycloak: KeycloakService) {
  return () => {
    return new Promise<void>(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: {
            url: 'http://localhost:9005',
            realm: 'lls_dev',
            clientId: 'frontend',
          },
          bearerPrefix: 'Bearer',
          enableBearerInterceptor: true,
          initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false,
          }
        });
        const keycloakAuth = keycloak.getKeycloakInstance();
        keycloakAuth.onTokenExpired = () => keycloak.updateToken();
        resolve();
      } catch (error) {
        reject(error);
      }
    });
  };
}

// Register the localization
registerLocaleData(localePt, 'de-De');

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
    NgxsStoragePluginModule.forRoot({
      key: [
        {
          key: PortalStoreState,
          engine: LOCAL_STORAGE_ENGINE
        }
      ]
    }),
  ],
  declarations: [
    AppComponent,
    PortalDashboardComponent
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    },
    {
      provide: LOCALE_ID,
      useValue: 'de-De'
    },
    {
      provide: DEFAULT_CURRENCY_CODE,
      useValue: 'EUR'
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
