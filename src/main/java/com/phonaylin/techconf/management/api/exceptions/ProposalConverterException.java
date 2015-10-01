package com.phonaylin.techconf.management.api.exceptions;

public class ProposalConverterException extends CommonException{
	private static final long serialVersionUID = 1L;

	public ProposalConverterException() {
        super(ExceptionConstants.UNABLE_TO_PARSE_PROPOSAL_MESSAGE);
    }
    
    @Override
    public String getCode() {
        return ExceptionConstants.UNABLE_TO_PARSE_PROPOSAL_CODE;
    }
  
}
