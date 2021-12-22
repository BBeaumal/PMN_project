import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Questionnaire } from 'src/app/models/questionnaire';
import { RestapiService } from 'src/app/restapi.service';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';

@Component({
  selector: 'app-liste-questionnaire',
  templateUrl: './liste-questionnaire.component.html',
  styleUrls: ['./liste-questionnaire.component.scss']
})
export class ListeQuestionnaireComponent implements OnInit {

  surveys: Questionnaire[] = [];
  displayedColumns: string[] = ['ID', 'Nom', 'Auteur', 'Date de creation', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService, public questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
    this.restapiService.questionnairesList().subscribe(surveys => this.surveys = surveys)
  }

  onSubmit(form: NgForm) {
    const name = form.value['nomQuestionnaire'];
    const status = form.value['description'];
  }
}