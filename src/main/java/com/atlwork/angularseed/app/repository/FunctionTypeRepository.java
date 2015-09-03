package com.atlwork.angularseed.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.FunctionType;

public interface FunctionTypeRepository extends JpaRepository<FunctionType, Long> {

    FunctionType findOneByName(String name);
}
