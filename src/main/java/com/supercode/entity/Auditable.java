package com.supercode.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import java.time.Instant;

import java.io.Serializable;
import java.util.Date;

/**
 * Auditable fields for database tables
 * @param <String>
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<String> implements Serializable {

    @CreatedBy
    @Column(name = "CREATED_BY")
    protected String createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "CREATION_DATE")
    protected Date creationDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    protected String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE")
    protected Date lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
