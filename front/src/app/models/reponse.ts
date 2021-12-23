export class Reponse {
    libelle: string;
    isCorrect: boolean;
    constructor(libelle: string, isCorrect: boolean) {
        this.libelle = libelle;
        this.isCorrect = isCorrect;
    }
}