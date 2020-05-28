import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountriesService {
  constructor(private http:HttpClient) { }
  allCountries(): Observable<any>{
    return this.http.get("../../assets/files/countryinfo.json");
  }
  allCountryCodes():Observable<any>{
    return this.http.get("../../assets/files/countrycodes.json");
  }
}
