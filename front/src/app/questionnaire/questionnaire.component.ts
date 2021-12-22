import { Component, OnInit } from '@angular/core';
import { QuestionnaireService } from '../services/questionnaire.service';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.scss']
})
export class QuestionnaireComponent implements OnInit {

  constructor(public questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
  }
}
