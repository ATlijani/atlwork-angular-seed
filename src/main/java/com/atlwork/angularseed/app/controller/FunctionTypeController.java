package com.atlwork.angularseed.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atlwork.angularseed.app.domain.FunctionType;
import com.atlwork.angularseed.app.service.FunctionTypeService;

@RestController
@RequestMapping("/api/function-types")
public class FunctionTypeController {

    @Autowired
    private FunctionTypeService functionTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FunctionType> list() {
	return functionTypeService.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    public FunctionType create(@RequestBody @Valid FunctionType functionType) {
	return functionTypeService.save(functionType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public FunctionType get(@PathVariable Long id) {
	return functionTypeService.read(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public FunctionType update(@Valid @RequestBody FunctionType functionType) {
	return functionTypeService.save(functionType);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable Long id) {
	functionTypeService.delete(id);
    }

}
