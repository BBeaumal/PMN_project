import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { Question } from '../models/question';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  questions: Question[] = [];

  displayedColumns: string[] = ['idQuestion', 'Intitule', 'IdQuestionnaire', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService) { }

  ngOnInit(): void {
    this.restapiService.questionsList(1).subscribe(utilisateurs => this.questions = utilisateurs);
  }

}