import { Router} from '@angular/router';
import { LoginService } from '../Services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  public loginService: LoginService;

  username: string="";
  password: string="";

  constructor(_loginService: LoginService, private router: Router) {
    this.loginService = _loginService;
  }

  onLogin(){

    this.loginService.getUser(this.username, this.password).subscribe(data => {
      let status = (data.status);
      if(status >= 200 && status < 205){
        localStorage.setItem('user', this.username);
        this.router.navigate(['/user-home']);
      }else{
        this.username="";
        this.password="";
      }

    });
  }
  ngOnInit(): void {
  }

}
