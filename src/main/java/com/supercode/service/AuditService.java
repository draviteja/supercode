package com.supercode.service;


import com.supercode.entity.Audit;
import com.supercode.enums.AuditEventType;
import com.supercode.enums.Result;
import com.supercode.exception.ApiException;
//import com.supercode.model.AuditDto;
//import com.supercode.model.PageRequestDto;
//import com.supercode.model.PageResponseDto;

import java.util.Collection;
import java.util.List;

/**
 * Interface for audit services
 */
public interface AuditService {

    Collection<Audit> getAllAuditLogs() throws ApiException;

    //PageResponseDto<AuditDto> getAuditLogsByDate(PageRequestDto requestDto, String fromDate, String toDate) throws ApiException;

    void createAuditEntry(AuditEventType eventType, String eventDetails, Result auditEventResult);
}
