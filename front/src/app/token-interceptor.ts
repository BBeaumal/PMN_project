import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (sessionStorage.getItem("secure-pmn-token") !== undefined && sessionStorage.getItem("secure-pmn-token") !== null){
      const userToken = sessionStorage.getItem("secure-pmn-token");
      const modifiedReq = req.clone({
        headers: req.headers.set('Authorization', `${userToken}`),
      });
      return next.handle(modifiedReq);
    } else {
      return next.handle(req);
    }
  }
}
