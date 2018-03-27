package game;

import java.util.ArrayList;
import java.io.*;
import game.players.Player;
import game.ThrowResult;

public class Report{
  private ArrayList<String> reports = new ArrayList<>();

  public void addLog(Player name, int score){
    if (score == 0){
    reports.add(name.getName() + " játékos dobásának eredménye: SIKERTELEN");
    }
    else if(score == 1){
      if(reports.get((reports.size()) - 1).equals(name.getName() + " játékos dobásának eredménye: LEPATTANO")){
        reports.add(name.getName() + " játékos dobásának eredménye: SIKERTELEN");
      }
      else{
        reports.add(name.getName() + " játékos dobásának eredménye: LEPATTANO");
      }
    }
    else{
      reports.add(name.getName() + " játékos dobásának eredménye: SIKERES");
    }
  }
  public void addLog(Player player1, Player player2){
    reports.add(player2.getName() + " játékos helyett " + player1.getName() + " a pályán");
  }

  @Override
  public String toString(){
    String newLine = "";
    if (reports.isEmpty()){
      return "A merkozes meg nem kezdodott el!" + '\n';
    }
    else{
      for (int i = 0; i < reports.size(); ++i){
        newLine += reports.get(i) + '\n';
      }
      return newLine;
    }
  }

  public void toFile(){
    File reportFile = new File("resources/reports.txt");
    try{
    if (!reportFile.exists()){
      reportFile.createNewFile();
    }
      BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile));
      bw.write(toString());
      bw.close();
    } catch(IOException e){
      System.out.println("IOException caught!");
    };

  }

}
