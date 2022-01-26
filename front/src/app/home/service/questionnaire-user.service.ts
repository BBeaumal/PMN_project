import { Injectable } from '@angular/core';
import {QuestionnaireBean} from "../../models/questionnaireBean";

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireUserService {

  afficherQuestionnaire: boolean = false;
  afficherDetailQuestionnaire: boolean = false;
  questionnaire: QuestionnaireBean = {} as QuestionnaireBean;
  idQuestionnaireDetail: number | undefined;
  nbQuestions: number = 0;
  incrementQuestion: number = 0;

  constructor() { }
}
