package com.adaptionsoft.games.uglytrivia;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  public void playerCanBePutInPenaltyBox() {
    // Given
    Player player = new Player("Donald");

    // When
    player.setInPenaltyBox();

    // Then
    Assertions.assertThat(player.isInPenaltyBox()).isTrue();
  }

  @Test
  public void playerCanWinGoldCoins() {
    // Given
    Player player = new Player("Vladimir");

    // When
    player.receiveOneGoldCoin();

    // Then
    Assertions.assertThat(player.getColdCoins()).isEqualTo(1);
  }
}