import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';



export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {

  msgFlag = false;
  usernameFormControl = new FormControl('',[
    Validators.required,
  ]);
  passwordFormControl = new FormControl('',[
    Validators.required,
  ]);

  constructor(private _snackBar: MatSnackBar,private userService: UserService,private router: Router) { 
  }

  ngOnInit(): void {
  }
  login(){
    this.userService.getUserByEmail(this.usernameFormControl.value)
      .subscribe(data => {
        if (this.usernameFormControl.value === data.emailId && this.passwordFormControl.value === data.passWord && data.id==1) {
          localStorage.setItem('username', this.usernameFormControl.value);
          this.router.navigate(['users']);
         } }, () => {
            this.openSnackBar("Invalid credential","Close");
          });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
