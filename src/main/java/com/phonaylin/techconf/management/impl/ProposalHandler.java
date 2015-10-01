package com.phonaylin.techconf.management.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.phonaylin.techconf.management.api.exceptions.CannotFindSuitableProposalReaderException;
import com.phonaylin.techconf.management.api.exceptions.ProposalFileIOException;
import com.phonaylin.techconf.management.proposal.reader.api.IProposalFileReader;
import com.phonaylin.techconf.management.proposal.reader.impl.ProposalStringConverter;
import com.phonaylin.techconf.management.proposal.reader.impl.ProposalTextFileReader;


public class ProposalHandler {
	private final static Logger LOG = Logger
			.getLogger(ProposalHandler.class.getName());
	
	public static IProposalFileReader getFileReader(final String filePath) {
		try {
			File file = new File(filePath);
		    String type = Files.probeContentType(file.toPath());
		    if (type.equals("text/plain")) {
		    	return new ProposalTextFileReader(file, new ProposalStringConverter());
		    } else {
		        throw new CannotFindSuitableProposalReaderException(); 
		    }
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "IOException while probing file MIME type", e);
		    throw new ProposalFileIOException();
		}
	}

	public static void writerToConsole(IProposalFileReader reader) {
		
	}
}
