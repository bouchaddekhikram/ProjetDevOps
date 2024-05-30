import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  user = {
    username:'',
    password:''
  
  }
  constructor(public _userAuth : AuthService){}


  login(){
    this._userAuth.signIn(this.user).subscribe(
      (res:HttpErrorResponse)=>{
        console.log(res);
        
      },
        (err: HttpErrorResponse)=>{
          console.log(err);
          
        }
    )
  }


}
