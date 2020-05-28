import { UserService } from '../service/user.service';
import { User } from '../user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CountriesService } from '../service/countries.service';
// import custom validator to validate that password and confirm password fields match
import { MustMatch } from '../service/must-match.validator';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  stateInfo: any[] = [];
  countryInfo: any[] = [];
  cityInfo: any[] = [];
  bloodGroupList: any = ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O', 'O-']
  registerForm: FormGroup;
  submitted = false;
  user: User = new User();
  validRegister = true;
  isEmpty = false;
  errorMessage = 'User already exists';
  successMessage: string;
  confirmpassWord: string;
  RegisterSuccess = false;
  countryId;
  stateId;
  countryCodeInfo:any[] = [];

  constructor(private userService: UserService, private country: CountriesService,
    private formBuilder: FormBuilder, private router: Router,public dialog: MatDialog) {
     }
  
     openDialog() {
      this.dialog.open(DialogElementsExampleDialog);
    }

  ngOnInit() {
    this.getCountries();
    this.getCountryCodes();
    this.registerForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.pattern("^[a-z A-Z]{3,15}$")]],
      emailId: ['', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]],
      mobileNumber: ['', [Validators.required, Validators.pattern("[0-9 ]{10}$")]],
      age: ['', [Validators.required, Validators.pattern("[0-9 ]{2}$")]],
      bloodGroup: ['', Validators.required],
      country: ['', Validators.required],
      zipcode: ['', Validators.required],
      state: ['', Validators.required],
      city: ['', Validators.required],
      usertype: ['', Validators.required],
      passWord: ['', [Validators.required, Validators.minLength(6)]],
      confirmpassWord: ['', Validators.required]
    }, {
      validator: MustMatch('passWord', 'confirmpassWord')
    });
  }
  get f() { return this.registerForm.controls; }
  newUser(): void {
    this.user = new User();
  }
  save() {
    this.userService.createUser(this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    // this.user = new User();
    if(this.user.type==1){
      console.log("in..");
      this.gotoList();
    }else{
      this.sendEmail();
      this.openDialog();
    }
  }
  handleRegister() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.userService.getUserByEmail(this.user.emailId)
      .subscribe(data => {
        if (this.user.emailId === data.emailId) {
          this.validRegister = false;
          this.RegisterSuccess = false;
        }
      },
        () => {
          this.successMessage = 'Registration Successful.';
          this.RegisterSuccess = true;
          this.save();
        });
  }
  gotoList() {
    this.router.navigate(['/login']);
  }
  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
  getCountries() {
    this.country.allCountries().
      subscribe(
        data2 => {
          this.countryInfo = data2.Countries;
        },
        err => console.log(err),
        () => console.log('country data fetched....')
      )
  }

  sendEmail() {
                this.userService.sendEmail(this.user.emailId)
      			.subscribe(data => {
      				}, () => {});	
  }

  getCountryCodes(){
    this.country.allCountryCodes().
    subscribe(
      data => {
        this.countryCodeInfo = data;
      },
      err => console.log(err),
      () => console.log('country codes data fetched....')
    )
  }
  onChangeCountry(countryValue) {
    this.countryId = countryValue;
    this.user.country = this.countryInfo[this.countryId].CountryName;
    this.stateInfo = this.countryInfo[countryValue].States;
    this.cityInfo = this.stateInfo[0].Cities;
  }
  onChangeState(stateValue) {
    this.stateId = stateValue;
    this.user.state = this.countryInfo[this.countryId].States[this.stateId].StateName;
    this.cityInfo = this.stateInfo[stateValue].Cities;
  }
  onChangeCity(value) {
    this.user.city = value;
  }
}

// @Component({
//   selector: 'dialog-elements-example-dialog',
//   templateUrl: 'dialog-elements-example-dialog.html',
// })

@Component({
  selector: 'app-dialog',
  templateUrl: '../dialog/dialog.component.html'
})
export class DialogElementsExampleDialog {}
