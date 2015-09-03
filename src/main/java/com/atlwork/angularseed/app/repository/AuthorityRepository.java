package com.atlwork.angularseed.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findOneByName(String name);
}
