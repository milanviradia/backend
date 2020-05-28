import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../user';


@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  
  email: string;
  successMessage: string;
  user: User;
  valid: boolean;
  validUser = true;
  Success = false;

  constructor(private userService: UserService,
	private router: Router) { }

  ngOnInit(): void {
  }

    sendEmail() {
	if(this.email==='')
		return;

	this.validUser = true;
	this.userService.getUserByEmail(this.email)
      .subscribe(data => {
        console.log("entered email = " + this.email);
        console.log("fetched email = " + data.emailId);
	if(this.email === data.emailId)
	{
		this.Success=true;
		this.successMessage = 'Temporary password has been sent to '+this.email;
                this.userService.sendPasswordByEmail(this.email)
      			.subscribe(data => {
				
      				}, () => {});
		
	}
      }, () => {
      this.validUser = false;
      this.Success = false;
    });
  }
 
  handleForgotpassword(){
       this.router.navigate(['']);
  }

}
