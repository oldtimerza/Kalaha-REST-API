package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
   private List<Pit> pits = new ArrayList<>();
   private Kalaha kalaha;

   public Player() {
   }

   public List<Pit> getPits() {
      return pits;
   }

   public boolean ownsPit(Pit pit){
      return pits.contains(pit);
   }

   public void addPit(Pit pit) {
      pits.add(pit);
   }

   public Kalaha getKalaha() {
      return kalaha;
   }

   public void setKalaha(Kalaha kalaha) {
      this.kalaha = kalaha;
   }
}
