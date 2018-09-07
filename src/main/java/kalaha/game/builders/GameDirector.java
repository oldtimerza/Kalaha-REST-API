package kalaha.game.builders;

import kalaha.game.Game;

public class GameDirector {
   private GameBuilder gameBuilder;

   public GameDirector(GameBuilder gameBuilder) {
      this.gameBuilder = gameBuilder;
   }

   public Game getGame() throws Exception {
      gameBuilder.buildPlayers();
      gameBuilder.buildState();
      gameBuilder.buildBoard();
      return gameBuilder.construct();
   }
}
