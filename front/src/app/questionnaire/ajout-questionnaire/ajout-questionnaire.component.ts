import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Questionnaire } from 'src/app/models/questionnaire';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';

@Component({
  selector: 'app-ajout-questionnaire',
  templateUrl: './ajout-questionnaire.component.html',
  styleUrls: ['./ajout-questionnaire.component.scss']
})
export class AjoutQuestionnaireComponent implements OnInit {
  private questionnaire = {} as Questionnaire;
  hide = true;

  constructor(private http: HttpClient, public questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm): void {
    this.questionnaire.nomQuestionnaire = form.value['intitule'];
    this.questionnaire.description = form.value['description'];
    this.http.post<Questionnaire>('http://localhost:8080/admin/rest/survey',
      this.questionnaire).subscribe(() => (this.questionnaireService.annuler()));
  }

}
