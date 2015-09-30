package com.nl.tw.exercise;

import com.nl.conference.mangement.reader.ProposalFileReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        if (args[0] != null) {
        	String filePath = args[0];
        	
        	ProposalFileReader reader = new ProposalFileReader();
        	reader.read(filePath);
        }
        
    }
    
    public static void test() {
    	String filePath = "/Users/phonaylin/Documents/Development/thoughtworks/conference-scheduler/conf-scheduler-app/src/test/resources/input.txt";;
    	
    	ProposalFileReader reader = new ProposalFileReader();
    	reader.read(filePath);
    }
}
