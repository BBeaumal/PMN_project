import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RestapiService} from "../../restapi.service";
import {Utilisateur} from "../../models/utilisateur";
import {UtilisateurService} from "../utilisateur.service";

@Component({
  selector: 'app-liste-utilisateurs',
  templateUrl: './liste-utilisateurs.component.html',
  styleUrls: ['./liste-utilisateurs.component.scss']
})
export class ListeUtilisateursComponent implements OnInit {


  utilisateurs: Utilisateur[] = [];

  displayedColumns: string[] = ['Nom', 'Prenom', 'Email', 'Societe', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService, public utilisateurService: UtilisateurService) { }

  ngOnInit(): void {
    this.restapiService.usersList().subscribe(utilisateurs => this.utilisateurs = utilisateurs);
  }

}
