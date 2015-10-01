package com.phonaylin.techconf.management.entity;

import java.util.Date;

public class TrackTimeSlot {
	private TimeSlotType type;
	private Date startTime;
	private Date endTime;
	
	public TrackTimeSlot(final TimeSlotType type, final Date startTime, final Date endTime) {
		this.setType(type);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}

	public int getTimeDifferenceInMinutes() {
		long diff = getEndTime().getTime() - getStartTime().getTime();
		long diffMinutes = diff / (60 * 1000);
		return (int)diffMinutes;
	}
	
	public TimeSlotType getType() {
		return type;
	}

	public void setType(TimeSlotType type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
