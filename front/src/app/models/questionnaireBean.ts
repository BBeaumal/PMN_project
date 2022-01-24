import {Question} from "./question";

export interface QuestionnaireBean {
    dateFin: String;
    dateFormulaire: String;
    idQuestionnaire: number;
    nomQuestionnaire: string;
    description: string;
    dateCreation: Date;
    listeQuestion: Question[];
}
