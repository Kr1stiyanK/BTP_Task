import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DataService} from '../../service/data.service';
import {TokenRequest} from '../../TokenRequest';
import {RouterLink} from '@angular/router';
import {ValidationTokenRequest} from '../../ValidationTokenRequest';

@Component({
  selector: 'app-fetch-url',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './fetch-url.component.html',
  styleUrl: './fetch-url.component.css'
})
export class FetchURLComponent implements OnInit {
  urlForm: FormGroup;
  isPrivateRepo: boolean = false;
  isTokenValid: boolean = false;
  isValidating: boolean = false;
  message: string | null = null;
  isSuccess: boolean = false;


  constructor(private fb: FormBuilder, private ds: DataService) {
    this.urlForm = this.fb.group({
      url: ['', [Validators.required]],
      accessToken: ['', [Validators.required]],
      visibility: ['PUBLIC', [Validators.required]]
    })
  }

  ngOnInit(): void {
    this.onVisibilityChange();
  }

  onVisibilityChange() {
    this.isPrivateRepo = this.urlForm.get('visibility')?.value === 'PRIVATE';

    if (!this.isPrivateRepo) {
      this.urlForm.get('accessToken')?.setValue('');
      this.isTokenValid = false;
    }
  }

  isSaveDisabled(): boolean {
    const urlValid = this.urlForm.get('url')!.valid;
    const accessTokenValid = this.isPrivateRepo ? this.isTokenValid : true;

    return !urlValid || !accessTokenValid;
  }

  validateAccessToken() {
    this.isValidating = true;

    const validateTokenRequest: ValidationTokenRequest = {
      url: this.urlForm.get('url')!.value,
      accessToken: this.urlForm.get('accessToken')!.value
    };
    this.ds.validateToken(validateTokenRequest).subscribe({
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
    const tokenRequest: TokenRequest = {
      url: this.urlForm.get('url')!.value,
      accessToken: this.urlForm.get('accessToken')!.value || "",
      visibility: this.urlForm.get('visibility')!.value
    }
    // tokenRequest.visibility = this.isPrivateRepo ? 'PRIVATE' : 'PUBLIC';
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
