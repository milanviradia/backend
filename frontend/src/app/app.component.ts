import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Blood Bank Management Portal';
  getUrl()
  {
    return "url('http://localhost:4200/assets/images/img.png')";
  }
}


