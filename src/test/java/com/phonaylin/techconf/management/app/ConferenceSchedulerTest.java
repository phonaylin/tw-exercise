package com.phonaylin.techconf.management.app;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ConferenceSchedulerTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ConferenceSchedulerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ConferenceSchedulerTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	ConferenceScheduler.main(new String[]{getInputFile("input.txt").toString()});

    	assertTrue( true );
    }
    
    //@Test(expected = ArithmeticException.class)  
    public void testAppWithWeirdData()
    {
    	ConferenceScheduler.main(new String[]{getInputFile("input-with-weird-data.txt").toString()});

    	assertTrue( true );
    }
    
    public void testAppWithWrongData()
    {
    	ConferenceScheduler.main(new String[]{getInputFile("wrong-input.txt").toString()});

    	assertTrue( true );
    }
    
    private static Path getInputFile(String fileName) {
    	URL resource = ConferenceScheduler.class.getClassLoader().getResource(fileName);
    	if (resource != null) {
    		try {
				return Paths.get(resource.toURI()).toAbsolutePath();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	return null;
    }
    
    
}
