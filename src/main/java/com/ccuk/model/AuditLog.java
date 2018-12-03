package com.ccuk.model;

import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.ccuk.constants.ApplicationConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class AuditLog {
	
	private String id;
	private String uniqueId;
	private String annexFileName;
	private boolean annexStatus;
	private String scheduleFileName;
	private boolean scheduleStatus;
	private String username;
	private String system;
	

	@JsonFormat(pattern= ApplicationConstant.ISO_DATE_TIME_FORMAT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date submittedDateTime;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private List<Date> dateScheduled;
	private CountryCode countryCode;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getAnnexFileName() {
		return annexFileName;
	}
	public void setAnnexFileName(String annexFileName) {
		this.annexFileName = annexFileName;
	}
	public boolean isAnnexStatus() {
		return annexStatus;
	}
	public void setAnnexStatus(boolean annexStatus) {
		this.annexStatus = annexStatus;
	}
	public String getScheduleFileName() {
		return scheduleFileName;
	}
	public void setScheduleFileName(String scheduleFileName) {
		this.scheduleFileName = scheduleFileName;
	}
	public boolean isScheduleStatus() {
		return scheduleStatus;
	}
	public void setScheduleStatus(boolean scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}

	public Date getSubmittedDateTime() {
		return submittedDateTime;
	}
	public void setSubmittedDateTime(Date submittedDateTime) {
		this.submittedDateTime = submittedDateTime;
	}
	public List<Date> getDateScheduled() {
		return dateScheduled;
	}
	public void setDateScheduled(List<Date> dateScheduled) {
		this.dateScheduled = dateScheduled;
	}
	
	public CountryCode getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public String toString() {
		return "AuditLog [id=" + id + ", uniqueId=" + uniqueId + ", annexFileName=" + annexFileName + ", annexStatus="
				+ annexStatus + ", scheduleFileName=" + scheduleFileName + ", scheduleStatus=" + scheduleStatus
				+ ", username=" + username + ", system=" + system + ", submittedDateTime=" + submittedDateTime
				+ ", dateScheduled=" + dateScheduled + ", countryCode=" + countryCode + "]";
	}
}
