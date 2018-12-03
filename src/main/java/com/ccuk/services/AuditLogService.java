package com.ccuk.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.ccuk.model.AuditLog;

@Service
@PropertySource("classpath:application.properties")
public class AuditLogService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.queuename}")
	private String AUDIT_MESSAGE_QUEUE;
	
	private static final Logger log = LoggerFactory.getLogger(AuditLogService.class);
	
	public AuditLogService(RabbitTemplate rabbitTemplate, String SFG_MESSAGE_QUEUE) {
		this.rabbitTemplate = rabbitTemplate;
		this.AUDIT_MESSAGE_QUEUE = SFG_MESSAGE_QUEUE;
	}
	public AuditLogService() {
		
	}
	public void postAuditLogData(AuditLog auditLog) {
		rabbitTemplate.convertAndSend(AUDIT_MESSAGE_QUEUE, auditLog);
		log.info("Pushed Audit Log details to queue : " + auditLog);
	}
}
