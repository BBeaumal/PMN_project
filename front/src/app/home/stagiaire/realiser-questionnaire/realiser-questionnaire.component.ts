import { Component, OnInit } from '@angular/core';
import {QuestionnaireUserService} from "../../service/questionnaire-user.service";
import {Reponse} from "../../../models/reponse";
import {HttpClient} from "@angular/common/http";
import {Questionnaire} from "../../../models/questionnaire";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-realiser-questionnaire',
  templateUrl: './realiser-questionnaire.component.html',
  styleUrls: ['./realiser-questionnaire.component.scss']
})
export class RealiserQuestionnaireComponent implements OnInit {

  constructor(public questionnaireUserService: QuestionnaireUserService, private http: HttpClient, private datepipe: DatePipe) { }

  ngOnInit(): void {
  }

  next() {
    this.questionnaireUserService.incrementQuestion++;
  }

  valider() {
    if (confirm("Voulez-vous valider ce parcours ?"))
    {
      this.questionnaireUserService.incrementQuestion = 0;
      const date=new Date();
      // @ts-ignore
      this.questionnaireUserService.questionnaire.dateFin = this.datepipe.transform(date, 'yyyy-MM-dd HH:mm:ss');
      this.http.post<Questionnaire>('http://localhost:8080/rest/questionnaire/repondre',
        this.questionnaireUserService.questionnaire).subscribe(value => {
        this.questionnaireUserService.afficherQuestionnaire = false;
      });
    }
  }

  toggle(reponse: Reponse) {
    if (reponse.isCorrect){
      // @ts-ignore
      reponse.isCorrect = null;
    } else {
      reponse.isCorrect = true;
    }

  }

  quitter() {
    if (confirm(" /!\\ Vous allze quitter un questionnaire en cours"))
    {
      this.questionnaireUserService.incrementQuestion = 0;
      this.questionnaireUserService.afficherQuestionnaire = false;
    }
  }
}
