import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginComponent = new FormControl();

  username="";
  password="";

  constructor(private service: RestapiService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {

  }

  login() {
    this.service.login(this.username, this.password).subscribe(() => {
      sessionStorage.setItem("secure-pmn-token", 'Basic ' + btoa(this.username + ':' + this.password))
      this.router.navigate(['home']);
    });
  }
}
