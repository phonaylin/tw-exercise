package com.phonaylin.techconf.management.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.phonaylin.techconf.management.entity.Talk;
import com.phonaylin.techconf.management.entity.TimeSlotType;
import com.phonaylin.techconf.management.entity.Track;
import com.phonaylin.techconf.management.entity.TrackTimeSlot;
import com.phonaylin.techconf.management.impl.KnapSackTrackManager;

import junit.framework.TestCase;

/**
 * Unit test for KnapSackManager.
 */
public class KnapSackManagerTest  extends TestCase
{
    private List<Talk> talks;
    
    private KnapSackTrackManager mgr;
    
    protected void setUp() throws Exception {
    	talks = new ArrayList<Talk>();
    	for (int i=1; i<21; i++) {
	    	talks.add(new Talk("Talk " + i, ThreadLocalRandom.current().nextInt(5, 90)));
    	}
    	
    	List<TrackTimeSlot> timeslots = configureTimeSlots(new Date());
    	mgr = new KnapSackTrackManager(timeslots);
    }
    
    private static List<TrackTimeSlot> configureTimeSlots(Date conferenceDate) {
    	List<TrackTimeSlot> timeSlots = new ArrayList<TrackTimeSlot>();
    	
    	Calendar cal = Calendar.getInstance();
		cal.setTime(conferenceDate);
		
		// set morning session
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startTime = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY, 12);
		Date endTime = cal.getTime();
		
		timeSlots.add(new TrackTimeSlot(TimeSlotType.TALK, startTime, endTime)); // morning session
		
		// set lunch session
		cal.set(Calendar.HOUR_OF_DAY, 12);
		startTime = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY, 13);
		endTime = cal.getTime();
		
		timeSlots.add(new TrackTimeSlot(TimeSlotType.LUNCH, startTime, endTime)); // lunch session
		
		// set afternoon session
		cal.set(Calendar.HOUR_OF_DAY, 13);
		startTime = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY, 17);
		endTime = cal.getTime();
		
		timeSlots.add(new TrackTimeSlot(TimeSlotType.TALK, startTime, endTime)); // afternoon session
		
		// set networking session
		cal.set(Calendar.HOUR_OF_DAY, 16);
		startTime = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY, 18);
		endTime = cal.getTime();
		
		timeSlots.add(new TrackTimeSlot(TimeSlotType.NETWORKING, startTime, endTime)); // networking session
		
		return timeSlots;
    }
    
    
    /**
     * 
     */
    public void testCreateTalks()
    {
    	List<Track> tracks = mgr.schedule(talks);
    	assertFalse( tracks.isEmpty() );
    	
    	assertFalse( tracks.isEmpty() );
    }
    
}
