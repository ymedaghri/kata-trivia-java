package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

  public List<String> logs = new ArrayList<>();
  List<Player> playerList = new ArrayList<>();
  LinkedList popQuestions = new LinkedList();
  LinkedList scienceQuestions = new LinkedList();
  LinkedList sportsQuestions = new LinkedList();
  LinkedList rockQuestions = new LinkedList();
  int currentPlayer = 0;

  public Game() {
    for (int i = 0; i < 50; i++) {
      popQuestions.addLast(createPopQuestion(i));
      scienceQuestions.addLast(createScienceQuestion(i));
      sportsQuestions.addLast(createSportsQuestion(i));
      rockQuestions.addLast(createRockQuestion(i));
    }
  }

  private String createSportsQuestion(int i) {
    return "Sports Question " + i;
  }

  private String createScienceQuestion(int i) {
    return "Science Question " + i;
  }

  private String createPopQuestion(int i) {
    return "Pop Question " + i;
  }

  public String createRockQuestion(int index) {
    return "Rock Question " + index;
  }

  public void add(String playerName) {
    playerList.add(new Player(playerName));
    logPlayerWasAdded(playerName);
    logNumberOfPlayersPlaying();
  }

  public void roll(int roll) {
    logWhoIsTheCurrentPlayer();
    logDiceRolled(roll);
    getCurrentPlayer().play(roll);

    if (getCurrentPlayer().isGettingOutOfPenaltyBox()) {
      logCurrentPlayerGettingOutOfPenaltyBox();
      logCurrentPlayersLocation();
      logCategory();
      askQuestion();
    } else if (getCurrentPlayer().isInPenaltyBox()) {
      logCurrentPlayerNotGettingOutOfPenaltyBox();
    } else {
      logCurrentPlayersLocation();
      logCategory();
      askQuestion();
    }

  }

  private String getCurrentPlayerName() {
    return getCurrentPlayer().getName();
  }

  private Player getCurrentPlayer() {
    return playerList.get(currentPlayer);
  }

  private void askQuestion() {
    if (currentCategory() == "Pop") { log((String) popQuestions.removeFirst()); }
    if (currentCategory() == "Science") { log((String) scienceQuestions.removeFirst()); }
    if (currentCategory() == "Sports") { log((String) sportsQuestions.removeFirst()); }
    if (currentCategory() == "Rock") { log((String) rockQuestions.removeFirst()); }
  }


  private String currentCategory() {
    if (getCurrentPlayer().getPosition() == 0) { return "Pop"; }
    if (getCurrentPlayer().getPosition() == 4) { return "Pop"; }
    if (getCurrentPlayer().getPosition() == 8) { return "Pop"; }
    if (getCurrentPlayer().getPosition() == 1) { return "Science"; }
    if (getCurrentPlayer().getPosition() == 5) { return "Science"; }
    if (getCurrentPlayer().getPosition() == 9) { return "Science"; }
    if (getCurrentPlayer().getPosition() == 2) { return "Sports"; }
    if (getCurrentPlayer().getPosition() == 6) { return "Sports"; }
    if (getCurrentPlayer().getPosition() == 10) { return "Sports"; }
    return "Rock";
  }

  public boolean wasCorrectlyAnswered() {
    if (getCurrentPlayer().isGettingOutOfPenaltyBox()) {
      logCorrectAnswer();
      getCurrentPlayer().receiveOneGoldCoin();
      logPlayerMoney();

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == playerList.size()) { currentPlayer = 0; }

      return winner;
    } else if (getCurrentPlayer().isInPenaltyBox()) {

      currentPlayer++;
      if (currentPlayer == playerList.size()) { currentPlayer = 0; }
      return true;


    } else {

      logCorrectAnswer();
      getCurrentPlayer().receiveOneGoldCoin();
      logPlayerMoney();

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == playerList.size()) { currentPlayer = 0; }

      return winner;
    }
  }

  public boolean wrongAnswer() {
    logQuestionIncorrect();
    logCurrentPlayerPutInPenaltyBox();
    getCurrentPlayer().setInPenaltyBox();

    currentPlayer++;
    if (currentPlayer == playerList.size()) { currentPlayer = 0; }
    return true;
  }


  private boolean didPlayerWin() {
    return !(getCurrentPlayer().getColdCoins() == 6);
  }

  private void logNumberOfPlayersPlaying() {
    log("They are player number " + playerList.size());
  }

  private void logPlayerWasAdded(String playerName) {
    log(playerName + " was added");
  }

  private void logCurrentPlayerPutInPenaltyBox() {
    log(getCurrentPlayerName() + " was sent to the penalty box");
  }

  private void logQuestionIncorrect() {
    log("Question was incorrectly answered");
  }

  private void logCurrentPlayerNotGettingOutOfPenaltyBox() {
    log(getCurrentPlayerName() + " is not getting out of the penalty box");
  }

  private void logCategory() {
    log("The category is " + currentCategory());
  }

  private void logCurrentPlayersLocation() {
    log(getCurrentPlayerName() + "'s new location is " + getCurrentPlayer().getPosition());
  }

  private void logCurrentPlayerGettingOutOfPenaltyBox() {
    log(getCurrentPlayerName() + " is getting out of the penalty box");
  }

  private void logDiceRolled(int roll) {
    log("They have rolled a " + roll);
  }

  private void logWhoIsTheCurrentPlayer() {
    log(getCurrentPlayerName() + " is the current player");
  }

  private void logPlayerMoney() {
    log(getCurrentPlayerName() + " now has " + getCurrentPlayer().getColdCoins() + " Gold Coins.");
  }

  private void logCorrectAnswer() {
    log("Answer was correct!!!!");
  }

  public void log(String message) {
    logs.add(message);
    System.out.println(message);
  }
}
