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
}