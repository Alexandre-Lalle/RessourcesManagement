import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Observable, catchError, throwError } from "rxjs";
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {


    constructor(private authService: AuthService, private router: Router) { }

    intercept(
        req: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        if (req.headers.get('No-Auth') === 'True') {
            return next.handle(req.clone());
        }

        let token = this.authService.getToken();

        if (!token) token = ""

        req = this.addToken(req, token);

        console.log(' the request : ', req, token);
        console.log("thee handle : ", next.handle(req));

        return next.handle(req).pipe(
            catchError(
                (err: HttpErrorResponse) => {
                    console.log(err.status);
                    if (err.status === 401) {
                        alert("Votre session a été expirée !");
                        this.router.navigate(['/login']);
                    } else if (err.status === 403) {
                        this.router.navigate(['/forbidden']);
                    }
                    return throwError("Some thing is wrong");
                }
            )
        );
    }


    private addToken(request: HttpRequest<any>, token: string) {
        return request.clone(
            {
                setHeaders: {
                    'Authorization': 'Bearer ' + token,
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }
        );
    }
}