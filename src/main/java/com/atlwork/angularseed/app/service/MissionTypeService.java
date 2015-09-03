package com.atlwork.angularseed.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlwork.angularseed.app.domain.MissionType;
import com.atlwork.angularseed.app.repository.MissionTypeRepository;

@Service
@Transactional
public class MissionTypeService {

    @Autowired
    private MissionTypeRepository userRepository;

    @Transactional(readOnly = true)
    public List<MissionType> list() {
	return userRepository.findAll();
    }

    public MissionType save(MissionType missionType) {
	return userRepository.save(missionType);
    }

    @Transactional(readOnly = true)
    public MissionType read(Long id) {
	return userRepository.getOne(id);
    }

    public void delete(Long id) {
	userRepository.delete(id);
    }
}
