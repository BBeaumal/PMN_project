import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Questionnaire} from "../../../models/questionnaire";

@Component({
  selector: 'app-liste-questionnaire-user',
  templateUrl: './liste-questionnaire-user.component.html',
  styleUrls: ['./liste-questionnaire-user.component.scss']
})
export class ListeQuestionnaireUserComponent implements OnInit {

  questionnaires: Questionnaire[] = [];

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get<Questionnaire[]>("http://localhost:8080/rest/surveys").subscribe(value => this.questionnaires = value);
  }

}
