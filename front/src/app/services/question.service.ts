import { Injectable } from '@angular/core';
import { Question } from '../models/question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  isModification = false;
  question = {} as Question;
  afficherQuestions: boolean = false;
  afficherReponses: boolean = false;
  constructor() { }

}
