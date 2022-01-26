import { Questionnaire } from "./questionnaire";
import { Reponse } from "./reponse";

export interface Question {
    idQuestion: number;
    intitule: string;
    reponses: Reponse[];
    questionnaire: Questionnaire;
}
