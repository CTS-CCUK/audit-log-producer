package com.ccuk.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ccuk.model.AuditLog;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
public class AuditLogServiceTest {

	private AuditLogService auditLogService;
	
    @MockBean
    private RabbitTemplate rabbitTemplate;
	
    @Before
    public void setUp() {
    	auditLogService = new AuditLogService(rabbitTemplate, "AuditLogQueue");
    }
    
	@Test
	public void verifyPostAuditLogData() throws Exception{
    	
		AuditLog auditLog = getDummyAuditLogDetails();
        auditLogService.postAuditLogData(auditLog);
        verify(rabbitTemplate, times(1)).convertAndSend("AuditLogQueue", auditLog);
	}
	
	
	private AuditLog getDummyAuditLogDetails()
	{
		 PodamFactory factory = new PodamFactoryImpl();
		 AuditLog auditLog = factory.manufacturePojo(AuditLog.class);
		 return auditLog;
		
	}

}
