import { UserService } from '../service/user.service';
import { User } from '../user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import custom validator to validate that password and confirm password fields match.
import { MustMatch } from '../service/must-match.validator';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  changePasswordForm: FormGroup;
  submitted = false;
  validCurrentPassword = true;
  currentpassWord: string;
  newpassWord: string;
  confirmnewpassWord: string;
  PasswordChangeSuccess = false;

  constructor(private userService: UserService,private formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
	this.changePasswordForm = this.formBuilder.group(
	{currentpassWord: ['', Validators.required],
	 newpassWord: ['', Validators.required, Validators.minLength(6)],
	 confirmnewpassWord: ['', Validators.required]},
	{
            validator: MustMatch('newpassWord', 'confirmnewpassWord')
        });
  }

    // convenience getter for easy access to form fields
    get f() { return this.changePasswordForm.controls; }


  savepassword(){
    let user = localStorage.getItem('username');
    console.log("Loggedin User = " + user + " New password = " + this.newpassWord );
    this.userService.updatePassword(user, this.newpassWord)
      .subscribe(data => console.log(data), error => console.log(error));
    //this.gotoList();
  }


  handlechangePassword() {

	this.submitted = true;
        // stop here if form is invalid
        if (this.changePasswordForm.invalid) {
            return;
        }
		let user = localStorage.getItem('username');
		this.userService.getUserByEmail(user)
      		.subscribe(data => 
		{
        			console.log("entered password = " + this.currentpassWord );
        			console.log("fetched password = " + data.passWord);
				if (this.currentpassWord !== data.passWord)
				{
					this.validCurrentPassword = false;
      					this.PasswordChangeSuccess = false;
				}
				else
				{
					this.validCurrentPassword = true;
					this.PasswordChangeSuccess = true;
					this.savepassword();
				}
        	}, 
		() => {
                    
    			});
  }

  gotoList() {
    this.router.navigate(['/']);
  }
 
      onReset() {
        this.submitted = false;
        this.changePasswordForm.reset();
    }


}
