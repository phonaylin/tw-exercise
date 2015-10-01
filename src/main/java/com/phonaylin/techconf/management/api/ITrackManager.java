package com.phonaylin.techconf.management.api;

import java.util.List;

import com.phonaylin.techconf.management.entity.Talk;
import com.phonaylin.techconf.management.entity.Track;

/***
 * 
 *
 */
public interface ITrackManager {
	
	public List<Track> schedule(List<Talk> talks);

}
