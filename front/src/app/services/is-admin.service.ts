import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Utilisateur} from "../models/utilisateur";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IsAdminService {
  public isAdmin: boolean | undefined;
  public isLogged: boolean | undefined;

  constructor(private http: HttpClient) { }

  isAdminFunc(): Observable<any>{
    return this.http.get<Utilisateur[]>("http://localhost:8080/admin/rest/users");
  }
}
