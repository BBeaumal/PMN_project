import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import {RestapiService} from "./restapi.service";
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./token-interceptor";
import {AuthGuard} from "./guard/AuthGuard";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from "@angular/material/input";
import { QuestionnaireComponent } from './questionnaire/questionnaire.component';
import { AjoutQuestionnaireComponent } from './questionnaire/ajout-questionnaire/ajout-questionnaire.component';
import { ListeQuestionnaireComponent } from './questionnaire/liste-questionnaire/liste-questionnaire.component';
import { AjoutUtilisateurComponent } from './home/ajout-utilisateur/ajout-utilisateur.component';
import { ListeUtilisateursComponent } from './home/liste-utilisateurs/liste-utilisateurs.component';
import { MenuComponent } from './menu/menu.component';
import { MatCheckboxModule } from '@angular/material/checkbox';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    QuestionnaireComponent,
    AjoutQuestionnaireComponent,
    ListeQuestionnaireComponent,
    AjoutUtilisateurComponent,
    ListeUtilisateursComponent,
    MenuComponent,
    QuestionComponent,
    AjoutQuestionComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatInputModule
  ],
  providers: [RestapiService, { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
