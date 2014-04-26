package utest;

import eval.HeuristicFacotry;
import model.Plain;
import model.State;
import org.junit.Before;
import org.junit.Test;
import model.Node;
import solution.Puzzle;
import solution.Report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: Ding
 * Date: 4/25/2014
 * Time: 10:40 PM
 */
public class NodeTreeTest {
    private State goal;
    private int size;

    @Before
    public void setUp() throws Exception {
        size = 3;
        goal = new Plain(size, true);
    }

    @Test
    public void testRandomDistance() throws Exception {
        int count = 0;
        while (count < 3) {
            Puzzle puzzle = new Puzzle(size, HeuristicFacotry.typeHeuristic.Distance, null);
            Node sln = puzzle.Run();

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);

            if (sln.getCost() > 10) {
                count += Report.reportNodeTree("DisTree.txt", sln);
            }
        }
    }

    @Test
    public void testRandomMisplaced() throws Exception {
        int count = 0;
        while (count < 3) {
            Puzzle puzzle = new Puzzle(size, HeuristicFacotry.typeHeuristic.Misplaced, null);
            Node sln = puzzle.Run();

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);

            if (sln.getCost() > 10) {
                count += Report.reportNodeTree("MisTree.txt", sln);
            }
        }
    }
}
