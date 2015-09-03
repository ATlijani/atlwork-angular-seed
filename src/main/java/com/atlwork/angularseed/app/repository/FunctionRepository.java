package com.atlwork.angularseed.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.Function;

public interface FunctionRepository extends JpaRepository<Function, Long> {

    Function findOneByName(String name);
}
