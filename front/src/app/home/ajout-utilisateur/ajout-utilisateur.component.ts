import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Utilisateur} from "../../models/utilisateur";
import {HttpClient} from "@angular/common/http";
import {UtilisateurService} from "../utilisateur.service";

@Component({
  selector: 'app-ajout-utilisateur',
  templateUrl: './ajout-utilisateur.component.html',
  styleUrls: ['./ajout-utilisateur.component.scss']
})
export class AjoutUtilisateurComponent implements OnInit {
  hide = true;

  constructor(private http: HttpClient, public utilisateurService: UtilisateurService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.utilisateurService.utilisateur.nom = form.value['nom'];
    this.utilisateurService.utilisateur.prenom = form.value['prenom'];
    this.utilisateurService.utilisateur.login = form.value['identifiant'];
    this.utilisateurService.utilisateur.password = form.value['password'];
    this.utilisateurService.utilisateur.mail = form.value['email'];
    this.utilisateurService.utilisateur.societe = form.value['societe'];
    if (this.utilisateurService.utilisateur.login.startsWith(" ")){
      this.utilisateurService.utilisateur.login = this.utilisateurService.utilisateur.login.substring(1).replace(" ", "");
    }
    if (this.utilisateurService.utilisateur.login.endsWith(" ")){
      this.utilisateurService.utilisateur.login = this.utilisateurService.utilisateur.login.substring(this.utilisateurService.utilisateur.login.length).replace(" ", "");
    }
    this.utilisateurService.utilisateur.login = this.utilisateurService.utilisateur.login.toLowerCase();
    if(this.utilisateurService.utilisateur.password == ""){

    }
    this.http.post<Utilisateur>('http://localhost:8080/admin/rest/user', this.utilisateurService.utilisateur).subscribe(()=> this.annuler(),
      error => this.utilisateurService.afficherErreur = true);
  }

  annuler() {
    this.utilisateurService.isCreation = false;
    this.utilisateurService.isModifier = false;
    this.utilisateurService.afficherErreur = false;
    this.utilisateurService.utilisateur = {} as Utilisateur;
  }
}
