package com.atlwork.angularseed.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_MISSION")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNCTION_ID")
    private Function function;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNCTION_TYPE_ID")
    private FunctionType functionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MISSION_TYPE_ID")
    private MissionType missionType;

    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "TITLE", length = 256, unique = true, nullable = false)
    private String title;

    @Column(name = "TECHNICAL_SKILLS")
    private String technicalSkills;

    @Column(name = "FUNCTIONAL_SKILLS")
    private String functionalSkills;

    @Column(name = "CONTEXT")
    private String context;

    @Column(name = "APPROCHE")
    private String approche;

    @Column(name = "METHODOLOGY")
    private String methodology;

    @Column(name = "BENEFITS")
    private String benefits;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Client getClient() {
	return client;
    }

    public void setClient(Client client) {
	this.client = client;
    }

    public Project getProject() {
	return project;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    public Function getFunction() {
	return function;
    }

    public void setFunction(Function function) {
	this.function = function;
    }

    public FunctionType getFunctionType() {
	return functionType;
    }

    public void setFunctionType(FunctionType functionType) {
	this.functionType = functionType;
    }

    public MissionType getMissionType() {
	return missionType;
    }

    public void setMissionType(MissionType missionType) {
	this.missionType = missionType;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getTechnicalSkills() {
	return technicalSkills;
    }

    public void setTechnicalSkills(String technicalSkills) {
	this.technicalSkills = technicalSkills;
    }

    public String getFunctionalSkills() {
	return functionalSkills;
    }

    public void setFunctionalSkills(String functionalSkills) {
	this.functionalSkills = functionalSkills;
    }

    public String getContext() {
	return context;
    }

    public void setContext(String context) {
	this.context = context;
    }

    public String getApproche() {
	return approche;
    }

    public void setApproche(String approche) {
	this.approche = approche;
    }

    public String getMethodology() {
	return methodology;
    }

    public void setMethodology(String methodology) {
	this.methodology = methodology;
    }

    public String getBenefits() {
	return benefits;
    }

    public void setBenefits(String benefits) {
	this.benefits = benefits;
    }

}
