
export class Reponse {
    libelle: string;
    isCorrect: boolean;
    idReponse: any;
    question: any;

  constructor(libelle: string, isCorrect: boolean, idReponse: any) {
    this.libelle = libelle;
    this.isCorrect = isCorrect;
    this.idReponse = idReponse;
  }

}
