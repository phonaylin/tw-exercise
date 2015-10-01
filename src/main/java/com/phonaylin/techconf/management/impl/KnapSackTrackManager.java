package com.phonaylin.techconf.management.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import com.phonaylin.techconf.management.api.exceptions.NoTimeslotConfiguredException;
import com.phonaylin.techconf.management.entity.Talk;
import com.phonaylin.techconf.management.entity.TimeSlotType;
import com.phonaylin.techconf.management.entity.Track;
import com.phonaylin.techconf.management.entity.TrackTimeSlot;
import com.phonaylin.techconf.management.knapsack.KnapSackSolver;
import com.phonaylin.techconf.management.knapsack.KnapSackSolverRequest;
import com.phonaylin.techconf.management.knapsack.KnapSackSolverResponse;


public class KnapSackTrackManager extends AbstractTrackManager {
	
	public KnapSackTrackManager(List<TrackTimeSlot> timeslots) {
		super(timeslots);
	}

	private final static Logger LOG = Logger
			.getLogger(KnapSackTrackManager.class.getName());
	
	
	@Override
	public List<Track> schedule(List<Talk> talks) {
		
		if (talks  == null || talks.size() ==0)
			throw new IllegalArgumentException("Talks must not be null");
		
		if (getTimeslots()  == null || getTimeslots().size() ==0)
			throw new NoTimeslotConfiguredException();
		
		
		// TODO: validations for talks
		List<Track> tracks = new ArrayList<Track>();
		
		// dangerous
		int trackNum = 1;
		while (talks.size() > 0 ) {
			Track track = new Track(trackNum);
			Calendar cal = Calendar.getInstance();
			for(TrackTimeSlot slot : getTimeslots()) {
				track.getTalks().addAll(createTalks(talks, slot, cal));
			}
			
			tracks.add(track);
			trackNum++;
		}
		
		return tracks;
	}
	
	private List<Talk> createTalks(List<Talk> talks, TrackTimeSlot slot, Calendar cal) {
		List<Talk> scheduledTalks = new ArrayList<Talk>();
		
		if (slot.getType().equals(TimeSlotType.TALK)) {
			// set start time
			cal.setTime(slot.getStartTime());
			
			// run algorithm
			KnapSackSolverRequest req = setKnapSackSolverRequest(talks, slot);
			KnapSackSolverResponse res = new KnapSackSolverResponse();
			useSolver(req, res);
			
			int i;
			boolean[] take;
			take = res.getTake();
			i = 1;
			for (ListIterator<Talk> iter = talks.listIterator(); iter
					.hasNext();) {
				Talk talk = iter.next();
				if (take[i]) {
					talk.setScheduledTime(cal.getTime());
					cal.add(Calendar.MINUTE, talk.getMinutes()); // set next talk start time
					scheduledTalks.add(talk);
					iter.remove();
				}
				i++;
			}
		}
		else {
			scheduledTalks.add(new Talk(slot.getType().getValue(), 0, cal.getTime()));
		}
		
		
		return scheduledTalks;
	}

	private KnapSackSolverRequest setKnapSackSolverRequest(
			List<Talk> validTalksList, TrackTimeSlot timeslot) {
		
//		long diff = timeslot.getEndTime().getTime() - timeslot.getStartTime().getTime();
//		long diffMinutes = diff / (60 * 1000);
		
		int W = timeslot.getTimeDifferenceInMinutes(); // TODO: 
		int N = validTalksList.size();

		int[] profit = new int[N + 1];
		int[] weight = new int[N + 1];
		int i = 1;
		// profit and weight are same in this case
		for (Talk proft : validTalksList) {
			profit[i] = weight[i] = proft.getMinutes();
			i++;
		}
		KnapSackSolverRequest req = new KnapSackSolverRequest();
		req.setMaxKnapSackSize(W);
		req.setNumSize(N);
		req.setProfit(profit);
		req.setWeight(weight);
		return req;
	}
	
	private void useSolver(KnapSackSolverRequest req, KnapSackSolverResponse res) {
		KnapSackSolver knapSolver = new KnapSackSolver();
		try {
			knapSolver.solver(req, res);
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
