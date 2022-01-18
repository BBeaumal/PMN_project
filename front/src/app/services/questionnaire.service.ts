import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Questionnaire } from '../models/questionnaire';
import {QuestionService} from "./question.service";

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {
  public questionnaire = {} as Questionnaire;
  isCreation = false;
  isModifier = false;

  constructor(private http: HttpClient, private questionService: QuestionService) { }

  ajouterSurvey() {
    this.isCreation = true;
    this.isModifier = false;
    this.questionnaire = {} as Questionnaire
    this.questionService.afficherQuestions = false;
    this.questionService.afficherReponses = false;
  }
  annuler() {
    this.isCreation = false;
    this.questionService.afficherQuestions = false;
    this.questionService.afficherReponses = false;
  }

  deleteSurvey(questionnaire: Questionnaire) {
    return this.http.get<Questionnaire>("http://localhost:8080/admin/rest/survey/" + questionnaire.idQuestionnaire);
  }

}

