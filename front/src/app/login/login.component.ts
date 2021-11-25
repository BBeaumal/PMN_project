import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {RestapiService} from "./restapi.service";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginComponent = new FormControl();

  username="";
  password="";

  constructor(private service: RestapiService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {

  }

  login() {
    this.service.login(this.username, this.password).subscribe(value => console.log(value));
  }
}
