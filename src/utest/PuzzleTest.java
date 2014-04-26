package utest;

import eval.HeuristicFacotry;
import model.Plain;
import model.State;
import org.junit.Before;
import org.junit.Test;
import model.Node;
import solution.Puzzle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: Ding
 * Date: 4/25/2014
 * Time: 6:39 PM
 */
public class PuzzleTest {
    private State goal;
    private int size;

    @Before
    public void setUp() throws Exception {
        size = 3;
        goal = new Plain(size, true);
    }

    @Test
    public void testRandomDistance() throws Exception {
        for (int i = 0; i < 100; i++) {
            Puzzle puzzle = new Puzzle(size, HeuristicFacotry.typeHeuristic.Distance, null);
            Node sln = puzzle.Run();

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);

            //System.out.println("Puzzle success on: " + System.lineSeparator() + sln.toString() + sln.getHuris() + System.lineSeparator());
        }
    }

    @Test
    public void testRandomMisplaced() throws Exception {
        for (int i = 0; i < 100; i++) {
            Puzzle puzzle = new Puzzle(size, HeuristicFacotry.typeHeuristic.Misplaced, null);
            Node sln = puzzle.Run();

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);

            //System.out.println("Puzzle success on: " + System.lineSeparator() + sln.toString() + sln.getHuris() + System.lineSeparator());
        }
    }
}
