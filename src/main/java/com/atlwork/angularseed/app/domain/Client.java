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
@Table(name = "T_CLIENT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ACCOUNT", length = 256)
    private String account;

    @Size(min = 0, max = 256)
    @Column(name = "ACTIVITY", length = 256)
    private String activity;

    @Size(min = 0, max = 256)
    @Column(name = "DEPARTEMENT", length = 256)
    private String departement;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAccount() {
	return account;
    }

    public void setAccount(String account) {
	this.account = account;
    }

    public String getActivity() {
	return activity;
    }

    public void setActivity(String activity) {
	this.activity = activity;
    }

    public String getDepartement() {
	return departement;
    }

    public void setDepartement(String departement) {
	this.departement = departement;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	Client client = (Client) o;

	if (account != null ? !account.equals(client.account) : client.account != null) {
	    return false;
	}

	return true;
    }

    @Override
    public int hashCode() {
	return account != null ? account.hashCode() : 0;
    }

}
