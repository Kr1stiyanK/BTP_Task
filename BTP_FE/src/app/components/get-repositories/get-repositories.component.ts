import {Component, OnInit} from '@angular/core';
import {RepositoryURLDTO} from '../../RepositoryURLDTO';
import {DataService} from '../../service/data.service';
import {NgForOf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-get-repositories',
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './get-repositories.component.html',
  standalone: true,
  styleUrl: './get-repositories.component.css'
})
export class GetRepositoriesComponent implements OnInit {
  repositories: RepositoryURLDTO[] = [];

  constructor(private ds: DataService) {
  }

  ngOnInit(): void {
    this.loadRepositories();
  }

  loadRepositories() {
    this.ds.getAllRepositories().subscribe({
      next: (data) => {
        this.repositories = data;
      }, error: (err) => {
        console.error('Error fetching repositories, ', err);
      }
    });
  }

  deleteRepository(id: number) {
    this.ds.deleteRepositoryById(id).subscribe({
      next: () => {
        alert('Repository deleted successfully');
        this.loadRepositories();
      },
      error: (err) => {
        console.error('Error deleting repository, ', err);
      },
    });
  }

}
