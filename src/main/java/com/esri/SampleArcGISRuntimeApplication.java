package com.esri;

import com.esri.client.local.*;
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
public class SampleArcGISRuntimeApplication implements CommandLineRunner, LocalServiceStartCompleteListener, LocalServiceStopCompleteListener {

    // Simple example shows how a command line spring application can
    // run geo processing tasks via the ArcGIS Java Runtime SDK

    @Autowired
    private GPTaskInfoService gpTaskInfoService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleArcGISRuntimeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        gpTaskInfoService.startService(this);
    }

    @Override
    public void localServiceStartComplete(LocalServiceStartCompleteEvent event) {
        LocalGeoprocessingService service = (LocalGeoprocessingService) event.getService();
        if (service.getStatus() == LocalServiceStatus.STARTED.STARTED) {
            gpTaskInfoService.printTasks();
            gpTaskInfoService.stopService(this);
        } else {
            System.out.println("Service was unable to start. Service status: " + service.getStatus());
        }
    }

    @Override
    public void localServiceStopComplete(LocalServiceStopCompleteEvent event) {
        LocalGeoprocessingService service = (LocalGeoprocessingService) event.getService();
        if (service.getStatus() == LocalServiceStatus.STOPPED.STOPPED) {
            System.out.println("Service stopped successfully." + service.getDescription());
        } else {
            System.out.println("Service was unable to stop properly. Service status: " + service.getStatus());
        }
    }
}
