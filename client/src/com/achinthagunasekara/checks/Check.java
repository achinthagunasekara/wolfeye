package com.achinthagunasekara.checks;

import java.util.Date;
import java.util.Calendar;

import com.achinthagunasekara.checks.CheckStatus.Status;
import com.google.gson.Gson;

/* 
 * @author Achintha Gunasekara
 * @email contact@achinthagunasekara.com.au
 */

public class Check {
	
	private final String checkName;
	private Date lastCheck;
	private Status status;
	private String statusDetails;
	
	public Check(String checkName) {
		this.checkName = checkName;
		Calendar cal = Calendar.getInstance();
		this.lastCheck = (Date) cal.getTime();
		this.status = CheckStatus.Status.UNKNOWN;
	}
	
	public void setLastCheck(Date lastCheck) {
		this.lastCheck = lastCheck;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getName() { return this.checkName; }
	public Date getLastCheck() { return this.lastCheck; }
	public Status getStatus() { return this.status; }
	
	public String toString() {
		
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}