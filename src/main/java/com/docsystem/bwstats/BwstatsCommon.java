package com.docsystem.bwstats;

import java.io.File;

import net.minecraftforge.client.ClientCommandHandler;

public class BwstatsCommon
{
    public void preInit(File configFile)
    {
        System.out.println("pre init côté commun");
    }
 
	public void init()
    {
		ClientCommandHandler.instance.registerCommand(new BwstatsCommand());
    }
}