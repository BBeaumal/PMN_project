import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutQuestionnaireComponent } from './ajout-questionnaire.component';

describe('AjoutQuestionnaireComponent', () => {
  let component: AjoutQuestionnaireComponent;
  let fixture: ComponentFixture<AjoutQuestionnaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjoutQuestionnaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjoutQuestionnaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
