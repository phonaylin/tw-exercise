package com.phonaylin.techconf.management.api.exceptions;

public class CannotFindSuitableProposalReaderException extends CommonException{
	private static final long serialVersionUID = 1L;

	public CannotFindSuitableProposalReaderException() {
        super(ExceptionConstants.SUITABLE_PROPOSAL_READER_NOT_FOUND_MESSAGE);
    }
    
	@Override
    public String getCode() {
        return ExceptionConstants.SUITABLE_PROPOSAL_READER_NOT_FOUND_CODE;
    }
}
