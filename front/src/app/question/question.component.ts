import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { RestapiService } from '../restapi.service';
import { Question } from '../models/question';
import { QuestionService } from '../services/question.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  questions = new MatTableDataSource<Question>();



  displayedColumns: string[] = ['idQuestion', 'Intitule', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService,
    private questionService: QuestionService, private router: Router) { }

  ngOnInit(): void {
    this.restapiService.questionsList(1).subscribe(utilisateurs => this.questions.data = utilisateurs);

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
    console.log("question before assignment " + question)
    this.questionService.question = question;
    this.questionService.isCreation = true;
    this.router.navigate(["/questions/create"]);
  }
}