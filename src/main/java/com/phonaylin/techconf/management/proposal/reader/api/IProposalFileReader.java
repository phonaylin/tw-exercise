package com.phonaylin.techconf.management.proposal.reader.api;

import java.util.List;

import com.phonaylin.techconf.management.entity.Talk;

/***
 * Intention is to have JSON reader, CSV reader etc.
 * @author I305731
 *
 */
public interface IProposalFileReader {
	
	public List<Talk> read();

}
