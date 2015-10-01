package com.phonaylin.techconf.management.entity;

import java.util.ArrayList;
import java.util.List;

public class Track {
	private List<Talk> talks = new ArrayList<Talk>();
	
	private int number;
	
	public Track(final int number) {		
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}
	
	
}
