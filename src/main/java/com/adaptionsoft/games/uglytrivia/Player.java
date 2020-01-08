package com.adaptionsoft.games.uglytrivia;

public class Player {

  private String playerName;
  private boolean isInPenaltyBox;
  private int goldCoins;

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

  public int getColdCoins() {
    return goldCoins;
  }

  public void receiveOneGoldCoin() {
    this.goldCoins++;
  }
}
