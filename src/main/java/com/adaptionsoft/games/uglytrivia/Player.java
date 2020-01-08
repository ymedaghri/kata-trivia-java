package com.adaptionsoft.games.uglytrivia;

public class Player {

  int position;
  private String playerName;
  private int goldCoins;
  private boolean isInPenaltyBox;
  private boolean isGettingOutOfPenaltyBox;

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

  public void play(int roll) {
    isGettingOutOfPenaltyBox = isInPenaltyBox && roll % 2 != 0;
    isInPenaltyBox = isInPenaltyBox && !isGettingOutOfPenaltyBox;

    if (!isInPenaltyBox) {
      position = (position + roll) % 12;
    }
  }

  public boolean isGettingOutOfPenaltyBox() {
    return isGettingOutOfPenaltyBox;
  }

  public int getPosition() {
    return position;
  }
}
