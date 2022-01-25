import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {IsAdminService} from "../services/is-admin.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor(private router: Router, public isAdminService: IsAdminService) {
  }

  ngOnInit(): void {
    this.isAdminService.isLogged = sessionStorage.getItem("secure-pmn-token") != null;
    this.isAdminService.isAdminFunc().subscribe({
      next: () => {this.isAdminService.isAdmin = true},
      error: () => {this.isAdminService.isAdmin = false},
    });
  }

  logout() {
    this.isAdminService.isAdmin = false;
    this.isAdminService.isLogged = false;
    sessionStorage.removeItem("secure-pmn-token");
    this.router.navigate(['login']);
  }
}
