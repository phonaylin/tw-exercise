package com.phonaylin.techconf.management.entity;

import java.util.Date;

public class Talk {
	
	private String name;
	private int minutes;
	private Date scheduledTime;

	public Talk(final String name, final int minutes) {
		this.name = name;
		this.minutes = minutes;
	}
	
	public Talk(final String name, final int minutes, Date scheduledTime) {
		this.name = name;
		this.minutes = minutes;
		this.scheduledTime =  scheduledTime;
	}
	
	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getName() {
		return name;
	}
	public Integer getMinutes() {
		return minutes;
	}
	
	/***
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%1$tH:%1$tM %1$tp %2s %3d",
				scheduledTime.toString(), name, minutes);
		
	}	
	
}
