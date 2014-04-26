package utest;

import eval.HeuristicFacotry;
import model.Plain;
import model.State;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import solution.Node;
import solution.Puzzle;
import solution.Report;
import solution.ReversePuzzle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: Ding
 * Date: 4/25/2014
 * Time: 11:28 AM
 */
public class ReportTest {
    private State goal;
    private int size;
    private int trials;

    private static final Report report;
    static {
        List<String> tagList = new ArrayList<String>();
        for (HeuristicFacotry.typeHeuristic hType : HeuristicFacotry.typeHeuristic.values()) {
            tagList.add(HeuristicFacotry.getHeuristicInstance(hType).toString());
        }
        report = new Report(tagList);
    }

    @Before
    public void setUp() throws Exception {
        size = 3;
        trials = 10000;
        goal = new Plain(size, true);
    }

    @Test
    public void testRandomMisplaced() throws Exception {
        for (int i = 0; i < trials; i++) {
            Puzzle puzzle = new Puzzle(size, HeuristicFacotry.typeHeuristic.Misplaced, null);

            report.StartRecord();
            Node sln = puzzle.Run();
            report.EndRecord(puzzle, sln);

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);
        }
    }

    @Test
    public void testRandomDistance() throws Exception {
        for (int i = 0; i < trials; i++) {
            Puzzle puzzle = new Puzzle(size, HeuristicFacotry.typeHeuristic.Distance, null);

            report.StartRecord();
            Node sln = puzzle.Run();
            report.EndRecord(puzzle, sln);

            assertNotNull(sln);
            assertEquals(sln.getState(), goal);
        }
    }

    @Test
    public void testSpecificDistanceSteps() {
        List<State> initStateList = new ArrayList<State>();
        for (int i = 1; i <= 30 ; i++) {
            for (int j = 0; j < 100; j++) {
                initStateList.add(ReversePuzzle.generateState(goal, i));
            }
        }

        for (State init : initStateList) {
            Puzzle disPuzzle = new Puzzle(HeuristicFacotry.typeHeuristic.Distance, init);
            report.StartRecord();
            Node disSln = disPuzzle.Run();
            report.EndRecord(disPuzzle, disSln);
            assertNotNull(disSln);
            assertEquals(disSln.getState(), goal);
        }
    }

    @Test
    public void testSpecificMisplacedSteps() {
        List<State> initStateList = new ArrayList<State>();
        for (int i = 1; i <= 30 ; i++) {
            for (int j = 0; j < 100; j++) {
                initStateList.add(ReversePuzzle.generateState(goal, i));
            }
        }

        for (State init : initStateList) {
            Puzzle misPuzzle = new Puzzle(HeuristicFacotry.typeHeuristic.Misplaced, init);
            report.StartRecord();
            Node misSln = misPuzzle.Run();
            report.EndRecord(misPuzzle, misSln);
            assertNotNull(misSln);
            assertEquals(misSln.getState(), goal);
        }
    }

    @AfterClass
    public static void OneTimeTeardown() {
        report.RunReport("log.txt");
    }
}
