import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { RestapiService } from '../restapi.service';
import { Question } from '../models/question';
import { QuestionService } from '../services/question.service';
import { Router } from '@angular/router';
import {QuestionnaireService} from "../services/questionnaire.service";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  questions = new MatTableDataSource<Question>();



  displayedColumns: string[] = ['idQuestion', 'Intitule', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService,
    public questionService: QuestionService, private router: Router, private questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
    this.restapiService.questionsList(this.questionnaireService.questionnaire.idQuestionnaire).subscribe(utilisateurs => this.questions.data = utilisateurs);
  }

  deleteQuestion(question: Question) {
    if (confirm("Voulez-vous supprimer cette question ?")) {
      const listeQuestion: Question[] = this.questions.data;
      this.deleteQuestionRequest(question).subscribe();
      this.questions.data.forEach((value, index) => {
        if (value.idQuestion == question.idQuestion) {
          listeQuestion.splice(index, 1);
        }
      })
      this.questions.data = listeQuestion;
    }
  }

  deleteQuestionRequest(question: Question) {
    return this.http.get<Question>("http://localhost:8080/admin/rest/question/" + question.idQuestion);
  }

  editQuestion(question: Question) {
    this.questionService.question = question;
    this.questionService.isModification = true;
    this.questionService.afficherQuestions = false;
    this.questionService.afficherReponses = true;

  }

  addQuestion() {
    this.questionService.question = {} as Question;
    this.questionService.afficherQuestions = false;
    this.questionService.afficherReponses = true;
    this.questionService.isModification = false;
  }
}
