package com.adaptionsoft.games.uglytrivia;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

  @Test
  void playerCanBePutInPenaltyBox() {
    // Given
    Player player = new Player("Vlad");

    // When
    player.setInPenaltyBox();

    // Then
    Assertions.assertThat(player.isInPenaltyBox()).isTrue();
  }

  @Test
  void playerCanWinGoldCoins() {
    // Given
    Player player = new Player("Vlad");

    // When
    player.receiveOneGoldCoin();

    // Then
    Assertions.assertThat(player.getColdCoins()).isEqualTo(1);
  }

  @ParameterizedTest(name = "when roll equals {arguments}")
  @ValueSource(ints = { 1, 3, 5 })
  void playerShouldGetOutOfPenaltyBoxIfInItAndPlayingAnOddRoll(int roll) {
    // Given
    Player player = new Player("Vlad");
    player.setInPenaltyBox();

    // When
    player.play(roll);

    // Then
    Assertions.assertThat(player.isGettingOutOfPenaltyBox()).isTrue();
    Assertions.assertThat(player.isInPenaltyBox()).isFalse();
  }

  @ParameterizedTest(name = "when roll equals {arguments}")
  @ValueSource(ints = { 2, 4, 6 })
  void playerShouldNotGetOutOfPenaltyBoxIfInItAndPlayingAnEvenRoll(int roll) {
    // Given
    Player player = new Player("Vlad");
    player.setInPenaltyBox();

    // When
    player.play(roll);

    // Then
    Assertions.assertThat(player.isGettingOutOfPenaltyBox()).isFalse();
    Assertions.assertThat(player.isInPenaltyBox()).isTrue();
  }

  @ParameterizedTest(name = "when roll equals {arguments}")
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
  void playerShouldNotGetOutOfPenaltyBoxIfNotInItAndPlayingAnyRoll(int roll) {
    // Given
    Player player = new Player("Vlad");

    // When
    player.play(roll);

    // Then
    Assertions.assertThat(player.isGettingOutOfPenaltyBox()).isFalse();
    Assertions.assertThat(player.isInPenaltyBox()).isFalse();
  }
}