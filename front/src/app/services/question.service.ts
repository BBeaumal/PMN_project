import { Injectable } from '@angular/core';
import { Question } from '../models/question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  isCreation = false;
  question = {} as Question;
  constructor() { }

  ajouterQuestion() {
    this.isCreation = true;
  }
}
