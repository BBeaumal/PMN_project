import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Questionnaire} from "../../../models/questionnaire";
import {QuestionnaireUserService} from "../../service/questionnaire-user.service";
import {QuestionnaireBean} from "../../../models/questionnaireBean";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-liste-questionnaire-user',
  templateUrl: './liste-questionnaire-user.component.html',
  styleUrls: ['./liste-questionnaire-user.component.scss']
})
export class ListeQuestionnaireUserComponent implements OnInit {

  questionnaires: Questionnaire[] = [];
  private date: Date | undefined;

  constructor(private http:HttpClient, public questionnaireUserService: QuestionnaireUserService, public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.http.get<Questionnaire[]>("http://localhost:8080/rest/surveys").subscribe(value => this.questionnaires = value);
  }

  startQuestionnaire(questionnaire: Questionnaire) {
    this.http.get<QuestionnaireBean>("http://localhost:8080/rest/survey/" + questionnaire.idQuestionnaire).subscribe(value => {
      this.questionnaireUserService.questionnaire = value;
      this.questionnaireUserService.nbQuestions = value.listeQuestion.length;
      this.questionnaireUserService.afficherQuestionnaire = true;
      this.date=new Date();
      // @ts-ignore
      this.questionnaireUserService.questionnaire.dateFormulaire = this.datepipe.transform(this.date, 'yyyy-MM-dd HH:mm:ss');
    })
  }
}
