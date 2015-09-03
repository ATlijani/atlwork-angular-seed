package com.atlwork.angularseed.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.MissionType;

public interface MissionTypeRepository extends JpaRepository<MissionType, Long> {

    MissionType findOneByName(String name);
}
