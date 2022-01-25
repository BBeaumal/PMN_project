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
  displayedColumns: string[] = ['idParcours','nomQuestionnaire','note', 'dateFin', 'dateRealisation'];

  constructor(private http: HttpClient, private rest: RestapiService, private questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
    this.rest.parcoursList().subscribe(parcours => {
      this.parcours.data = parcours;
      console.log(parcours);
    });
  }

}
