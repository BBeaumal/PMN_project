import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Questionnaire } from '../models/questionnaire';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {
  private questionnaire = {} as Questionnaire;
  isCreation = false;

  constructor(private http: HttpClient) { }

  ajouterSurvey() {
    this.isCreation = true;
  }
  annuler() {
    this.isCreation = false;
  }

  deleteSurvey(questionnaire: Questionnaire) {
    if (confirm("Voulez vous vraiment supprimer ce questionnaire ?")) {
      return this.http.get<Questionnaire>("http://localhost:8080/admin/rest/survey/" +
        questionnaire.idQuestionnaire).subscribe();
    }
    return null;
  }

}

