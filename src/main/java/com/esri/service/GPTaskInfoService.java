package com.esri.service;

import com.esri.client.local.GPServiceType;
import com.esri.client.local.LocalGeoprocessingService;
import com.esri.client.local.LocalServiceStartCompleteListener;
import com.esri.client.local.LocalServiceStopCompleteListener;
import com.esri.core.tasks.ags.geoprocessing.GPServiceInfo;
import com.esri.core.tasks.ags.geoprocessing.GPTaskInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GPTaskInfoService {

    @Value("${gpkPath:../data/SimpleBuffer.gpk}")
    private String gpkPath;

    private LocalGeoprocessingService localGpService;

    public void startService(LocalServiceStartCompleteListener startCompleteListener) {
        System.out.println("Starting Service...");
        localGpService = new LocalGeoprocessingService(gpkPath);
        localGpService.setServiceType(GPServiceType.EXECUTE.EXECUTE);
        localGpService.addLocalServiceStartCompleteListener(startCompleteListener);
        localGpService.startAsync();
    }

    public void stopService(LocalServiceStopCompleteListener stopCompleteListener){
        localGpService.addLocalServiceStopCompleteListener(stopCompleteListener);
        localGpService.stopAsync();
    }

    public String[] getGPTasks() {
        System.out.println("Getting GP Tasks...");
        // get GP service info
        GPServiceInfo serviceInfo = null;
        try {
            serviceInfo = GPServiceInfo.fromUrl(localGpService.getUrlGeoprocessingService());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceInfo.getTasks();
    }

    public GPTaskInfo getGPTaskInfo(String task) {
        // get the task URL
        String taskUrl = localGpService.getUrlGeoprocessingService() + "/" + task;
        taskUrl = taskUrl.replaceAll("\\s", "%20");

        // get the GPTaskInfo from the task URL
        GPTaskInfo taskInfo = null;
        try {
            taskInfo = GPTaskInfo.fromUrl(taskUrl);
        } catch (Exception e) {
            System.err.println("Exception at GPTaskInfo creation");
            e.printStackTrace();
        }
        return taskInfo;
    }

    public void printTasks() {
        String[] tasks = getGPTasks();
        System.out.println("Tasks:");
        for(String task : tasks){
            printTaskInfo(task);
        }
    }

    public void printTaskInfo(String task) {
        GPTaskInfo taskInfo = getGPTaskInfo(task);

        System.out.println("    Name: " + taskInfo.getName());
        System.out.println("    Category: " + taskInfo.getCategory());
        System.out.println("    DisplayName: " + taskInfo.getDisplayName());
        System.out.println("    ExecutionType: " + taskInfo.getExecutionType());
        System.out.println("    HelpUrl: " + taskInfo.getHelpUrl());
        System.out.println("    Parameters: ");

        for(GPTaskInfo.GPParameterInfo param  : taskInfo.getParameters()){
            System.out.println("        ########################################");
            System.out.println("        Name: " + param.getName());
            System.out.println("        DisplayName: " + param.getDisplayName());
            System.out.println("        Category: " + param.getCategory());
            System.out.println("        ChoiceList: " + param.getChoiceList());
            System.out.println("        DataType: " + param.getDataType());
            System.out.println("        DefaultValue: " + param.getDefaultValue());
            System.out.println("        Direction: " + param.getDirection());
            System.out.println("        Direction: " + param.getParameterType());
            System.out.println();
        }
    }
}
