package game;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class PitTest {
    @Test
    public void shouldAddStoneToListOfStones(){
        List<Stone> stones = new ArrayList<>();
        Player player = new Player();
        Pit pit = new Pit(stones, player);
        List<Stone> stonesToInsert = new ArrayList<>();
        stonesToInsert.add(new Stone());
        pit.addStones(stonesToInsert);
        Assert.assertThat(pit.getStones(), equalTo(stonesToInsert));
    }

    @Test
    public void shouldGetAllStonesAndSetPitsStonesToEmpty(){
        List<Stone> stones = new ArrayList<Stone>();
        stones.add(new Stone());
        stones.add(new Stone());
        stones.add(new Stone());
        Player player = new Player();
        Pit pit = new Pit(stones, player);

        List<Stone> actualStones = pit.removeStones();
        Assert.assertThat(pit.getStones().size(), equalTo(0));
        Assert.assertThat(actualStones, equalTo(stones));
    }
}
