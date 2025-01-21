import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DataService} from '../../service/data.service';
import {TokenRequest} from '../../TokenRequest';

@Component({
  selector: 'app-fetch-url',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './fetch-url.component.html',
  styleUrl: './fetch-url.component.css'
})
export class FetchURLComponent implements OnInit {
  urlForm: FormGroup;
  isTokenValid: boolean = false;
  isValidating: boolean = false;


  constructor(private fb: FormBuilder, private ds: DataService) {
    this.urlForm = this.fb.group({
      url: ['', [Validators.required]],
      accessToken: ['', [Validators.required]]
    })
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

  ngOnInit(): void {
  }

}
