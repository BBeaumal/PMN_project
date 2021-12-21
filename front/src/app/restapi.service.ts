import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Utilisateur } from "./models/utilisateur";
import { Question } from './models/question';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {


  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.http.get('http://localhost:8080/rest/login', { headers, responseType: 'text' as 'json' });
  }

  usersList(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>("http://localhost:8080/admin/rest/users");
  }

  questionsList(): Observable<Question[]> {
    return this.http.get<Question[]>("http://localhost:8080/admin/rest/question");
  }
}
