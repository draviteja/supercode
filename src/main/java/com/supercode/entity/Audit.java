package com.supercode.entity;

import com.supercode.enums.AuditEventType;
import com.supercode.enums.Result;
import com.supercode.enums.AuditEventType;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity for AUDIT_LOG table
 */
@Entity
@Table(name = "AUDIT_LOG")
public class Audit extends Auditable<String>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="LOG_ID", nullable = false)
    Long logId;

    @Column(name="EVENT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    AuditEventType eventType;

    @Column(name="EVENT_DETAILS", nullable = false)
    String eventDetails;

    @Column(name="EVENT_RESULT", nullable = false)
    @Enumerated(EnumType.STRING)
    Result eventResult;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public AuditEventType getEventType() {
        return eventType;
    }

    public void setEventType(AuditEventType eventType) {
        this.eventType = eventType;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public Result getEventResult() {
        return eventResult;
    }

    public void setEventResult(Result eventResult) {
        this.eventResult = eventResult;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "logId='" + logId + '\'' +
                ", eventType=" + eventType +
                ", eventDetails='" + eventDetails + '\'' +
                ", eventResult='" + eventResult + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return Objects.equals(logId, audit.logId) &&
                eventType == audit.eventType &&
                Objects.equals(eventDetails, audit.eventDetails) &&
                Objects.equals(eventResult, audit.eventResult);
    }

    @Override
    public int hashCode() {

        return Objects.hash(logId, eventType, eventDetails, eventResult);
    }
}
