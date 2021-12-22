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
  private utilisateur = {} as Utilisateur;
  hide = true;

  constructor(private http: HttpClient, private utilisateurService: UtilisateurService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.utilisateur.nom = form.value['nom'];
    this.utilisateur.prenom = form.value['prenom'];
    this.utilisateur.login = form.value['identifiant'];
    this.utilisateur.password = form.value['password'];
    this.utilisateur.mail = form.value['email'];
    this.utilisateur.societe = form.value['societe'];
    this.http.post<Utilisateur>('http://localhost:8080/admin/rest/user', this.utilisateur).subscribe(()=> this.annuler());
  }

  annuler() {
    this.utilisateurService.isCreation = false;
  }
}
