import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginComponent = new FormControl();

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(): void {
  }
}