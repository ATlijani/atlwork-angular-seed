package com.atlwork.angularseed.app.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atlwork.angularseed.app.domain.Mission;
import com.atlwork.angularseed.app.service.MissionService;

@RestController
@RequestMapping("/api")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @RequestMapping(value = "{loginId}/missions", method = RequestMethod.GET)
    public Set<Mission> getUserMissions(@PathVariable(value = "loginId") String login) {
	return missionService.listUserMissions(login);
    }

    @RequestMapping(value = "{loginId}/missions", method = RequestMethod.POST)
    public Mission create(@PathVariable(value = "loginId") String login, @Valid @RequestBody Mission mission) {
	return missionService.create(login, mission);
    }

    @RequestMapping(value = "/missions/{id}", method = RequestMethod.GET)
    public Mission get(@PathVariable Long id) {
	return missionService.read(id);
    }

    @RequestMapping(value = "/missions", method = RequestMethod.PUT)
    public Mission update(@Valid @RequestBody Mission mission) {
	return missionService.update(mission);
    }

    @RequestMapping(value = "missions/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
	missionService.delete(id);
    }

}
