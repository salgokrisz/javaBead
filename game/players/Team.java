package game.players;

import java.util.*;
//import java.io.*;
import game.players.Player;

public class Team{
	private String name;
	private Player[] players;
	private int points;

	public Team(String name, Player[] playerArray){
		this.name = name;
		this.players = playerArray;
	}

	public Player getActualPlayer(){
		return this.players[0];
	}

	public void nextPlayer(){
				Player sg = players[0];
				players[0] = players[1];
				players[1] = players[2];
				players[2] = sg;
	}

	public void increasePoints(int plusPont){
		this.points += plusPont;
	}
  public boolean equals(Team compareTeam){
    return this.players.equals(compareTeam.players);
  }

	public void substitute(){
		if (players.length > 3){
			Player temp = players[0];
			players[0] = players[3];
			for(int i = 4; i < (players.length)-1; ++i){
				players[i-1] = players[i];
			}
			players[(players.length)-1] = temp;
		}
	}

  public String getName(){
    return this.name;
  }

  public int getScore(){
    return this.points;
  }

  public Player getSubPlayer(){
    return this.players[players.length -1];
  }

  public int getArrayLength(){
    return players.length;
  }

}
