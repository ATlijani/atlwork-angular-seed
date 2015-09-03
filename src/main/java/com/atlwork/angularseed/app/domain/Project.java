package com.atlwork.angularseed.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_PROJECT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "NAME", length = 256)
    private String name;

    @Size(min = 0, max = 256)
    @Column(name = "SUBJECT", length = 256)
    private String subject;

    @Size(min = 0, max = 256)
    @Column(name = "MANAGER_N1", length = 256)
    private String managerN1;

    @Size(min = 0, max = 256)
    @Column(name = "MANAGER_N2", length = 256)
    private String managerN2;

    @Size(min = 0, max = 256)
    @Column(name = "NB_EQUIPE", length = 256)
    private Integer nbEquipe;

    @Size(min = 0, max = 256)
    @Column(name = "LOCATION", length = 256)
    private String location;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getManagerN1() {
	return managerN1;
    }

    public void setManagerN1(String managerN1) {
	this.managerN1 = managerN1;
    }

    public String getManagerN2() {
	return managerN2;
    }

    public void setManagerN2(String managerN2) {
	this.managerN2 = managerN2;
    }

    public Integer getNbEquipe() {
	return nbEquipe;
    }

    public void setNbEquipe(Integer nbEquipe) {
	this.nbEquipe = nbEquipe;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

}
