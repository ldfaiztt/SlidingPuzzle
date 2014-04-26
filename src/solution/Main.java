package solution;

import eval.HeuristicFacotry;
import model.Plain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User: Ding
 * Date: 4/19/2014
 * Time: 11:57 PM
 */
public class Main {
    private static List<Integer> getInput(String filename) throws FileNotFoundException {
        String path = new File(filename).getAbsolutePath();
        FileReader reader = new FileReader(path);
        Scanner scanner = new Scanner(reader);

        List<Integer> ret = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            ret.add(scanner.nextInt());
        }

        return ret;
    }

    public static void main(String[] args) {
        String strInput = "";
        String strHeuristic = "";
        String strOutput = "";
        int size = 3;

        if (args.length >= 1) {
            strHeuristic = args[0];
        }
        if (args.length >= 2) {
            strOutput = args[1];
        }
        if (args.length >= 3) {
            strInput = args[2];
        }
        if (args.length >= 4) {
            try {
                size = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                size = 3;
            }
        }

        List<Integer> inputList = null;
        if (!strInput.isEmpty()) {
            try {
                inputList = getInput(strInput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

        if (inputList != null && inputList.size() > 0) {
            if (Math.pow(size, 2) != inputList.size()) {
                System.err.println("Input size = " + size + " which is conflicted with amount of the data in the " + strInput);
                return;
            } else if (!Plain.checkLegal(inputList)) {
                System.err.println("The input array cannot be solved");
                return;
            }
        }

        HeuristicFacotry.typeHeuristic hType = HeuristicFacotry.typeHeuristic.Distance;
        if (strHeuristic.toLowerCase().contains("misplaced")) {
            hType = HeuristicFacotry.typeHeuristic.Misplaced;
        }

        Puzzle puzzle = new Puzzle(size, hType, inputList);
        Node sln = puzzle.Run();
        if (sln == null) {
            System.out.println("Puzzle failed!");
        } else {
            System.out.println("Puzzle success on: " + System.lineSeparator() + sln.toString() + sln.getHuris());

            if (!strOutput.isEmpty()) {
                Report.reportNodeTree(strOutput, sln);
            }
        }
    }
}
