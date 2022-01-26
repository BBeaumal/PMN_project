import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { RestapiService } from '../restapi.service';
import { QuestionnaireService } from '../services/questionnaire.service';
import {QuestionnaireUserService} from "../home/service/questionnaire-user.service";
import {Parcours} from "../models/parcours";

@Component({
  selector: 'app-questionnaire-details',
  templateUrl: './questionnaire-details.component.html',
  styleUrls: ['./questionnaire-details.component.scss']
})
export class QuestionnaireDetailsComponent implements OnInit {

  surveyDetails = new MatTableDataSource<Parcours>();
  displayedColumns: string[] = ['Num Tentative', 'Temps passé', 'Date de réalisation', 'Note'];

  constructor( private restapiService: RestapiService, public questionnaireService: QuestionnaireService, public questionnaireUserService: QuestionnaireUserService) { }



  ngOnInit(): void {

    //getSurveyDetails(this.questionnaireService.questionnaire).subscribe
    this.restapiService.getSurveyDetails_2(this.questionnaireUserService.idQuestionnaireDetail).subscribe(surveyDetails =>
      {
      this.surveyDetails.data = surveyDetails;
      this.surveyDetails.data.forEach(
        function (value)
          {
            console.log("date : "+value.dateFin);
            value.timeSpend = addTime(value.dateFin!, value.dateRealisation!);
          }
        )
      }
    )
  }

  retour() {
    window.location.href = "/";
  }
}

function addTime(dateFin: Date, dateRealisation: Date): string {
    const dateA = new Date(dateFin);
    const dateB = new Date(dateRealisation);

    var timeDifference = dateA.getTime() - dateB.getTime();

    var ms = timeDifference % 1000;
    timeDifference = (timeDifference - ms) / 1000;
    var secs = timeDifference % 60;
    timeDifference = (timeDifference - secs) / 60;
    var mins = timeDifference % 60;
    var hrs = (timeDifference - mins) / 60;

    let affichage:string = "";
    if (hrs != 0){
      affichage += hrs + ' heure(s) ';
    }
    if (mins != 0){
      affichage += mins + ' minutes(s) ';
    }
    if (secs != 0){
      affichage += secs + ' seconde(s) ';
    }
    return affichage;
}
