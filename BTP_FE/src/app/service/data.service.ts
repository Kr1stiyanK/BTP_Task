import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenRequest} from '../TokenRequest';

const BASE_URL = 'http://localhost:8080/api/';


@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) {
  }


  validateToken(validateTokenRequest: TokenRequest): Observable<boolean> {
    return this.http.post<boolean>(BASE_URL + 'validate-token', validateTokenRequest);
  }

  saveRepository(saveRepositoryURLAndToken: TokenRequest): Observable<string> {
    return this.http.post<string>(BASE_URL + 'save', saveRepositoryURLAndToken, {responseType: 'text' as 'json'});
  }

}




