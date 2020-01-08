package com.adaptionsoft.games.uglytrivia;

public class Player {

  private String playerName;
  private boolean isInPenaltyBox;

  public Player(String playerName) {
    this.playerName = playerName;
  }

  public String getName() {
    return playerName;
  }

  public boolean isInPenaltyBox() {
    return isInPenaltyBox;
  }

  public void setInPenaltyBox() {
    this.isInPenaltyBox = true;
  }
}
