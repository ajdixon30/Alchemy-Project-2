import { AdminService } from 'src/app/Services/admin.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  public adminService: AdminService;

  username: string="";
  password: string="";

  constructor(_adminService: AdminService, private router: Router) {
    this.adminService = _adminService;
  }

  onLogin(){

    this.adminService.getUser(this.username, this.password).subscribe(data=> {
      let status = (data.status);
      if(status >= 200 && status < 205){
        this.router.navigate(['/admin-home']);
      }else{

      }

    });
  }
  ngOnInit(): void {
  }

}
