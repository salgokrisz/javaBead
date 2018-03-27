package game.players;

import java.util.*;

public class Player{
  private String name;
  private int level;

  public Player(String name, int level){
    this.name = name;
    this.level = level;
  }

  public boolean equals(Player newPlayer){
    if (this.name != null){
      if (newPlayer != null){
        if (this.name.equals(newPlayer.name) && this.level == newPlayer.level){
          return true;
        }
      }
    }
      return false;
  }

  public String getName(){
    return this.name;
  }

  public int getLevel(){
    return this.level;
  }
}
