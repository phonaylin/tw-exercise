package com.phonaylin.techconf.management.api.exceptions;

public interface ExceptionConstants {
    public static final String UNEXPECTED_ERROR_CODE = "unexpected-error";
    public static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error has occurred, please try again.";

	public static final String UNABLE_TO_PARSE_PROPOSAL_CODE = "unable-to-parse-proposal";
    public static final String UNABLE_TO_PARSE_PROPOSAL_MESSAGE = "Unable to parse the proposal line to talk";
    
    public static final String FILE_NOT_FOUND_CODE = "proposal-file-cannot-be-found";
    public static final String FILE_NOT_FOUND_MESSAGE = "Cannot find the proposal file";

    public static final String FILE_IO_ERROR_CODE = "io-error-in-reading-proposal-file";
    public static final String FILE_IO_ERROR_MESSAGE = "Error while reading the proposal file";
    
    public static final String SUITABLE_PROPOSAL_READER_NOT_FOUND_CODE = "suitable-proposal-file-reader-not-found";
    public static final String SUITABLE_PROPOSAL_READER_NOT_FOUND_MESSAGE = "Cannot find a suitable reader for given proposal file";
    
    public static final String NO_TIMESLOT_IS_CONFIGURED_CODE = "no-timeslot-is-configured";
    public static final String NO_TIMESLOT_IS_CONFIGURED_MESSAGE = "Cannot find any timeslot configured for conference track";
}
