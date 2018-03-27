package io;

import java.io.*;
import java.util.*;
import java.lang.*;
import game.players.Team;
import game.players.Player;

public class Reader{

	public static ArrayList<Integer> readThrowResults(String fileName){
		File inFile = new File(fileName);
		ArrayList<Integer> results = new ArrayList<>();
		String newLine;
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((newLine = br.readLine()) != null){
				results.add(Integer.parseInt(newLine));
			}
		} catch (IOException e){
			System.out.println("IOException");
		}
		return results;
	}

	public static Team[] readTeams(String fileName){
		Team[] teams = new Team[2];
		String teamName = "";
		int playersNum = 0;
		int palyerLevel = 0;
		String playerName = "";
		try{
			File inFile = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			for (int i = 0; i < 2; ++i){
				teamName = br.readLine();
				//System.out.println(teamName);
				playersNum = Integer.parseInt(br.readLine());
				Player[] playerArray = new Player[playersNum];
				for (int j = 0; j < playersNum; ++j){
					String[] lvlName;
					String ln = br.readLine();
					lvlName = ln.split(" ", 2);
					//System.out.println(lvlName.length);
					Player player = new Player(lvlName[1], Integer.parseInt(lvlName[0]));
					playerArray[j] = player;
				}
				teams[i] = new Team(teamName, playerArray);
			}
		}catch(IOException e){
			System.out.println("IOException");
		}
		return teams;
	}

}
