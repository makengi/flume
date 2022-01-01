package com.imr.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;



public class AppConfig {

	 
	 private static Logger LOG = Logger.getLogger(AppConfig.class);
	 private Properties appAroperties = new Properties();

	  //Set Config File Job Start & End--------------------------
    public void loadConfig(String configPath) {
        try {
        	
        	String appConfigPath = null;
       		checkConfigFileExist(configPath);   	//make config file
        	appConfigPath = (configPath == null || "".equals(configPath)) ? "app.properties" : configPath;
        	
            InputStream is = new FileInputStream(appConfigPath);
            appAroperties.load(new InputStreamReader(is, "UTF-8"));
      
            is.close();
        } catch (Exception e) {
            LOG.error("ConfigProperties Parsing failed.  Reason: " + e.getMessage());
        }
    }
    
    public void checkConfigFileExist(String configPath) {
    	File config_file = null;
    	
    	if(configPath != null && !"".equals(configPath)) {
    		config_file = new File(configPath);	
    	}
    	else {
    		config_file = new File("app.properties");	
    	}
    	FileWriter fw = null;
		
		if(!config_file.exists()) {
			
			try {
				fw = new FileWriter(config_file, true);
				fw.write("################### serverInfo setting ###################\r\n" +
						"# Set the HTTP address for web server. Default: any local address. Default: any local address.\r\n" +
						"webhost=0.0.0.0\r\n" +
						"\r\n" + 
						"# Set the HTTP port for web server. Default: 8080.\r\n" +
						"webPort=19000\r\n" +
						"\r\n" + 
						"# Set the web directory for web server. Default: webapp resource directory.\r\n" +
						"webDirectory=\r\n" +
						"\r\n" + 
						"################### scheduling setting ###################\r\n" + 
						"# Set the use Shceduling. Default: false\r\n" + 
						"schedulingUse=false\r\n" + 
						"\r\n" + 
						"# Set the Shceduling type. Default: it\r\n" +
						"#schedulingUseType=ft\r\n" +
						"schedulingUseType=it\r\n" + 
						"\r\n" + 
						"# Set the Shceduling Expression or interval. Default: 10(interval)\r\n" +
						"#schedulingUseTime=5 * * * * ? *\r\n" + 
						"schedulingUseTime=10\r\n" + 
						"\r\n" +
						"################### file copy setting ###################\r\n" +
						"# Set Copy Mode(ftp/shared). Default : ftp\r\n" + 
						"copyMode=shared\r\n" + 
						"\r\n" +
						"# Set Remote Base Directory Path\r\n" + 
						"basePath=\"D:\\copyTest\"\r\n" + 
						"\r\n" +
						"# Set Source Directory Path 1\r\n" + 
						"facility=D:\\\\tempr\n" + 
						"\r\n" +
						"# Set Source Directory Path 2\r\n" + 
						"barcode=D:\\temp\r\n" + 
						"\r\n" +
						"# Set Source Directory Path 3\r\n" + 
						"barcodeData=D:\\temp\r\n" + 
						"\r\n" +
						"################### debug mode setting ###################\r\n" +
						"# Set Debug Mode(space/debug). Default : space.\r\n" + 
						"debugMode=\r\n" + 
						"\r\n" +
						"");
				BufferedWriter out = new BufferedWriter(fw);
				out.newLine();
				
				fw.flush();
				out.close();
				fw.close(); 
				
				System.out.println("The file is created.");
			} catch (IOException e) {				
				e.printStackTrace();
				LOG.error("checkConfigFileExist error : " + e.getMessage());
			}			 
						
		}  
    }
    
    public Properties getPropertiesFile() {
    	return appAroperties;
    }


    public void printConfig() {        
    	LOG.info("######################## C-its Configuration #############################");
    	
        LOG.info("********************* remote Setting *********************");
        LOG.info("ip : " + appAroperties.getProperty("ip"));
        LOG.info("port : " + appAroperties.getProperty("port"));
        LOG.info("********************* scheduling setting *********************");
        LOG.info("schedulingUse : " + appAroperties.getProperty("schedulingUse"));
        LOG.info("schedulingUseType : " + appAroperties.getProperty("schedulingUseType"));
        LOG.info("schedulingUseTime : " + appAroperties.getProperty("schedulingUseTime"));   
        LOG.info("********************* debug mode setting *********************");
        LOG.info("debugMode : " + appAroperties.getProperty("debugMode"));   
        LOG.info("######################## Configuration Print End #############################\n");
    }   
}
