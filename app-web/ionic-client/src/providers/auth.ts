import {tokenNotExpired} from 'angular2-jwt';
import {Injectable} from "@angular/core";

@Injectable()
export class AuthService {
  constructor() {}

  public static authenticated() {
    return tokenNotExpired();
  }
}
