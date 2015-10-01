package com.phonaylin.techconf.management.impl;

import java.util.List;

import com.phonaylin.techconf.management.api.ITrackManager;
import com.phonaylin.techconf.management.entity.Talk;
import com.phonaylin.techconf.management.entity.Track;
import com.phonaylin.techconf.management.entity.TrackTimeSlot;

public abstract class AbstractTrackManager implements ITrackManager {
	
	private List<TrackTimeSlot> timeslots;
	
	public AbstractTrackManager(List<TrackTimeSlot> timeslots) {
		this.setTimeslots(timeslots);
	}
	
	@Override
	public abstract List<Track> schedule(List<Talk> talks);

	public List<TrackTimeSlot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(List<TrackTimeSlot> timeslots) {
		this.timeslots = timeslots;
	}
	
}
