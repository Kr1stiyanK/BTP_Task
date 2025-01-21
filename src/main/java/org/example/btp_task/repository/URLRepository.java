package org.example.btp_task.repository;

import org.example.btp_task.entities.RepositoryURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<RepositoryURL, Long> {

    List<RepositoryURL> findAll();
}
