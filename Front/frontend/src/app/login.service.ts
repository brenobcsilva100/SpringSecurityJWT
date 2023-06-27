import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConstants} from "./app-constants";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(usuario){
    return this.http.post(AppConstants.baseLogin, JSON.stringify(usuario)).subscribe(data =>{
      var token = JSON.parse(JSON.stringify(data)).token.split(' ')[1];

      localStorage.setItem("token", token);
    }, error => {
      console.error("Error ao fazer login");
    });


  }
}
