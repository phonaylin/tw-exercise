package com.nl.tw.exercise.conf.mgmt.entity;

public class TalkSession {
	private String name;
	private int minutes;
	
	public TalkSession(final String name, final int minutes) {
		this.name = name;
		this.minutes = minutes;
	}
	
	public String getName() {
		return name;
	}
	public int getMinutes() {
		return minutes;
	}
	
}
