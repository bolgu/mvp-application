import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import {AuthService} from "../../providers/auth";
import {Headers, Http} from "@angular/http";
import {JwtHelper} from "angular2-jwt";
import { Storage } from '@ionic/storage';
import {GlobalVariable} from "../../app/global";
import {FORM_DIRECTIVES} from 'angular2/common';

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
  local: Storage = new Storage();
  user: string;

  constructor(public navCtrl: NavController, public navParams: NavParams, private http: Http) {
    this.auth = AuthService;
    this.local.get('profile').then(profile => {
      this.user = JSON.parse(profile);
    }).catch(error => {
      console.log(error);
    });
  }


  login(credentials) {
    this.http.post(this.LOGIN_URL, JSON.stringify(credentials), { headers: this.contentHeader })
      .map(res => res.json())
      .subscribe(
        data => this.authSuccess(data.id_token),
        err => this.error = err
      );
  }

  signup(credentials) {
    this.http.post(this.SIGNUP_URL, JSON.stringify(credentials), { headers: this.contentHeader })
      .map(res => res.json())
      .subscribe(
        data => this.authSuccess(data.id_token),
        err => this.error = err
      );
  }

  logout() {
    this.local.remove('id_token');
    this.user = null;
  }

  authSuccess(token) {
    this.error = null;
    this.local.set('id_token', token);
    this.user = this.jwtHelper.decodeToken(token).username;
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad IndexPage');
  }

}
