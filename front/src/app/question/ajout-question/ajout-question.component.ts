import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { Question } from 'src/app/models/question';
import { Questionnaire } from 'src/app/models/questionnaire';
import { Reponse } from 'src/app/models/reponse';
import { QuestionService } from 'src/app/services/question.service';
import {QuestionBean} from "../../models/questionBean";
import {Router} from "@angular/router";

@Component({
  selector: 'app-ajout-question',
  templateUrl: './ajout-question.component.html',
  styleUrls: ['./ajout-question.component.scss']
})

export class AjoutQuestionComponent implements OnInit {

  intitule: string;
  isChecked1: boolean;
  isChecked2: boolean;
  isChecked3: boolean;
  isChecked4: boolean;
  isChecked5: boolean;
  libelleReponse1: string;
  libelleReponse2: string;
  libelleReponse3: string;
  libelleReponse4: string;
  libelleReponse5: string;


  private question = {} as Question;
  hide = true;

  constructor(private http: HttpClient, private questionService: QuestionService, private router: Router) {
    this.isChecked1 = false;
    this.isChecked2 = false;
    this.isChecked3 = false;
    this.isChecked4 = false;
    this.isChecked5 = false;
    this.libelleReponse1 = "";
    this.libelleReponse2 = "";
    this.libelleReponse3 = "";
    this.libelleReponse4 = "";
    this.libelleReponse5 = "";
    this.intitule = "";
  }

  ngOnInit(): void {
    if (this.questionService.isCreation) {
      this.intitule = this.questionService.question.intitule;
      this.questionService.question.reponses.forEach(
        (value, index) => {
          switch (index) {
            case 0: {
              this.libelleReponse1 = value.libelle;
              this.isChecked1 = value.isCorrect;
              break;
            }
            case 1: {
              this.libelleReponse2 = value.libelle;
              this.isChecked2 = value.isCorrect;
              break;
            }

            case 2: {
              this.libelleReponse3 = value.libelle;
              this.isChecked3 = value.isCorrect;
              break;
            }

            case 3: {
              this.libelleReponse4 = value.libelle;
              this.isChecked4 = value.isCorrect;
              break;
            }

            case 4: {
              this.libelleReponse5 = value.libelle;
              this.isChecked5 = value.isCorrect;
              break;
            }
          }
        }
      )
      if (this.questionService.question.intitule) {
        this.intitule = this.questionService.question.intitule
      }
    }
  }

  resetFormulaire(){
    this.isChecked1 = false;
    this.isChecked2 = false;
    this.isChecked3 = false;
    this.isChecked4 = false;
    this.isChecked5 = false;
    this.libelleReponse1 = "";
    this.libelleReponse2 = "";
    this.libelleReponse3 = "";
    this.libelleReponse4 = "";
    this.libelleReponse5 = "";
    this.intitule = "";
  }

  onSubmit(form: NgForm) {
    let tab: Reponse[] = [];
    if (this.libelleReponse1.length > 0){
      tab.push(new Reponse(this.libelleReponse1, this.isChecked1, null));
    }
    if (this.libelleReponse2.length > 0){
      tab.push(new Reponse(this.libelleReponse2, this.isChecked2, null));
    }
    if (this.libelleReponse3.length > 0){
      tab.push(new Reponse(this.libelleReponse3, this.isChecked3, null));
    }
    if (this.libelleReponse4.length > 0){
      tab.push(new Reponse(this.libelleReponse4, this.isChecked4, null));
    }
    if (this.libelleReponse5.length > 0){
      tab.push(new Reponse(this.libelleReponse5, this.isChecked5, null));
    }

    if (this.questionService.isCreation){
      this.question.idQuestion = this.questionService.question.idQuestion;
      this.questionService.question.reponses.forEach(repBdd => {
        let stop = false;
        tab.forEach(repQuest => {
          if (repQuest.idReponse == null && !stop){
            repQuest.idReponse = repBdd.idReponse;
            stop = true;
          }
        })
      })
      this.questionService.isCreation = false;
    }

    this.question.intitule = form.value['intitule'];
    this.question.reponses = tab;
    let questionnaire = new Questionnaire();
    questionnaire.idQuestionnaire = 1;
    this.question.questionnaire = questionnaire;
    this.http.post<Question>('http://localhost:8080/admin/rest/question',
      this.question).subscribe();
    this.resetFormulaire();
    this.router.navigate(["/questions"]);
  }

  toggle($event: MatCheckboxChange, numeroReponse: number) {
    switch (numeroReponse) {
      case 1: {
        this.isChecked1 = $event.checked;
        break;
      }
      case 2: {
        this.isChecked2 = $event.checked;
        break;
      }

      case 3: {
        this.isChecked3 = $event.checked;
        break;
      }

      case 4: {
        this.isChecked4 = $event.checked;
        break;
      }

      case 5: {
        this.isChecked5 = $event.checked;
        break;
      }

    }
  }

  checkInput() {

    if (this.libelleReponse1.length == 0) {
      this.isChecked1 = false;
    }
    if (this.libelleReponse2.length == 0) {
      this.isChecked2 = false;
    }
    if (this.libelleReponse3.length == 0) {
      this.isChecked3 = false;
    }
    if (this.libelleReponse4.length == 0) {
      this.isChecked4 = false;
    }
    if (this.libelleReponse5.length == 0) {
      this.isChecked5 = false;
    }

  }

}
