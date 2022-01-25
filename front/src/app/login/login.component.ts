import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";
import {IsAdminService} from "../services/is-admin.service";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginComponent = new FormControl();

  username="";
  password="";

  constructor(private service: RestapiService, private router: Router, private isAdminService: IsAdminService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {

  }

  login() {
    this.username = this.username.toLowerCase();
    if (this.username.startsWith(" ")){
      this.username = this.username.substring(1).replace(" ", "");
    }
    if (this.username.endsWith(" ")){
      this.username = this.username.substring(this.username.length).replace(" ", "");
    }
    if (this.password.startsWith(" ")){
      this.password = this.password.substring(1).replace(" ", "");
    }
    if (this.password.endsWith(" ")){
      this.password = this.password.substring(this.password.length).replace(" ", "");
    }
    this.service.login(this.username, this.password).subscribe(() => {
      sessionStorage.setItem("secure-pmn-token", 'Basic ' + btoa(this.username + ':' + this.password))
      this.isAdminService.isAdminFunc().subscribe({
        next: () => {this.isAdminService.isAdmin = true},
        error: () => {this.isAdminService.isAdmin = false},
      });
      this.isAdminService.isLogged = true;
      this.router.navigate(['home']);
    });
  }
}
