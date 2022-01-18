import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Questionnaire } from 'src/app/models/questionnaire';
import { RestapiService } from 'src/app/restapi.service';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';

@Component({
  selector: 'app-liste-questionnaire',
  templateUrl: './liste-questionnaire.component.html',
  styleUrls: ['./liste-questionnaire.component.scss']
})
export class ListeQuestionnaireComponent implements OnInit {

  surveys = new MatTableDataSource<Questionnaire>();
  displayedColumns: string[] = ['ID', 'Nom', 'Description', 'Date de creation', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService, public questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
    this.restapiService.questionnairesList().subscribe(surveys => this.surveys.data = surveys)
  }

  deleteSurvey(questionnaire: Questionnaire) {
    if (confirm("Voulez-vous vraiment supprimer ce questionnaire ?")) {
      const listeQuestionnaire: Questionnaire[] = this.surveys.data;
      this.questionnaireService.deleteSurvey(questionnaire).subscribe();
      this.surveys.data.forEach((value, index) => {
        if (value.idQuestionnaire == questionnaire.idQuestionnaire) {
          listeQuestionnaire.splice(index, 1);
        }
      })
      this.surveys.data = listeQuestionnaire;
    }
  }

  editSurvey(questionnaire: Questionnaire) {
    this.questionnaireService.questionnaire = questionnaire;
    this.questionnaireService.isModifier = true;
    this.questionnaireService.isCreation = true;
  }
}