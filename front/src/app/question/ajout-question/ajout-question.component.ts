import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { Question } from 'src/app/models/question';
import { Questionnaire } from 'src/app/models/questionnaire';
import { Reponse } from 'src/app/models/reponse';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-ajout-question',
  templateUrl: './ajout-question.component.html',
  styleUrls: ['./ajout-question.component.scss']
})

export class AjoutQuestionComponent implements OnInit {

  isChecked1: boolean;
  isChecked2: boolean = false;
  isChecked3: boolean = false;
  isChecked4: boolean = false;
  isChecked5: boolean = false;
  libelleReponse1: string;
  libelleReponse2: string;
  libelleReponse3: string;
  libelleReponse4: string;
  libelleReponse5: string;


  private question = {} as Question;
  hide = true;

  constructor(private http: HttpClient, private questionService: QuestionService) {
    this.isChecked1 = false;
    this.libelleReponse1 = "";
    this.libelleReponse2 = "";
    this.libelleReponse3 = "";
    this.libelleReponse4 = "";
    this.libelleReponse5 = "";
  }

  ngOnInit(): void {
    console.log(this.questionService.question);
    if (this.questionService.isCreation) {
      this.questionService.question.reponses.forEach(
        (value, index) => {
          switch (index) {
            case 0: {
              this.libelleReponse1 = value.libelle
              break;
            }
            case 1: {
              this.libelleReponse2 = value.libelle
              break;
            }

            case 2: {
              this.libelleReponse3 = value.libelle
              break;
            }

            case 3: {
              this.libelleReponse4 = value.libelle
              break;
            }

            case 4: {
              this.libelleReponse5 = value.libelle
              break;
            }
          }
        }
      )
    }
  }

  onSubmit(form: NgForm) {

    let tab: Reponse[] = [];
    tab.push(new Reponse(this.libelleReponse1, this.isChecked1),
      new Reponse(this.libelleReponse2, this.isChecked2),
      new Reponse(this.libelleReponse3, this.isChecked3),
      new Reponse(this.libelleReponse4, this.isChecked4),
      new Reponse(this.libelleReponse5, this.isChecked5)
    )

    this.question.intitule = form.value['intitule'];
    this.question.reponses = tab;
    let questionnaire = new Questionnaire();
    questionnaire.idQuestionnaire = 1;
    this.question.questionnaire = questionnaire;
    console.log(this.question);
    this.http.post<Question>('http://localhost:8080/admin/rest/question',
      this.question).subscribe();
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
