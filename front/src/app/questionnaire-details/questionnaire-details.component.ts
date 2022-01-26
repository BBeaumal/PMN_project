import { ValueConverter } from '@angular/compiler/src/render3/view/template';
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
    
    //getSurveyDetails(this.questionnaireService.questionnaire).subscribe
    this.restapiService.getSurveyDetails_2(4).subscribe(surveyDetails => 
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

}

function addTime(dateFin: Date, dateRealisation: Date): string {

    console.log(dateFin);
    console.log(dateRealisation);
    const dateA = new Date(dateFin);
    const dateB = new Date(dateRealisation);

    var timeDifference = dateA.getTime() - dateB.getTime();

    var ms = timeDifference % 1000;
    timeDifference = (timeDifference - ms) / 1000;
    var secs = timeDifference % 60;
    timeDifference = (timeDifference - secs) / 60;
    var mins = timeDifference % 60;
    var hrs = (timeDifference - mins) / 60;

    console.log(hrs + ':' + mins + ':' + secs + '.' + ms);
    return  mins + ' min :' + secs + ' secs';
}