export interface RepositoryURLDTO {
  id: number;
  url: string;
  tokenPlatform: string;
  visibility: 'PUBLIC' | 'PRIVATE';
  accessTokenIdentifier: string;
}
