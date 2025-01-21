import {Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {FetchURLComponent} from './components/fetch-url/fetch-url.component';
import {GetRepositoriesComponent} from './components/get-repositories/get-repositories.component';
import {GetAccessTokensComponent} from './components/get-access-tokens/get-access-tokens.component';

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'fetch', component: FetchURLComponent},
  {path: 'get-repositories', component: GetRepositoriesComponent},
  {path: 'get-access-tokens', component: GetAccessTokensComponent}
];
