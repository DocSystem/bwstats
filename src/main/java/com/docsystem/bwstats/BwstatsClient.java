package com.docsystem.bwstats;

import java.io.File;

public class BwstatsClient extends BwstatsCommon {
	@Override
    public void preInit(File configFile)
    {
        super.preInit(configFile);
        System.out.println("pre init côté client");
    }
 
	@Override
    public void init()
    {
        super.init();
    }
}
