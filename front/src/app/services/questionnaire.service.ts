import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  isCreation = false;

  constructor() { }

  ajouterSurvey(){
    this.isCreation=true;
  }
}

