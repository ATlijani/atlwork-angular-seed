package com.atlwork.angularseed.app.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlwork.angularseed.app.domain.Mission;
import com.atlwork.angularseed.app.domain.User;
import com.atlwork.angularseed.app.exceptions.DataNotFoundException;
import com.atlwork.angularseed.app.exceptions.UniqueConstraintException;
import com.atlwork.angularseed.app.repository.MissionRepository;
import com.atlwork.angularseed.app.repository.UserRepository;

@Service
@Transactional
public class MissionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public Set<Mission> listUserMissions(String login) {
	return userRepository.findOneByLogin(login).getMissions();
    }

    public Mission create(String login, Mission mission) {

	checkUniqueConstraint(mission);

	User user = getUserByLogin(login);
	user.getMissions().add(mission);

	userRepository.save(user);

	return mission;
    }

    @Transactional(readOnly = true)
    public Mission read(Long id) {
	return missionRepository.findOne(id);
    }

    public Mission update(Mission mission) {

	Mission existMission = missionRepository.findOneByTitleIgnoreCase(mission.getTitle());
	if (existMission != null && existMission.getId() != mission.getId())
	    throw new UniqueConstraintException();

	Mission missionDB = missionRepository.findOne(mission.getId());
	missionDB.setApproche(mission.getApproche());
	missionDB.setBenefits(mission.getBenefits());
	missionDB.setClient(mission.getClient());
	missionDB.setContext(mission.getContext());
	missionDB.setFunction(mission.getFunction());
	missionDB.setFunctionalSkills(mission.getFunctionalSkills());
	missionDB.setFunctionType(mission.getFunctionType());
	missionDB.setMethodology(mission.getMethodology());
	missionDB.setMissionType(mission.getMissionType());
	missionDB.setProject(mission.getProject());
	missionDB.setTechnicalSkills(mission.getTechnicalSkills());
	missionDB.setTitle(mission.getTitle());
	missionDB.setUser(mission.getUser());

	return missionRepository.save(mission);
    }

    public void delete(Long id) {
	Mission mission = missionRepository.findOne(id);
	User user = mission.getUser();
	user.getMissions().remove(mission);
	userRepository.save(user);
    }

    private User getUserByLogin(String login) {

	User user = userRepository.findOneByLogin(login);
	if (user == null)
	    throw new DataNotFoundException();

	return user;
    }

    private void checkUniqueConstraint(Mission mission) {
	Mission existMission = missionRepository.findOneByTitleIgnoreCase(mission.getTitle());
	if (existMission != null)
	    throw new UniqueConstraintException();
    }
}
