import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment} from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class PatientFormService {
  constructor(private http: HttpClient) { }
  fatchDonnersService(address:Object): Observable<Object>{
    console.log(address);
    return this.http.post(environment.baseUrl+"donnerList",address);
  }
  sendRequest(data){
    console.log(data);
    return this.http.post(environment.baseUrl+"sendSMS",data);
  }
}
