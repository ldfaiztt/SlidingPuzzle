package solution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * User: Ding
 * Date: 4/25/2014
 * Time: 10:50 AM
 */
public class Report {
    private class ReportData {
        public final long run_time;
        public final int run_cost;

        public ReportData(long run_time, int run_cost) {
            this.run_time = run_time;
            this.run_cost = run_cost;
        }
    }

    private long start_time;
    private final int maxDepth;
    private final Map<String, Map<Integer, List<ReportData>>> datas;

    public Report(List<String> tagList)
    {
        maxDepth = 40;
        datas = new HashMap<String, Map<Integer, List<ReportData>>>();
        for (String tag : tagList) {
            datas.put(tag,new HashMap<Integer, List<ReportData>>());
        }
    }

    public void StartRecord() {
        start_time = System.currentTimeMillis();

    }

    public void EndRecord(Puzzle pzl, Node sln) {
        RecordReport(System.currentTimeMillis() - start_time, pzl, sln);
    }

    private boolean AddData(final String tag, final int length, final long time, final int cost) {
        if (!datas.containsKey(tag)) {
            return false;
        }

        Map<Integer, List<ReportData>> data = datas.get(tag);
        if (!data.containsKey(length)) {
            data.put(length, new ArrayList<ReportData>() {{
                add(new ReportData(time, cost));
            }});
        } else {
            data.get(length).add(new ReportData(time, cost));
        }

        return true;
    }

    private boolean RecordReport(final long time, Puzzle pzl, Node sln) {
        if (sln != null) {
            return AddData(sln.getHuris().toString(), sln.getCost(), time, pzl.getNodesNumber());
        }

        return false;
    }

    private ReportData CalcAverage(List<ReportData> records) {
        if (records.size() <= 0) {
            return new ReportData(0,0);
        }

        long sum_time = 0;
        int sum_cost = 0;

        for (ReportData rd : records) {
            sum_cost += rd.run_cost;
            sum_time += rd.run_time;
        }

        return new ReportData(sum_time / records.size(), sum_cost / records.size());
    }

    public void RunReport(final String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }

        try {
            FileWriter writer = new FileWriter(file);

            writer.write("Depth ");
            for (String tag : datas.keySet()) {
                writer.write(tag + " ");
            }
            writer.write(System.lineSeparator());

            for (int i = 0; i <= maxDepth; i++) {
                writer.write(i + " ");
                for (Map<Integer, List<ReportData>> entry : datas.values()) {
                    if (entry.containsKey(i)){
                        List<ReportData> cases = entry.get(i);
                        ReportData average = CalcAverage(cases);
                        writer.write(average.run_cost + " " + average.run_time + " " + cases.size() + " ");
                    }
                    else {
                        writer.write("-- -- ");
                    }
                }
                writer.write(System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String splitLine() {
        String ret = "";
        for (int i = 0; i < 20; i++) {
            ret += "-";
        }
        ret += System.lineSeparator();

        return ret;
    }

    private static String linkBar() {
        String ret = "";
        ret += "   |  " + System.lineSeparator();
        ret += "   |  " + System.lineSeparator();
        ret += "   |  " + System.lineSeparator();
        ret += "   V  " + System.lineSeparator();

        return ret;
    }

    public static int reportNodeTree(String filename, Node end) {
        Node cur = end;
        Stack<Node> nodes = new Stack<Node>();
        while (!cur.isRoot()) {
            nodes.push(cur);
            cur = cur.getParent();
        }
        nodes.push(cur);

        if (nodes.isEmpty()) {
            return 0;
        }

        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(System.lineSeparator());
            writer.write(splitLine());

            Node root = nodes.pop();
            writer.write(root.toString());

            while (!nodes.isEmpty()) {
                writer.write(linkBar());
                writer.write(nodes.pop().toString());
            }

            writer.write(splitLine());
            writer.write(System.lineSeparator());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

}
