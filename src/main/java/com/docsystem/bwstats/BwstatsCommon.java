package com.docsystem.bwstats;

import java.io.File;

import net.minecraftforge.client.ClientCommandHandler;

public class BwstatsCommon
{
    public void preInit(File configFile)
    {
        System.out.println("pre init c�t� commun");
    }
 
	public void init()
    {
		ClientCommandHandler.instance.registerCommand(new BwstatsCommand());
    }
}