import { Component, OnInit } from '@angular/core';
import {UtilisateurService} from "./utilisateur.service";
import {IsAdminService} from "../services/is-admin.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  
  constructor(public utilisateurService: UtilisateurService, public isAdminService: IsAdminService) { }

  ngOnInit(): void {

  }
}
