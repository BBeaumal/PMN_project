import { Question } from "./question";
import { Utilisateur } from "./utilisateur";

export class ReponseUtilisateurQuestion {

    linkPk: ReponseUtilisateurQuestionLink | undefined;
    reponse: number | undefined;
    dateRealisation: Date | undefined;
    dateFin: Date | undefined;
    timeSpend?: string;
    

  constructor() {
  }
}

export class ReponseUtilisateurQuestionLink {
  utilisateur: Utilisateur| undefined;
  question: Question| undefined;
}