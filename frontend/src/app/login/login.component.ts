import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { AuthenticationService } from '../service/authentication.service';
import { User } from '../user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  email: string;
  password: string;
  validLogin = true;

  constructor(private userService: UserService, private formBuilder: FormBuilder,
    private router: Router, private authenticateservice: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }
  get f() { return this.loginForm.controls; }
  handleLogin() {
    this.submitted = true;
    // this.validLogin = false;
    if (this.loginForm.invalid) {
      return;
    }

    this.userService.getUserByEmail(this.email)
      .subscribe(data => {
        console.log("entered email = " + this.email + "    entered password = " + this.password);
        console.log("fetched email = " + data.emailId + "    fetched password = " + data.passWord);
        if (this.email === data.emailId && this.password === data.passWord) {
          this.validLogin = true;
          localStorage.setItem('username', this.email);
          if(data.id==1)
            this.router.navigate(['users']);
          else if(data.type==1)
            this.router.navigate(['patientform']);
        }

      }, () => {
      });
  }
  handleForgotpassword() {
    this.router.navigate(['/forgotpassword']);
  }
}
