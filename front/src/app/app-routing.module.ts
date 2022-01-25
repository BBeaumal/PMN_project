import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from "./guard/AuthGuard";
import { QuestionnaireComponent } from './questionnaire/questionnaire.component';
import { QuestionComponent } from './question/question.component';
import { AjoutQuestionComponent } from './question/ajout-question/ajout-question.component';
import { ParcoursComponent } from './parcours/parcours.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'surveys', component: QuestionnaireComponent, canActivate: [AuthGuard] },
  { path: 'parcours', component: ParcoursComponent, canActivate: [AuthGuard] },
  { path: 'questions', component: QuestionComponent, canActivate: [AuthGuard] },
  { path: 'questions/create', component: AjoutQuestionComponent, canActivate: [AuthGuard] },
  { path: '**', component: HomeComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
