import { Injectable } from '@angular/core';
import {Utilisateur} from "../models/utilisateur";

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  isCreation = false;
  isModifier = false;
  utilisateur = {} as Utilisateur;
  afficherErreur = false;

  constructor() { }


  ajouterUser() {
    this.isCreation = true;
  }
}
