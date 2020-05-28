import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService{
	
  constructor(private userService: UserService) { this.valid=false;}
  username: string;
  valid: boolean;
  authenticate(username): any  {
  }

  isUserLoggedIn() {
    let user = localStorage.getItem('username')
    return !(user === null);
  }

  logOut() {
    localStorage.removeItem('username');
  }
}
