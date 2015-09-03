package com.atlwork.angularseed.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlwork.angularseed.app.domain.Function;
import com.atlwork.angularseed.app.repository.FunctionRepository;

@Service
@Transactional
public class FunctionService {

    @Autowired
    private FunctionRepository userRepository;

    @Transactional(readOnly = true)
    public List<Function> list() {
	return userRepository.findAll();
    }

    public Function save(Function function) {
	return userRepository.save(function);
    }

    @Transactional(readOnly = true)
    public Function read(Long id) {
	return userRepository.findOne(id);
    }

    public void delete(Long id) {
	userRepository.delete(id);
    }
}
