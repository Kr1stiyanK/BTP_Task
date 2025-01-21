import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DataService} from '../../service/data.service';
import {TokenRequest} from '../../TokenRequest';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-fetch-url',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './fetch-url.component.html',
  styleUrl: './fetch-url.component.css'
})
export class FetchURLComponent implements OnInit {
  urlForm: FormGroup;
  isTokenValid: boolean = false;
  isValidating: boolean = false;
  message: string | null = null;
  isSuccess: boolean = false;


  constructor(private fb: FormBuilder, private ds: DataService) {
    this.urlForm = this.fb.group({
      url: ['', [Validators.required]],
      accessToken: ['', [Validators.required]]
    })
  }

  ngOnInit(): void {
  }

  validateAccessToken() {
    this.isValidating = true;

    const tokenRequest: TokenRequest = this.urlForm.value;
    this.ds.validateToken(tokenRequest).subscribe({
      next: (isValid) => {
        this.isTokenValid = isValid;
        this.isValidating = false;
      }, error: (err) => {
        console.error('Error validating token', err);
        this.isTokenValid = false;
        this.isValidating = false;
      }
    })
  }

  saveRepository() {
    const tokenRequest: TokenRequest = this.urlForm.value;
    this.ds.saveRepository(tokenRequest).subscribe({
      next: (response: string) => {
        this.isSuccess = true;
        this.message = response;
        this.urlForm.reset();
        this.isTokenValid = false;
      }, error: (err) => {
        this.isSuccess = false;
        this.message = err.error;
        console.error(err);
      }
    })
  }


}
