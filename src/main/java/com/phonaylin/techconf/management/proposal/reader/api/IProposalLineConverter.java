package com.phonaylin.techconf.management.proposal.reader.api;

import com.phonaylin.techconf.management.entity.Talk;

/***
 * Intention is to have line converter with different business logic
 * @author I305731
 * @param <T>
 *
 */
public interface IProposalLineConverter<T> {
	public static final String LIGHTNING_TEXT = "lightning";
	public static final int LIGHTNING_DURATION = 5;
	
	public Talk convertLineToTalk(T t);
	
	public T convertTalkToLine(Talk talk);
	
}
