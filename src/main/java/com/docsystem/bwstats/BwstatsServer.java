package com.docsystem.bwstats;

import java.io.File;

public class BwstatsServer extends BwstatsCommon {
	@Override
    public void preInit(File configFile)
    {
        super.preInit(configFile);
        System.out.println("pre init côté serveur");
    }
 
	@Override
    public void init()
    {
        super.init();
        
    }
}
