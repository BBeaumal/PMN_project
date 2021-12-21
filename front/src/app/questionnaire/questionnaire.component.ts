import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Questionnaire } from '../models/questionnaire';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.scss']
})
export class QuestionnaireComponent implements OnInit {

  surveys: Questionnaire[] = [];
  displayedColumns: string[] = ['ID', 'Nom', 'Auteur', 'Date de creation', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService) { }

  ngOnInit(): void {
    this.restapiService.questionnairesList().subscribe(surveys => this.surveys = surveys)
  }

}
