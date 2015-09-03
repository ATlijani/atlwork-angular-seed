package com.atlwork.angularseed.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Mission findOneByTitle(String title);

    Mission findOneByTitleIgnoreCase(String title);
}
