import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RestapiService} from "../../../restapi.service";
import {Utilisateur} from "../../../models/utilisateur";
import {UtilisateurService} from "../../utilisateur.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-liste-utilisateurs',
  templateUrl: './liste-utilisateurs.component.html',
  styleUrls: ['./liste-utilisateurs.component.scss']
})
export class ListeUtilisateursComponent implements OnInit {


  utilisateurs = new MatTableDataSource<Utilisateur>();

  displayedColumns: string[] = ['Nom', 'Prenom', 'Email', 'Societe', 'Supprimer', 'Editer'];

  constructor(private http: HttpClient, private restapiService: RestapiService, public utilisateurService: UtilisateurService) { }

  ngOnInit(): void {
    this.restapiService.usersList().subscribe(utilisateurs => this.utilisateurs.data = utilisateurs);
  }

  supprimer(utilisateur: Utilisateur) {
    if (confirm("Voulez-vous vraiment supprimer cet utilisateur ?")){
      const listUtilisateur: Utilisateur[] = this.utilisateurs.data;
      this.restapiService.supprimerUtilisateur(utilisateur).subscribe();
      this.utilisateurs.data.forEach((value, index) => {
        if (value.idUtilisateur == utilisateur.idUtilisateur){
          listUtilisateur.splice(index, 1);
        }
      })
      this.utilisateurs.data = listUtilisateur;
    }
  }

  editer(utilisateur: Utilisateur) {
    this.utilisateurService.isModifier = true;
    this.utilisateurService.utilisateur = utilisateur;
    this.utilisateurService.isCreation = true;
  }
}
