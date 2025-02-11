import {Component, OnInit} from '@angular/core';
import {AccessTokenDTO} from '../../AccessTokenDTO';
import {DataService} from '../../service/data.service';
import {RouterLink} from '@angular/router';
import {RepositoryURLDTO} from '../../RepositoryURLDTO';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-get-access-tokens',
  imports: [
    RouterLink,
    NgForOf
  ],
  templateUrl: './get-access-tokens.component.html',
  standalone: true,
  styleUrl: './get-access-tokens.component.css'
})
export class GetAccessTokensComponent implements OnInit {
  accessTokens: AccessTokenDTO[] = [];

  constructor(private ds: DataService) {
  }

  ngOnInit(): void {
    this.loadAccessTokens();
  }


  loadAccessTokens() {
    this.ds.getAllAccessTokens().subscribe({
      next: (data) => {
        this.accessTokens = data;
      }, error: (err) => {
        console.error('Error fetching access tokens, ', err);
      }
    });
  }

  deleteAccessTokenById(id: number, identifier: string) {
    const confirmation = confirm(`Are you sure you want to delete ${identifier}?\nAll associated PRIVATE repositories will also be deleted.`);

    if (confirmation) {
      this.ds.deleteAccessTokeById(id).subscribe({
        next: () => {
          alert(`Access Token ${identifier} and associated private repositories deleted.`);
          this.loadAccessTokens();
        }, error: (err) => {
          console.error('Error deleting access token, ', err);
        }
      });
    }
  }

  getRepositoryURLs(repositories: RepositoryURLDTO[]): string {
    if (!repositories || repositories.length == 0) {
      return 'No URLs associated found';
    }
    return repositories.map(repo => repo.url).join('; ');
  }

}
