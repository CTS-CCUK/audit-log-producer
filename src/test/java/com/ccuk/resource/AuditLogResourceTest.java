package com.ccuk.resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.ccuk.model.AuditLog;
import com.ccuk.services.AuditLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(AuditLogResource.class)
public class AuditLogResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AuditLogService auditLogService;
	
	@Test
	public void shouldPostAuditLogs() throws Exception {
    	AuditLog auditLog = getDummyAuditLogDetails();
    	
        MockHttpServletResponse response = mockMvc.perform(post("/audit-queue-service/auditlog")
                .content(new ObjectMapper().writeValueAsString(auditLog))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());

        ArgumentCaptor<AuditLog> captor = ArgumentCaptor.forClass(AuditLog.class);
        verify(auditLogService, times(1)).postAuditLogData(captor.capture());
        assertEquals(auditLog.getAnnexFileName(), captor.getValue().getAnnexFileName());
        assertEquals(auditLog.getScheduleFileName(), captor.getValue().getScheduleFileName());
        assertEquals(auditLog.getUniqueId(), captor.getValue().getUniqueId());
        assertEquals(auditLog.getUsername(), captor.getValue().getUsername());
	}
	
	
	private AuditLog getDummyAuditLogDetails()
	{
		 PodamFactory factory = new PodamFactoryImpl();
		 AuditLog auditLog = factory.manufacturePojo(AuditLog.class);
		 return auditLog;
		
	}

}
