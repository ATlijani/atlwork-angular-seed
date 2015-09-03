package com.atlwork.angularseed.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findOneByAccount(String account);
}
