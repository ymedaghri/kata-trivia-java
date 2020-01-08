package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

  public List<String> logs = new ArrayList<>();
  List<Player> playerList = new ArrayList<>();
  int[] places = new int[6];
  int[] purses = new int[6];
  LinkedList popQuestions = new LinkedList();
  LinkedList scienceQuestions = new LinkedList();
  LinkedList sportsQuestions = new LinkedList();
  LinkedList rockQuestions = new LinkedList();
  int currentPlayer = 0;
  boolean isGettingOutOfPenaltyBox;

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

  public boolean add(String playerName) {
    playerList.add(new Player(playerName));
    places[howManyPlayers()] = 0;
    purses[howManyPlayers()] = 0;

    log(playerName + " was added");
    log("They are player number " + playerList.size());
    return true;
  }

  public int howManyPlayers() {
    return playerList.size();
  }

  public void roll(int roll) {
    log(getCurrentPlayerName() + " is the current player");
    log("They have rolled a " + roll);

    if (getCurrentPlayer().isInPenaltyBox()) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        log(getCurrentPlayerName() + " is getting out of the penalty box");
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) { places[currentPlayer] = places[currentPlayer] - 12; }

        log(getCurrentPlayerName() + "'s new location is " + places[currentPlayer]);
        log("The category is " + currentCategory());
        askQuestion();
      } else {
        log(getCurrentPlayerName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {

      places[currentPlayer] = places[currentPlayer] + roll;
      if (places[currentPlayer] > 11) { places[currentPlayer] = places[currentPlayer] - 12; }

      log(getCurrentPlayerName() + "'s new location is " + places[currentPlayer]);
      log("The category is " + currentCategory());
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
    if (currentCategory() == "Pop") { log(popQuestions.removeFirst()); }
    if (currentCategory() == "Science") { log(scienceQuestions.removeFirst()); }
    if (currentCategory() == "Sports") { log(sportsQuestions.removeFirst()); }
    if (currentCategory() == "Rock") { log(rockQuestions.removeFirst()); }
  }


  private String currentCategory() {
    if (places[currentPlayer] == 0) { return "Pop"; }
    if (places[currentPlayer] == 4) { return "Pop"; }
    if (places[currentPlayer] == 8) { return "Pop"; }
    if (places[currentPlayer] == 1) { return "Science"; }
    if (places[currentPlayer] == 5) { return "Science"; }
    if (places[currentPlayer] == 9) { return "Science"; }
    if (places[currentPlayer] == 2) { return "Sports"; }
    if (places[currentPlayer] == 6) { return "Sports"; }
    if (places[currentPlayer] == 10) { return "Sports"; }
    return "Rock";
  }

  public boolean wasCorrectlyAnswered() {
    if (getCurrentPlayer().isInPenaltyBox()) {
      if (isGettingOutOfPenaltyBox) {
        log("Answer was correct!!!!");
        purses[currentPlayer]++;
        log(getCurrentPlayerName() + " now has " + purses[currentPlayer] + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == playerList.size()) { currentPlayer = 0; }

        return winner;
      } else {
        currentPlayer++;
        if (currentPlayer == playerList.size()) { currentPlayer = 0; }
        return true;
      }


    } else {

      log("Answer was corrent!!!!");
      purses[currentPlayer]++;
      log(getCurrentPlayerName() + " now has " + purses[currentPlayer] + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == playerList.size()) { currentPlayer = 0; }

      return winner;
    }
  }

  public boolean wrongAnswer() {
    log("Question was incorrectly answered");
    log(getCurrentPlayerName() + " was sent to the penalty box");
    getCurrentPlayer().setInPenaltyBox();

    currentPlayer++;
    if (currentPlayer == playerList.size()) { currentPlayer = 0; }
    return true;
  }


  private boolean didPlayerWin() {
    return !(purses[currentPlayer] == 6);
  }

  public void log(Object message) {

    logs.add((String)message);
    System.out.println(message);
  }
}
