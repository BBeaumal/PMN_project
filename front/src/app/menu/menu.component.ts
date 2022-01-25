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
  }

  logout() {
    this.isAdminService.isLogged = false;
    this.isAdminService.isAdmin = false;
    sessionStorage.removeItem("secure-pmn-token");
    this.router.navigate(['login']);
  }
}
