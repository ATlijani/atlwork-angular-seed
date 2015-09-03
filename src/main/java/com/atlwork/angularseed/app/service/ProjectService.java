package com.atlwork.angularseed.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlwork.angularseed.app.domain.Project;
import com.atlwork.angularseed.app.repository.ProjectRepository;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<Project> list() {
	return projectRepository.findAll();
    }

    public Project save(Project project) {
	return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public Project read(Long id) {
	return projectRepository.getOne(id);
    }

    public void delete(Long id) {
	projectRepository.delete(id);
    }
}
