import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8087/api/users';

  constructor(private http: HttpClient) { }

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/id/${id}`);
  }
	
  getUserByEmail(emailId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/email/${emailId}`);
  }

  sendEmail(emailId: string): Observable<any> {
    console.log(emailId);
    return this.http.get(`${this.baseUrl}/sendemail/${emailId}`);
  }

   sendPasswordByEmail(emailId: string): Observable<any> {
    console.log(emailId);
    return this.http.get(`${this.baseUrl}/sendpasswordemail/${emailId}`);
  }

  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user);
  }

   updatePassword(emailId: string, value: string): Observable<Object> {
    return this.http.put(`${this.baseUrl}/updatepassword/${emailId}`, value);
  }

  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/id/${id}`, value);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/id/${id}`, {responseType: 'text' });
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
