package com.esri.service;

import com.esri.SampleArcGISRuntimeApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.OutputCapture;
import static org.junit.Assert.assertTrue;


public class SampleArcGISRuntimeApplicationTests {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	private String profiles;

	@Before
	public void init() {
		this.profiles = System.getProperty("spring.profiles.active");
	}

	@After
	public void after() {
		if (this.profiles != null) {
			System.setProperty("spring.profiles.active", this.profiles);
		}
		else {
			System.clearProperty("spring.profiles.active");
		}
	}

	@Test
	public void testDefaultSettings() throws Exception {
		SampleArcGISRuntimeApplication.main(new String[0]);
		String output = this.outputCapture.toString();
        assertTrue("Wrong output: " + output, output.contains("Starting Service..."));
	}

	@Test
	public void testCommandLineOverrides() throws Exception {
		SampleArcGISRuntimeApplication.main(new String[] { "--gpkPath=data/SimpleBuffer.gpk" });
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("Starting Service..."));
	}

    @Test
   	public void testBadGPKOverride() throws Exception {
   		SampleArcGISRuntimeApplication.main(new String[] { "--gpkPath=McFoo" });
   		String output = this.outputCapture.toString();
   		assertTrue("Wrong output: " + output, output.contains("Service was unable to start. Service status: STARTFAILED"));
   	}



}
