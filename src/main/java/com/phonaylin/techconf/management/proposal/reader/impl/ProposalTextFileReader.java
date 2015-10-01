package com.phonaylin.techconf.management.proposal.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.phonaylin.techconf.management.api.exceptions.ProposalFileIOException;
import com.phonaylin.techconf.management.api.exceptions.ProposalFileNotFoundException;
import com.phonaylin.techconf.management.entity.Talk;
import com.phonaylin.techconf.management.proposal.reader.api.IProposalLineConverter;
import com.phonaylin.techconf.management.proposal.reader.api.IProposalFileReader;


public class ProposalTextFileReader implements IProposalFileReader {
	
	private File file;
	
	private IProposalLineConverter<String> converter;
	
	private final static Logger LOG = Logger.getLogger(ProposalTextFileReader.class.getName());
	
	public ProposalTextFileReader(File file, IProposalLineConverter<String> converter) {
		this.file = file;
		this.converter = converter;
	}
	
//	public ProposalTextFileReader(IProposalLineConverter<String> converter) {
//		this.converter = converter;
//	}
//	
//	public ProposalTextFileReader() {
//		this.converter = new ProposalStringConverter();
//	}
	
	@Override
	public List<Talk> read() {
		if (file == null) {
			throw new IllegalArgumentException("file is null");
		}
		
		LOG.log(Level.INFO, "Start reading file [{0}]", file.getName());
		
		BufferedReader br = null; 
		List<Talk> talks = new ArrayList<Talk>();

		try {

			String currentLine;

			br = new BufferedReader(new FileReader(file));

			while ((currentLine = br.readLine()) != null) {
				talks.add(converter.convertLineToTalk(currentLine));
			}
		} catch (FileNotFoundException e) {
			LOG.log(Level.SEVERE, "File [{0}] cannot be found ", file.getName());
			throw new ProposalFileNotFoundException();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "IOException occured while reading file", e);
			throw new ProposalFileIOException();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException ex) {
				LOG.log(Level.SEVERE, "IOException occured while closing BufferedReader", ex);
			}
		}
		
		
		return talks;
	}
}
