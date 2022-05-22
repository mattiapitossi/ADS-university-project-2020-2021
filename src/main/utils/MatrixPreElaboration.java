package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixPreElaboration {


    public ArrayList<List<String>> convert2DarrayToList(String[][] rows) {
        var res = new ArrayList<List<String>>();
        for (String[] row : rows) {
            res.add(Arrays.asList(row));
        }
        return res;
    }

    //implement remove rows
    public List<List<String>> removeRows(List<List<String>> array) {
        var found = new ArrayList<List<String>>();
        for (var i = 0; i < array.size() - 1; i++) {
            for (var j = i + 1; j < array.size(); j++) {
                if (array.get(i).containsAll(array.get(j).stream().filter(c -> !c.equals("0")).toList())) {
                    found.add(array.get(i));
                } else if (array.get(j).containsAll(array.get(i).stream().filter(c -> !c.equals("0")).toList())) {
                    found.add(array.get(j));
                }
            }
        }
        array.removeAll(found);
        return array;
    }

    //implement remove columns

    //implement convert from list to 2D array

    /*
    public List<String> removeRows(List<String> ctx) {
        var list = new ArrayList<ArrayList<String>>();
        ctx.forEach(element -> {
            var toInsert = new ArrayList<String>();
            for (char c : element.toCharArray()) {
                toInsert.add(String.valueOf(c));
            }
            list.add(toInsert);
            toInsert.clear();
        });
        var listOfString = new ArrayList<String>();
        list.forEach(c -> {
            listOfString.add(String.join("", c));
        });
        listOfString.forEach(System.out::println);
        return listOfString;
    }
    */

}
