package runner;

import game.players.Team;
import game.players.Player;
import game.Report;
import game.ThrowResult;
import io.Reader;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Match{
  private Report report = new Report();
  private Team team1;
  private Team team2;
  private Team actualTeam;
  private ArrayList<Integer> throwResults = new ArrayList<>();
  private int currentThrowValue = 0;

  public Match(Team team1, Team team2, ArrayList<Integer> throwResults){
    this.team1 = team1;
    this.team2 = team2;
    this.actualTeam = team1;
    this.throwResults = throwResults;
  }

  private void substitute(){
    if (actualTeam.equals(team1) && actualTeam.getScore()+6 < team2.getScore() && actualTeam.getArrayLength() > 3){
      actualTeam.substitute();
      report.addLog(actualTeam.getActualPlayer(), actualTeam.getSubPlayer());
    }
    else if(actualTeam.equals(team2) && actualTeam.getScore()+6 < team1.getScore() && actualTeam.getArrayLength() > 3){
      actualTeam.substitute();
      report.addLog(actualTeam.getActualPlayer(), actualTeam.getSubPlayer());
    }
  }

  public int throwBall(Player player){

    int level = player.getLevel();
    int result = throwResults.get(currentThrowValue);
    if (result > level){
      currentThrowValue += 1;
      report.addLog(player, ThrowResult.SIKERTELEN);
      return ThrowResult.SIKERTELEN;
    }
    else if(result == level){
      currentThrowValue += 1;
        report.addLog(player, ThrowResult.LEPATTANO);
        return ThrowResult.LEPATTANO;
    }
    currentThrowValue += 1;
    report.addLog(player, ThrowResult.SIKERES);
    return ThrowResult.SIKERES;
  }

  public void play(){
    while(actualTeam.getScore() <= 21){
      substitute();
      int secondThrow;
      int firstThrow = throwBall(actualTeam.getActualPlayer());
      if (firstThrow == ThrowResult.SIKERES){
        actualTeam.increasePoints(2);
      }
      else if (firstThrow == ThrowResult.LEPATTANO){
        secondThrow = throwBall(actualTeam.getActualPlayer());
        if(secondThrow == ThrowResult.SIKERES){
          actualTeam.increasePoints(1);
        }
        else{
          actualTeam.increasePoints(0);
        }
      }
      else{
        actualTeam.increasePoints(0);
      }
      //System.out.println(actualTeam.getActualPlayer().getName());
      actualTeam.nextPlayer();
      //System.out.println(actualTeam.getActualPlayer().getName());
      if(actualTeam.equals(team1)){
        actualTeam = team2;
      }
      else{
        actualTeam = team1;
      }
    }
    report.toFile();
    System.out.print(report.toString());
    if(team1.getScore() > team2.getScore()){
      System.out.println("A győztes csapat: " + team1.getName() + ", " + team1.getScore() + "pontot ért el.");
    }
    else{
      System.out.println("A győtes csapat " + team2.getName() + ", " + team2.getScore() + "ponot ért el.");
    }
  }

  public static void main(String[] args) throws IOException {
    ArrayList<Integer> results = Reader.readThrowResults("resources/throw_results.txt");
		Team teams[] = Reader.readTeams("resources/teams.txt");
		Match matchRunner = new Match(teams[0], teams[1], results);
		matchRunner.play();
  }


}
