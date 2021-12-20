import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {RestapiService} from "../restapi.service";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private userService: RestapiService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (sessionStorage.getItem("secure-pmn-token") !== undefined && sessionStorage.getItem("secure-pmn-token") !== null ) {
      return true;
    } else {
      return this.router.parseUrl("/login");
    }
  }
}
