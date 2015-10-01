package com.phonaylin.techconf.management.api.exceptions;

public class ProposalFileIOException extends CommonException{
	private static final long serialVersionUID = 1L;

	public ProposalFileIOException() {
        super(ExceptionConstants.FILE_IO_ERROR_MESSAGE);
    }
    
	@Override
    public String getCode() {
        return ExceptionConstants.FILE_IO_ERROR_CODE;
    }
}
