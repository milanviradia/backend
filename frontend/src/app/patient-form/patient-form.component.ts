import { Component, OnInit,ViewChild } from '@angular/core';
import { PatientFormService } from '../service/patient-form.service'
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import { Address } from '../address';
import {FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { CountriesService } from '../service/countries.service';


/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit {

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  stateInfo: any[] = [];
  countryInfo: any[] = [];
  cityInfo: any[] = [];
  address :Address = new Address();
  donnerList
  donnerListArray:any[] = [];
  Donnersloaded
  noDonnerFound
  emergencyAddress;
  countryId;
  stateId;
  displayedCol = ['name','mobileNumber','bloodGroup']
  options: FormGroup;
  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto');
  bloodGroupFormControl = new FormControl('',[
    Validators.required,
  ]);
  cityFormControl = new FormControl('',[
    Validators.required,
  ]);
  stateFormControl = new FormControl('',[
    Validators.required,
  ]);
  countryFormControl = new FormControl('',[
    Validators.required,
  ]);
  postalcodeFormControl =  new FormControl('',[
    Validators.required,
  ]);
  matcher = new MyErrorStateMatcher();

  constructor(fb: FormBuilder,private patientFormService:PatientFormService,private country:CountriesService,) { 
    this.noDonnerFound=false;
    this.donnerList=[];
    this.options = fb.group({
      hideRequired: this.hideRequiredControl,
      floatLabel: this.floatLabelControl,
    });
  }
  ngOnInit(): void {
    this.Donnersloaded=false;
    this.getCountries();
  }

  getCountries(){
    this.country.allCountries().
    subscribe(
      data2 => {
        this.countryInfo=data2.Countries;
      },
      err => console.log(err),
      () => console.log('complete')
    )
  }

  onChangeCountry(countryValue) {
    this.countryId = countryValue;
    this.address.country = this.countryInfo[this.countryId].CountryName;
    this.stateInfo=this.countryInfo[countryValue].States;
    this.cityInfo=this.stateInfo[0].Cities;
  }

  onChangeState(stateValue) {
    this.stateId = stateValue;
    this.address.state = this.countryInfo[this.countryId].States[this.stateId].StateName;
    this.cityInfo=this.stateInfo[stateValue].Cities;
  }

  onChangeCity(value){
    this.address.city=value;
  }

  fatchDonners(){
    if(this.bloodGroupFormControl.status=="VALID" && this.countryFormControl.status=="VALID" &&this.stateFormControl.status=="VALID"
     && this.cityFormControl.status=="VALID" && this.postalcodeFormControl.status=="VALID"){
      this.Donnersloaded=true;
      this.patientFormService.fatchDonnersService(this.address).subscribe(data =>
        { 
          this.donnerListArray=<any>data;
          if(this.donnerListArray.length){
            this.donnerList = new MatTableDataSource(this.donnerListArray);
            this.donnerList.sort = this.sort;
            this.donnerList.paginator = this.paginator;
            
          }else{
            this.noDonnerFound=true;
          }
      });
    }
  }

    list(){
        this.router.navigate(['']);
     }

  sendRequest(){
    this.patientFormService.sendRequest({"users":this.donnerListArray,"address":this.emergencyAddress}).subscribe(data =>
    { 
      
    });
    this.ngOnInit();
  }
}
