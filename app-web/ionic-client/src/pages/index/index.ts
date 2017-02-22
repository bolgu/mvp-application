import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import {AuthService} from "../../providers/auth";
import {Headers, Http} from "@angular/http";
import {JwtHelper} from "angular2-jwt";
import {GlobalVariable} from "../../app/global";

@Component({
  selector: 'page-index',
  templateUrl: 'index.html'
})
export class IndexPage {

  LOGIN_URL: string = GlobalVariable.BASE_API_URL + "/api/login";
  SIGNUP_URL: string = GlobalVariable.BASE_API_URL + "/api/signup";

  auth: AuthService;
  // When the page loads, we want the Login segment to be selected
  authType: string = "login";
  // We need to set the content type for the server
  contentHeader: Headers = new Headers({"Content-Type": "application/json"});
  error: string;
  jwtHelper: JwtHelper = new JwtHelper();
  user: string;
  loginCreds: any = {}

  constructor(public navCtrl: NavController, public navParams: NavParams, private http: Http) {
    this.auth = AuthService;
    let token = localStorage.getItem('id_token');
    if(token) {
      this.user = this.jwtHelper.decodeToken(token).sub;
    }
  }

  login(credentials) {
    // alert(JSON.stringify(credentials))
    // credentials = {username:"admin", password: "Password123!"}
    if(credentials.username == "" || credentials.password == "") {
      this.error = "User name and password should be provided!"
    } else {
      this.http.post(this.LOGIN_URL, JSON.stringify(credentials), { headers: this.contentHeader })
        .map(res => res.json())
        .subscribe(
          data => this.authSuccess(data.access_token),
          err => {
            console.log(err)
            if(err.status == 401) {
              this.error = "Invalid password and/or username.";
            }
            if(err.status == 400) {
              this.error = "Invalid call to the server.";
            }
          }
      );
    }
  }

  signup(credentials) {
    this.http.post(this.SIGNUP_URL, JSON.stringify(credentials), { headers: this.contentHeader })
      .map(res => res.json())
      .subscribe(
        data => this.authSuccess(data.access_token),
        err => {
          console.log(err)
          if(err.status == 401) {
            this.error = "Invalid password and/or username.";
          }
          if(err.status == 400) {
            this.error = "Invalid call to the server.";
          }
        }
      );
  }

  logout() {
    localStorage.removeItem('id_token');
    this.user = null;
  }

  authSuccess(token) {
    this.error = null;
    localStorage.setItem('id_token', token);
    var tokenData = this.jwtHelper.decodeToken(token);
    this.user = tokenData.sub
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad IndexPage');
  }

}
