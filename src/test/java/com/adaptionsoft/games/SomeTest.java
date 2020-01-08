package com.adaptionsoft.games;


import com.adaptionsoft.games.uglytrivia.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class SomeTest {

  class GameWithSeam extends Game {

    @Override
    public void log(Object message) {
      logs.add((String) message);
    }
  }

  @Test
  @Disabled
  public void utilitaireDeCaracterisation() {


    class RollWithResult {

      int roll;
      boolean answer;

      public RollWithResult(int roll, boolean answer) {
        this.roll = roll;
        this.answer = answer;
      }

      @Override
      public String toString() {
        String answercall = (answer) ? "aGame.wasCorrectlyAnswered();" : "aGame.wrongAnswer();";
        return "aGame.roll(" + roll + "); " + answercall;

      }
    }

    boolean notAWinner;

    List<RollWithResult> rollWithResults = new ArrayList<>();

    Game aGame = new GameWithSeam();

    aGame.add("Chet");
    aGame.add("Pat");
    aGame.add("Sue");

    Random rand = new Random();

    do {

      int roll = rand.nextInt(5) + 1;

      aGame.roll(roll);

      if (rand.nextInt(9) == 7) {
        notAWinner = aGame.wrongAnswer();
        rollWithResults.add(new RollWithResult(roll, false));
      } else {
        notAWinner = aGame.wasCorrectlyAnswered();
        rollWithResults.add(new RollWithResult(roll, true));
      }


    } while (notAWinner);

    System.out.println("Entrées : ");
    System.out.println("----------");
    System.out.println(
        rollWithResults.stream().map(RollWithResult::toString).collect(Collectors.joining("\n")));

    System.out.println("");
    System.out.println("Sorties : ");
    System.out.println("----------");
    System.out.println(aGame.logs.stream().collect(Collectors.joining("\n")));


  }

  @Test
  public void aCompleteGame() {
    // Given

    Game aGame = new GameWithSeam();

    aGame.add("Chet");
    aGame.add("Pat");
    aGame.add("Sue");

    // When

    aGame.roll(4);
    aGame.wasCorrectlyAnswered();
    aGame.roll(1);
    aGame.wasCorrectlyAnswered();
    aGame.roll(2);
    aGame.wasCorrectlyAnswered();
    aGame.roll(5);
    aGame.wasCorrectlyAnswered();
    aGame.roll(2);
    aGame.wasCorrectlyAnswered();
    aGame.roll(5);
    aGame.wasCorrectlyAnswered();
    aGame.roll(3);
    aGame.wasCorrectlyAnswered();
    aGame.roll(5);
    aGame.wrongAnswer();
    aGame.roll(3);
    aGame.wasCorrectlyAnswered();
    aGame.roll(2);
    aGame.wasCorrectlyAnswered();
    aGame.roll(4);
    aGame.wasCorrectlyAnswered();
    aGame.roll(4);
    aGame.wasCorrectlyAnswered();
    aGame.roll(1);
    aGame.wrongAnswer();
    aGame.roll(2);
    aGame.wasCorrectlyAnswered();
    aGame.roll(4);
    aGame.wasCorrectlyAnswered();
    aGame.roll(4);
    aGame.wasCorrectlyAnswered();
    aGame.roll(1);
    aGame.wasCorrectlyAnswered();
    aGame.roll(5);
    aGame.wasCorrectlyAnswered();

    // Then

    Assertions.assertThat(aGame.logs.stream().collect(Collectors.joining("\n"))).isEqualTo(
        "Chet was added\n" + "They are player number 1\n" + "Pat was added\n"
            + "They are player number 2\n" + "Sue was added\n" + "They are player number 3\n"
            + "Chet is the current player\n" + "They have rolled a 4\n"
            + "Chet's new location is 4\n" + "The category is Pop\n" + "Pop Question 0\n"
            + "Answer was corrent!!!!\n" + "Chet now has 1 Gold Coins.\n"
            + "Pat is the current player\n" + "They have rolled a 1\n" + "Pat's new location is 1\n"
            + "The category is Science\n" + "Science Question 0\n" + "Answer was corrent!!!!\n"
            + "Pat now has 1 Gold Coins.\n" + "Sue is the current player\n"
            + "They have rolled a 2\n" + "Sue's new location is 2\n" + "The category is Sports\n"
            + "Sports Question 0\n" + "Answer was corrent!!!!\n" + "Sue now has 1 Gold Coins.\n"
            + "Chet is the current player\n" + "They have rolled a 5\n"
            + "Chet's new location is 9\n" + "The category is Science\n" + "Science Question 1\n"
            + "Answer was corrent!!!!\n" + "Chet now has 2 Gold Coins.\n"
            + "Pat is the current player\n" + "They have rolled a 2\n" + "Pat's new location is 3\n"
            + "The category is Rock\n" + "Rock Question 0\n" + "Answer was corrent!!!!\n"
            + "Pat now has 2 Gold Coins.\n" + "Sue is the current player\n"
            + "They have rolled a 5\n" + "Sue's new location is 7\n" + "The category is Rock\n"
            + "Rock Question 1\n" + "Answer was corrent!!!!\n" + "Sue now has 2 Gold Coins.\n"
            + "Chet is the current player\n" + "They have rolled a 3\n"
            + "Chet's new location is 0\n" + "The category is Pop\n" + "Pop Question 1\n"
            + "Answer was corrent!!!!\n" + "Chet now has 3 Gold Coins.\n"
            + "Pat is the current player\n" + "They have rolled a 5\n" + "Pat's new location is 8\n"
            + "The category is Pop\n" + "Pop Question 2\n" + "Question was incorrectly answered\n"
            + "Pat was sent to the penalty box\n" + "Sue is the current player\n"
            + "They have rolled a 3\n" + "Sue's new location is 10\n" + "The category is Sports\n"
            + "Sports Question 1\n" + "Answer was corrent!!!!\n" + "Sue now has 3 Gold Coins.\n"
            + "Chet is the current player\n" + "They have rolled a 2\n"
            + "Chet's new location is 2\n" + "The category is Sports\n" + "Sports Question 2\n"
            + "Answer was corrent!!!!\n" + "Chet now has 4 Gold Coins.\n"
            + "Pat is the current player\n" + "They have rolled a 4\n"
            + "Pat is not getting out of the penalty box\n" + "Sue is the current player\n"
            + "They have rolled a 4\n" + "Sue's new location is 2\n" + "The category is Sports\n"
            + "Sports Question 3\n" + "Answer was corrent!!!!\n" + "Sue now has 4 Gold Coins.\n"
            + "Chet is the current player\n" + "They have rolled a 1\n"
            + "Chet's new location is 3\n" + "The category is Rock\n" + "Rock Question 2\n"
            + "Question was incorrectly answered\n" + "Chet was sent to the penalty box\n"
            + "Pat is the current player\n" + "They have rolled a 2\n"
            + "Pat is not getting out of the penalty box\n" + "Sue is the current player\n"
            + "They have rolled a 4\n" + "Sue's new location is 6\n" + "The category is Sports\n"
            + "Sports Question 4\n" + "Answer was corrent!!!!\n" + "Sue now has 5 Gold Coins.\n"
            + "Chet is the current player\n" + "They have rolled a 4\n"
            + "Chet is not getting out of the penalty box\n" + "Pat is the current player\n"
            + "They have rolled a 1\n" + "Pat is getting out of the penalty box\n"
            + "Pat's new location is 9\n" + "The category is Science\n" + "Science Question 2\n"
            + "Answer was correct!!!!\n" + "Pat now has 3 Gold Coins.\n"
            + "Sue is the current player\n" + "They have rolled a 5\n"
            + "Sue's new location is 11\n" + "The category is Rock\n" + "Rock Question 3\n"
            + "Answer was corrent!!!!\n" + "Sue now has 6 Gold Coins.");
  }
}
