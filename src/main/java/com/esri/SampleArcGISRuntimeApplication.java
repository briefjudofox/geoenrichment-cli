package com.esri;

import com.esri.service.GPTaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleArcGISRuntimeApplication implements CommandLineRunner {

    // Simple example shows how a command line spring application can
    // run geo processing tasks via the ArcGIS Java Runtime SDK

    @Autowired
    private GPTaskInfoService gpTaskInfoService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleArcGISRuntimeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        gpTaskInfoService.startService();
        System.out.println("Service Started.");
        gpTaskInfoService.printTasks();
        gpTaskInfoService.stopService();
        System.out.println("Service Stopped.");
    }

}
