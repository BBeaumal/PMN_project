import { Component, OnInit } from '@angular/core';
import {UtilisateurService} from "./utilisateur.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(public utilisateurService: UtilisateurService) { }

  ngOnInit(): void {

  }
}
