package com.atlwork.angularseed.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findOneByName(String name);
}
