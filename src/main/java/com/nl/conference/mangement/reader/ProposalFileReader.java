package com.nl.conference.mangement.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nl.conference.mangement.reader.exceptions.ConverterException;
import com.nl.tw.exercise.conf.mgmt.entity.TalkSession;


public class ProposalFileReader {
	private ProposalConverter converter = new ProposalConverter();
	
	public List<TalkSession> read(String filePath) {

		List<TalkSession> sessions = new ArrayList<TalkSession>();
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				
				sessions.add(converter.convert(sCurrentLine));
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		return sessions;
		
	}
	
	
	private class ProposalConverter {
		public TalkSession convert(String talk) throws ConverterException {
			TalkSession session = null;
			if (talk == null || talk.isEmpty()) {
				throw new ConverterException("Error while converting talk [" + talk + "]");
			}
				
				
			talk = talk.replaceAll("\\s+", " ").trim();
			Pattern pattern = Pattern
					.compile("(.*)(\\s){1}([0-2]?[0-9]?[0-9]{1}min|lightning)\\b");
			Matcher matcher = pattern.matcher(talk);
			if (!matcher.matches()) {
//				log.warn("Talk:[" + talk
//						+ "] was ignored. Check whether it is valid");
				throw new ConverterException("Error while converting talk [" + talk + "]");
			
			}
			String title = matcher.group(1);
			String duration = matcher.group(3);
			
			if (duration.equals("lightning")) {
				session = new TalkSession(title, 5);
			} else {
				session = new TalkSession(title, Integer.parseInt(duration.substring(0, duration.indexOf("min"))));
			}
			
			System.out.println(" ? : " + matcher.group(1));
			System.out.println("Title : " + matcher.group(2));
			System.out.println("Duration : " + matcher.group(3));
//			
//			talktime = calculateTalkTime(matcher.group(3));
//			if (talktime <= maxTalkTime && talktime >= minTalkTime) {
//				// Add talk to the valid talk List.
//				validTalksList.add(new Talk(matcher.group(1), talktime));
//				// System.out.println("Considering : " + name);
//			} else {
//				log.warn("Talk:[" + talk
//						+ "] was ignored. The talk time was out of bounds");
//			}
			return session;
		}
	}
	
	
}
