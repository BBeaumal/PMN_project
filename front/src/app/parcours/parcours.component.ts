import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Parcours } from '../models/parcours';
import { RestapiService } from '../restapi.service';
import { QuestionnaireService } from '../services/questionnaire.service';

@Component({
  selector: 'app-parcours',
  templateUrl: './parcours.component.html',
  styleUrls: ['./parcours.component.scss']
})
export class ParcoursComponent implements OnInit {

  parcours = new MatTableDataSource<Parcours>();
  displayedColumns: string[] = ['nomQuestionnaire', 'Temps passé', 'dateRealisation', 'note'];

  constructor(private http: HttpClient, private rest: RestapiService, private questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
    this.rest.parcoursList().subscribe(parcours => {
      this.parcours.data = parcours;
      this.parcours.data.forEach(
        function (value)
        {
          console.log("date : "+value.dateFin);
          value.timeSpend = addTime(value.dateFin!, value.dateRealisation!);
        }
      )
      console.log(parcours);
    });
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
