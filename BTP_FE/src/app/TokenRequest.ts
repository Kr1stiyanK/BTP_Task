export interface TokenRequest {
  url: string;
  accessToken: string;
  visibility: 'PUBLIC' | 'PRIVATE';
}
