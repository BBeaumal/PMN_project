import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RestapiService} from "../restapi.service";
import {Utilisateur} from "../models/utilisateur";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  utilisateurs: Utilisateur[] = [];

  displayedColumns: string[] = ['Nom', 'Prenom', 'Email', 'Societe', 'Supprimer', 'Editer'];


  constructor(private http: HttpClient, private restapiService: RestapiService) { }

  ngOnInit(): void {
    this.restapiService.usersList().subscribe(utilisateurs => this.utilisateurs = utilisateurs);
  }

}
