package com.ccuk.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccuk.model.AuditLog;
import com.ccuk.model.CountryCode;
import com.ccuk.services.AuditLogService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/audit-queue-service")
public class AuditLogResource {
	
	private static final Logger logger = LoggerFactory.getLogger(AuditLogResource.class);
	
    @Autowired
    private AuditLogService auditLogService;
    
    @ApiOperation(value = "Post Audit Log Information  to queue")
    @RequestMapping(value = "/auditlog", method = RequestMethod.POST)
    public ResponseEntity<?> postAuditLog(@RequestBody AuditLog auditLog)
    {
    	logger.info("Received Audit Log request for France : "+auditLog);
    	auditLog.setCountryCode(CountryCode.FR);
    	auditLogService.postAuditLogData(auditLog);
    	HttpHeaders headers = new HttpHeaders();
    	return new ResponseEntity<String>(headers, HttpStatus.NO_CONTENT);
    }

}
