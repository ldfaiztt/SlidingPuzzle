package utest;

import eval.HeuristicFacotry;
import junit.framework.TestCase;
import model.Plain;
import model.State;
import solution.Node;
import solution.Puzzle;

/**
 * User: Ding
 * Date: 4/19/2014
 * Time: 9:33 PM
 */
public class PuzzleTest extends TestCase {
    private State goal;
    private final int size = 3;

    public void setUp() throws Exception {
        super.setUp();
        goal = new Plain(size, true);
    }

    public void tearDown() throws Exception {

    }

    public void testRandomDistance() throws Exception {
        for (int i = 0; i < 100; i++) {
            Puzzle puzzle = new Puzzle(3, HeuristicFacotry.typeHeuristic.Distance, null);
            Node sln = puzzle.Run();

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);

            System.out.println("Puzzle success on: " + System.lineSeparator() + sln.toString() + sln.getHuris() + System.lineSeparator());
        }
    }

    public void testRandomMisplaced() throws Exception {
        for (int i = 0; i < 100; i++) {
            Puzzle puzzle = new Puzzle(3, HeuristicFacotry.typeHeuristic.Misplaced, null);
            Node sln = puzzle.Run();

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);

            System.out.println("Puzzle success on: " + System.lineSeparator() + sln.toString() + sln.getHuris() + System.lineSeparator());
        }
    }
}
