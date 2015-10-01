package com.phonaylin.techconf.management.api.exceptions;

public class ProposalFileNotFoundException extends CommonException{
	private static final long serialVersionUID = 1L;

	public ProposalFileNotFoundException() {
        super(ExceptionConstants.FILE_NOT_FOUND_MESSAGE);
    }
    
	@Override
    public String getCode() {
        return ExceptionConstants.FILE_NOT_FOUND_CODE;
    }
}
