runtime-gptask-cli-starter
===================

A basic Java CLI built with [spring-boot](https://github.com/spring-projects/spring-boot/) and the [ArcGIS Runtime SDK for Java](http://resources.arcgis.com/en/communities/runtime-java/).  This example shows how you can get info on available geo-processing tasks contained in a geo-processing package.  This project is intended to be cloned and used as a starting point for more useful tools.

This project assumes that you've already installed the ArcGIS Runtime SDK on your machine.  

To add the ArcGIS Runtime SDK jar file to your local Maven repo cd to your runtime install: `../ArcGIS SDKs/java10.1.1/sdk/jars` and run the following:
```
mvn install:install-file -Dfile=ArcGIS_Runtime_Java.jar -DgroupId=com.esri -DartifactId=arcgis-runtime -Dversion=10.1.1 -Dpackaging=jar
```
You should then be able to build this project from the root directory: `mvn clean install`

Once the build completes you should have an executable jar in your target directory.  You can run with default values like this: 
`java -jar GPTaskRunner.jar`

Or you can specify your own gp package like this:
`java -jar GPTaskRunner.jar --gpkPath=<PATH TO GPK>`

Running with defaults should give you something like this:
```
Service Started.
Getting GP Tasks...
Tasks:
    Name: SimpleBuffer
    Category:
    DisplayName: SimpleBuffer
    ExecutionType: esriExecutionTypeSynchronous
    HelpUrl: http://127.0.0.1:50000/outputdir/SimpleBuffer.gpk/output/SimpleBuffer.gpk/SimpleBuffer.htm

    Parameters:
        ########################################
        Name: InputFeatures
        DisplayName: InputFeatures
        Category:
        ChoiceList: null
        DataType: class com.esri.core.tasks.ags.geoprocessing.GPFeatureRecordSetLayer
        DefaultValue: null
        Direction: esriGPParameterDirectionInput
        Direction: esriGPParameterTypeRequired

        ########################################
        Name: Distance
        DisplayName: Distance
        Category:
        ChoiceList: null
        DataType: class com.esri.core.tasks.ags.geoprocessing.GPLinearUnit
        DefaultValue: null
        Direction: esriGPParameterDirectionInput
        Direction: esriGPParameterTypeRequired

        ########################################
        Name: BufferOutput
        DisplayName: BufferOutput
        Category:
        ChoiceList: null
        DataType: class com.esri.core.tasks.ags.geoprocessing.GPFeatureRecordSetLayer
        DefaultValue: null
        Direction: esriGPParameterDirectionOutput
        Direction: esriGPParameterTypeRequired

Stopping Service...
Service Stopped.
```

  