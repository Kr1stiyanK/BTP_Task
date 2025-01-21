import {RepositoryURLDTO} from './RepositoryURLDTO';

export interface AccessTokenDTO {
  id: number;
  platform: string;
  repositories: RepositoryURLDTO[];
}
