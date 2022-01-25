import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ReponseUtilisateurQuestion } from '../models/reponseUtilisateurQuestion';
import { RestapiService } from '../restapi.service';
import { QuestionnaireService } from '../services/questionnaire.service';

@Component({
  selector: 'app-questionnaire-details',
  templateUrl: './questionnaire-details.component.html',
  styleUrls: ['./questionnaire-details.component.scss']
})
export class QuestionnaireDetailsComponent implements OnInit {

  surveyDetails = new MatTableDataSource<ReponseUtilisateurQuestion>();
  displayedColumns: string[] = ['Num Tentative', 'Temps passé', 'Date de réalisation'];

  constructor( private restapiService: RestapiService, public questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
    this.restapiService.getSurveyDetails_2(4).subscribe(surveyDetails => this.surveyDetails.data = surveyDetails)
    console.log(this.surveyDetails.data);
  }


}