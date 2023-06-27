import { Component } from '@angular/core';
import {LoginService} from "./login.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  usuario = {username: '', senha: ''};

  constructor(private loginService: LoginService) {
  }

  public login(){
    this.loginService.login(this.usuario);
  }
}
