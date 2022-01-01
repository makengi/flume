package com.imr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;

import com.imr.config.AppConfig;

public class Starter {
	
	private static Logger LOG = Logger.getLogger(Starter.class);
	private static CommandLine cl;
	private static String configPath = null;
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        
		Options options = new Options();
		
		options.addOption("scf", "setconfigfile", true, "Set Config File");// Settting Config file
        
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        
        // Parse arguments
       
        try {
			cl = new DefaultParser().parse(options, args);
			if (cl.hasOption("scf")) {
	  			configPath = cl.getOptionValue("scf");
	  		}
		} catch (org.apache.commons.cli.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
        //Set Config File Job Start--------------------------
  		
  		LOG.info("AppConfig configPath : " + configPath) ;
    	
    	AppConfig config = new AppConfig();
   		config.checkConfigFileExist(configPath);   	//make config file

  		config.loadConfig(configPath);
		config.printConfig();

		String remoteIp = config.getPropertiesFile().getProperty("ip");	
		int remotePort = Integer.parseInt(config.getPropertiesFile().getProperty("port"));
		int intersection = Integer.parseInt(config.getPropertiesFile().getProperty("intersection"));
		int groupId = Integer.parseInt(config.getPropertiesFile().getProperty("groupId"));
		
		LOG.info("remoteIp: "+remoteIp);
		LOG.info("remotePort: "+remotePort);
		LOG.info("intersection: "+intersection);
		LOG.info("groupId: "+groupId);
		
	}
}
