package com.docsystem.bwstats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class BwstatsCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "bwstats";
	}
	
	@Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Usage: /bwstats <player>";
	}
	
	@Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return getListOfStringsMatchingLastWord(args, this.getListOfPlayerUsernames());
    }
	
	private String[] getListOfPlayerUsernames() {
		String[] playerList = new String[]{"DocSystem"};
		return playerList;
    }

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 1) {
			String url = "https://api.hypixel.net/player?key=0b41349c-a396-4aeb-b90e-5a1d9fa7f685&name=" + args[0];
			URL obj = null;
			try {
				obj = new URL(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) obj.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader in = null;
			try {
				in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        try {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        JSONObject jsonResponse = new JSONObject(response.toString());
	        JSONObject jsonResponsePlayer = new JSONObject();
	        int validPlayer;
	        try {
	        	jsonResponsePlayer = jsonResponse.getJSONObject("player");
	        	validPlayer = 1;
	        }
	        catch (Exception e) {
	        	validPlayer = 0;
	        }
	        JSONObject jsonResponseStats = new JSONObject();
	        try {
	        	jsonResponseStats = jsonResponsePlayer.getJSONObject("stats");
	        	validPlayer = 1;
	        }
	        catch (Exception e) {
	        	validPlayer = 0;
	        }
	        JSONObject jsonResponseAchievements = new JSONObject();
	        try {
	        	jsonResponseAchievements = jsonResponsePlayer.getJSONObject("achievements");
	        	validPlayer = 1;
	        }
	        catch (Exception e) {
	        	validPlayer = 0;
	        }
	        JSONObject jsonResponseBedwars = new JSONObject();
	        try {
	        	jsonResponseBedwars = jsonResponseStats.getJSONObject("Bedwars");
	        	validPlayer = 1;
	        }
	        catch (Exception e) {
	        	validPlayer = 0;
	        }
	        if (validPlayer == 1) {
	        	Integer networkExp = 0;
	        	try {
	        		networkExp = jsonResponsePlayer.getInt("networkExp");
	        	}
	        	catch (Exception e) {
	        		networkExp = 0;
	        	}
	        	Integer networkLevel = (int) ((Math.sqrt(networkExp + 15312.5) - 125 / Math.sqrt(2)) / (25 * Math.sqrt(2)));
	        
	        	sender.addChatMessage(new ChatComponentText(" "));
	        	try {
	        		if (jsonResponsePlayer.getString("rank").equals("YOUTUBER")) {
	        			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[" + EnumChatFormatting.WHITE + "YOUTUBER" + EnumChatFormatting.RED + "] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
	        		}
	        		else if (jsonResponsePlayer.getString("rank").equals("ADMIN")) {
	        			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[ADMIN] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
	        		}
	        	}
	        	catch (Exception mainE) {
		        	try {
		        		if (jsonResponsePlayer.getString("newPackageRank").equals("MVP_PLUS")) {
		        			EnumChatFormatting plusColor = EnumChatFormatting.RED;
		        			if (jsonResponsePlayer.getString("rankPlusColor").equals("RED")) {
		        				plusColor = EnumChatFormatting.RED;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("GOLD")) {
		        				plusColor = EnumChatFormatting.GOLD;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("GREEN")) {
		        				plusColor = EnumChatFormatting.GREEN;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("YELLOW")) {
		        				plusColor = EnumChatFormatting.YELLOW;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("LIGHT_PURPLE")) {
		        				plusColor = EnumChatFormatting.LIGHT_PURPLE;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("WHITE")) {
		        				plusColor = EnumChatFormatting.WHITE;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("BLUE")) {
		        				plusColor = EnumChatFormatting.BLUE;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("DARK_GREEN")) {
		        				plusColor = EnumChatFormatting.DARK_GREEN;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("DARK_RED")) {
		        				plusColor = EnumChatFormatting.DARK_RED;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("DARK_AQUA")) {
		        				plusColor = EnumChatFormatting.DARK_AQUA;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("DARK_PURPLE")) {
		        				plusColor = EnumChatFormatting.DARK_PURPLE;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("DARK_GRAY")) {
		        				plusColor = EnumChatFormatting.DARK_GRAY;
		        			}
		        			else if (jsonResponsePlayer.getString("rankPlusColor").equals("BLACK")) {
		        				plusColor = EnumChatFormatting.BLACK;
		        			}
		        			try {
		        				if (jsonResponsePlayer.getString("monthlyPackageRank").equals("SUPERSTAR")) {
		        					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[MVP" + plusColor + "++" + EnumChatFormatting.GOLD + "] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
		        				}
		        				else {
		        					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[MVP" + plusColor + "+" + EnumChatFormatting.AQUA + "] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
		        				}
		        			}
		        			catch (Exception e) {
		        				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[MVP" + plusColor + "+" + EnumChatFormatting.AQUA + "] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
		        			}
		        		}
		        		else if (jsonResponsePlayer.getString("newPackageRank").equals("MVP")) {
		        			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[MVP] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s Hypixel Stats"));
		        		}
		        		else if (jsonResponsePlayer.getString("newPackageRank").equals("VIP_PLUS")) {
		        			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "[VIP" + EnumChatFormatting.GOLD + "+" + EnumChatFormatting.GREEN + "] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
		        		}
		        		else if (jsonResponsePlayer.getString("newPackageRank").equals("VIP")) {
		        			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "[VIP] " + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
		        		}
		        		else {
		        			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
		        		}
		        	}
		        	catch (Exception e) {
		        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + jsonResponsePlayer.getString("displayname") + EnumChatFormatting.YELLOW + "'s BedWars Stats"));
		        	}
	        	}
	        	sender.addChatMessage(new ChatComponentText(" "));
	        	try {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Bed destroyed: " + EnumChatFormatting.RED + jsonResponseBedwars.get("beds_broken_bedwars").toString()));
	        	}
	        	catch (Exception e) {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Bed destroyed: " + EnumChatFormatting.RED + "0"));
	        	}
	        	try {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Final Kills: " + EnumChatFormatting.RED + jsonResponseBedwars.get("final_kills_bedwars").toString()));
	        	}
	        	catch (Exception e) {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Final Kills: " + EnumChatFormatting.RED + "0"));
	        	}
	        	try {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Kills: " + EnumChatFormatting.RED + jsonResponseBedwars.get("kills_bedwars").toString()));
	        	}
	        	catch (Exception e) {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Kills: " + EnumChatFormatting.RED + "0"));
	        	}
	        	try {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Wins: " + EnumChatFormatting.RED + jsonResponseBedwars.get("wins_bedwars").toString()));
	        	}
	        	catch (Exception e) {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Wins: " + "0"));
	        	}
	        	try {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "BedWars Level: " + EnumChatFormatting.RED + jsonResponseAchievements.get("bedwars_level").toString()));
	        	}
	        	catch (Exception e) {
	        		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "BedWars Level: " + EnumChatFormatting.RED + "0"));
	        	}
	        	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Global Level: " + EnumChatFormatting.RED + networkLevel.toString()));
	        	sender.addChatMessage(new ChatComponentText(" "));
	        }
	        else {
	        	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This player doesn't exist"));
	        }
		}
		else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /bwstats <player>"));
		}
	}
}
