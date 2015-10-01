package com.phonaylin.techconf.management.api.exceptions;

public class NoTimeslotConfiguredException extends CommonException{
	private static final long serialVersionUID = 1L;

	public NoTimeslotConfiguredException() {
        super(ExceptionConstants.NO_TIMESLOT_IS_CONFIGURED_MESSAGE);
    }
    
    @Override
    public String getCode() {
        return ExceptionConstants.NO_TIMESLOT_IS_CONFIGURED_CODE;
    }
  
}
