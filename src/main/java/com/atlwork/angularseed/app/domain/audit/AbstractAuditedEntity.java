package com.atlwork.angularseed.app.domain.audit;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Audited
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractAuditedEntity {

    @CreatedDate
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    private DateTime createdDate = DateTime.now();

    public DateTime getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
	this.createdDate = createdDate;
    }

}
