package com.phonaylin.techconf.management.proposal.reader.impl;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.phonaylin.techconf.management.api.exceptions.ProposalConverterException;
import com.phonaylin.techconf.management.entity.Talk;
import com.phonaylin.techconf.management.proposal.reader.api.IProposalLineConverter;

public class ProposalStringConverter implements IProposalLineConverter<String> {

	private final static Logger LOG = Logger
			.getLogger(ProposalStringConverter.class.getName());

	private final static String MINUTE_SUFFIX = "min";

	@Override
	public Talk convertLineToTalk(String line) {

		if (line == null || line.isEmpty()) {
			throw new IllegalArgumentException("line must not be null or empty");
		}

		line = line.replaceAll("\\s+", " ").trim();
		Pattern pattern = Pattern
				.compile("(.*)(\\s){1}([0-2]?[0-9]?[0-9]{1}min|lightning)\\b");
		Matcher matcher = pattern.matcher(line);
		if (!matcher.matches()) {
			LOG.log(Level.SEVERE,
					"Line [{0}] is not recognised by the converter", line);
			throw new ProposalConverterException();
		}

		final String title = matcher.group(1);
		final String duration = matcher.group(3);
		int durationMinutes = 0;

		if (duration.equals(IProposalLineConverter.LIGHTNING_TEXT)) {
			durationMinutes = IProposalLineConverter.LIGHTNING_DURATION;
		} else {
			try {
				durationMinutes = Integer.parseInt(duration.substring(0,
						duration.indexOf(MINUTE_SUFFIX)));
			} catch (NumberFormatException e) {
				LOG.log(Level.SEVERE,
						"NumberFormatException while parsing minutes into Integer",
						e);
				throw new ProposalConverterException();
			}
		}

		return new Talk(title, durationMinutes);
	}

	@Override
	public String convertTalkToLine(final Talk talk)
			throws ProposalConverterException {

		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mma");
		final String scheduledTime = sdf.format(talk.getScheduledTime());
		final String title = " " + talk.getName() + " ";
		String minutes = "";
		if (talk.getMinutes() > 0) {
			minutes = talk.getMinutes().toString() + MINUTE_SUFFIX;
		} else if ( talk.getMinutes() == IProposalLineConverter.LIGHTNING_DURATION) {
			minutes = IProposalLineConverter.LIGHTNING_TEXT;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(scheduledTime);
		sb.append(title);
		sb.append(minutes);
		
		return sb.toString();
	}

}
