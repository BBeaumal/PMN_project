import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeQuestionnaireComponent } from './liste-questionnaire.component';

describe('ListeQuestionnaireComponent', () => {
  let component: ListeQuestionnaireComponent;
  let fixture: ComponentFixture<ListeQuestionnaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeQuestionnaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeQuestionnaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
