package com.atlwork.angularseed.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atlwork.angularseed.app.domain.Project;
import com.atlwork.angularseed.app.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Project> list() {
	return projectService.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project create(@RequestBody @Valid Project project) {
	return projectService.save(project);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Project get(@PathVariable Long id) {
	return projectService.read(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Project update(@Valid @RequestBody Project project) {
	return projectService.save(project);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable Long id) {
	projectService.delete(id);
    }

}
