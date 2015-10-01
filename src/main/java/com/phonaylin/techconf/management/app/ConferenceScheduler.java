package com.phonaylin.techconf.management.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.phonaylin.techconf.management.api.ITrackManager;
import com.phonaylin.techconf.management.api.exceptions.ProposalFileNotFoundException;
import com.phonaylin.techconf.management.entity.Talk;
import com.phonaylin.techconf.management.entity.TimeSlotType;
import com.phonaylin.techconf.management.entity.Track;
import com.phonaylin.techconf.management.entity.TrackTimeSlot;
import com.phonaylin.techconf.management.impl.KnapSackTrackManager;
import com.phonaylin.techconf.management.proposal.reader.api.IProposalFileReader;
import com.phonaylin.techconf.management.proposal.reader.api.IProposalLineConverter;
import com.phonaylin.techconf.management.proposal.reader.impl.ProposalStringConverter;
import com.phonaylin.techconf.management.proposal.reader.impl.ProposalTextFileReader;


/**
 * ConferenceScheduler
 *
 */
public class ConferenceScheduler 
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome to Conference Scheduler!" );
        
        if (args.length > 0) {
        	ConferenceScheduler app = new ConferenceScheduler();
        	List<Track> tracks = app.scheduleTracks(args[0]);
        	app.writeToConsole(tracks);
        }
        else {
        	System.out.println( "Please provide file path in the argument. e.g., java -jar conferenceschdeudler.jar <path to file>" );
        }
    }
    
    public List<Track> scheduleTracks(String proposalFilePath) {
    	if (proposalFilePath == null || proposalFilePath.isEmpty()) {
        	throw new ProposalFileNotFoundException();
        }
        
    	File file = new File(proposalFilePath);
    	IProposalFileReader reader = new ProposalTextFileReader(file, new ProposalStringConverter());
    	List<Talk> talks = reader.read();
    	 
    	List<TrackTimeSlot> timeSlots = configureTimeSlots(new Date());
        ITrackManager manager = new KnapSackTrackManager(timeSlots);
    	List<Track> tracks = manager.schedule(talks);
    	
    	return tracks;
    }
    
    private List<TrackTimeSlot> configureTimeSlots(Date conferenceDate) {
    	List<TrackTimeSlot> timeSlots = new ArrayList<TrackTimeSlot>();
    	
    	Calendar cal = Calendar.getInstance();
		cal.setTime(conferenceDate);
		
		// set morning session
		cal.set(Calendar.HOUR_OF_DAY, 9);
		cal.set(Calendar.MINUTE, 0);
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
 
    // Commented because Files.probeContentType does not work in Mac OSX
//	private IProposalFileReader getFileReader(final String filePath) {
//		try {
//			File file = new File(filePath);
//		    String type = Files.probeContentType(file.toPath());
//		    if (type != null && type.equals("text/plain")) {
//		    	return new ProposalTextFileReader(file, new ProposalStringConverter());
//		    } else {
//		        throw new CannotFindSuitableProposalReaderException(); 
//		    }
//		} catch (IOException e) {
//			LOG.log(Level.SEVERE, "IOException while probing file MIME type", e);
//		    throw new ProposalFileIOException();
//		}
//	}
	
    // write Tracks to console
	private void writeToConsole(List<Track> tracks) {
		IProposalLineConverter<String> converter = new ProposalStringConverter();
		for (Track track : tracks) {
			System.out.println("==================================");
			System.out.println("Track " + track.getNumber());
			for (Talk talk : track.getTalks()) {
				System.out.println(converter.convertTalkToLine(talk));
			}
		}
	}

}
