package com.supercode.listener;

import com.supercode.enums.AuditEventType;
import com.supercode.enums.Result;
import com.supercode.service.AuditService;
import com.supercode.utils.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

/**
 * Listener for db transactions
 */
public class AuditEntityListener {

    private static final Logger logger = LoggerFactory.getLogger(AuditEntityListener.class);

    /**
     * calls before save
     * @param object
     * @throws JsonProcessingException
     */
    @PrePersist
    public void prePersist(Object object)  throws JsonProcessingException{
        perform(object, AuditEventType.INSERT);
    }

    /**
     * calls before update
     * @param object
     * @throws JsonProcessingException
     */
    @PreUpdate
    public void preUpdate(Object object) throws JsonProcessingException{
        perform(object, AuditEventType.UPDATE);
    }

    /**
     * calls before delete
     * @param object
     * @throws JsonProcessingException
     */
    @PreRemove
    public void preRemove(Object object) throws JsonProcessingException{
        perform(object, AuditEventType.DELETE);
    }

    /**
     * creates audit details and insert in audit
     * @param object
     * @param action
     * @throws JsonProcessingException
     */
    @Transactional(Transactional.TxType.MANDATORY)
    private void perform(Object object, AuditEventType action) throws JsonProcessingException{
        logger.debug("performing audit action : " + action.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.writeValueAsString(object);
        AuditService auditService= BeanUtil.getBean(AuditService.class);
        auditService.createAuditEntry(action, jsonValue, Result.PASSED);
    }
}

