import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenRequest} from '../TokenRequest';
import {RepositoryURLDTO} from '../RepositoryURLDTO';
import {AccessTokenDTO} from '../AccessTokenDTO';
import {ValidationTokenRequest} from '../ValidationTokenRequest';

const BASE_URL = 'http://localhost:8080/api/';


@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) {
  }


  validateToken(validateTokenRequest: ValidationTokenRequest): Observable<boolean> {
    return this.http.post<boolean>(BASE_URL + 'validate-token', validateTokenRequest);
  }

  saveRepository(saveRepositoryURLAndToken: TokenRequest): Observable<string> {
    return this.http.post<string>(BASE_URL + 'save', saveRepositoryURLAndToken, {responseType: 'text' as 'json'});
  }

  getAllRepositories(): Observable<RepositoryURLDTO[]> {
    return this.http.get<RepositoryURLDTO[]>(BASE_URL + 'repositories', {
      headers: {'Cache-Control': 'no-cache', 'Pragma': 'no-cache'},
    });
  }

  deleteRepositoryById(id: number): Observable<void> {
    return this.http.delete<void>(BASE_URL + `repositories/${id}`);
  }

  getAllAccessTokens(): Observable<AccessTokenDTO[]> {
    return this.http.get<AccessTokenDTO[]>(BASE_URL + 'access-tokens');
  }

  deleteAccessTokeById(id: number): Observable<void> {
    return this.http.delete<void>(BASE_URL + `access-tokens/${id}`);
  }

}




