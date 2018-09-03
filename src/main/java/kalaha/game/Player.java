package kalaha.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
   private List<Pit> pits = new ArrayList<>();
   private Kalaha kalaha;
   private int index;

   public Player(int index) {
      this.index = index;
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

   public int getIndex() {
      return index;
   }
}
