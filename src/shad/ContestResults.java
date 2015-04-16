package shad;

import java.util.*;


public class ContestResults {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        List<Map.Entry<Integer, Integer>> contestResults = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = in.nextInt();
            int result = in.nextInt();
            contestResults.add(new AbstractMap.SimpleEntry<>(id, result));
        }

        class EntryComparer implements Comparator<Map.Entry<Integer, Integer>> {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int result = (-1) * Integer.compare(o1.getValue(), o2.getValue());
                if (result == 0)
                    return Integer.compare(o1.getKey(), o2.getKey());
                return result;
            }
        }

        Collections.sort(contestResults,  new EntryComparer());


        for (Map.Entry<Integer, Integer> values : contestResults) {
            System.out.println(values.getKey() + " " + values.getValue());
        }
    }


}
