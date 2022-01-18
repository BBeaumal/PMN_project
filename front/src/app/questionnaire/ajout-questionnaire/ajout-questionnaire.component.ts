import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Questionnaire } from 'src/app/models/questionnaire';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';
import {QuestionService} from "../../services/question.service";

@Component({
  selector: 'app-ajout-questionnaire',
  templateUrl: './ajout-questionnaire.component.html',
  styleUrls: ['./ajout-questionnaire.component.scss']
})
export class AjoutQuestionnaireComponent implements OnInit {
  public questionnaire = {} as Questionnaire;
  hide = true;
  afficherQuestions: boolean = false;

  constructor(private http: HttpClient, public questionnaireService: QuestionnaireService, public questionService: QuestionService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm): void {
    this.questionnaireService.questionnaire.nomQuestionnaire = form.value['intitule'];
    this.questionnaireService.questionnaire.description = form.value['description'];
    this.http.post<Questionnaire>('http://localhost:8080/admin/rest/survey',
      this.questionnaireService.questionnaire).subscribe(() => (this.questionnaireService.annuler()));
  }

  gererQuestions() {
    this.questionService.afficherQuestions = !this.questionService.afficherQuestions;
    this.questionService.afficherReponses = false;
  }
}
