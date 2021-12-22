import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  isCreation = false;

  constructor() { }


  ajouterUser() {
    this.isCreation = true;
  }
}
