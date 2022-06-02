package utils;

import java.util.*;

public class MatrixPreElaboration {

    public List<List<String>> removeRows(List<List<String>> array) {
        var found = new ArrayList<List<String>>();
        for (var i = 0; i < array.size() - 1; i++) {
            for (var j = i + 1; j < array.size(); j++) {
                if (new HashSet<>(array.get(i)).containsAll(array.get(j)
                        .stream().filter(c -> !c.equals("0"))
                        .toList()) && !found.contains(array.get(i))) {
                    found.add(array.get(i));
                } else if (new HashSet<>(array.get(j)).containsAll(array.get(i)
                        .stream().filter(c -> !c.equals("0"))
                        .toList()) && !found.contains(array.get(j))) {
                    found.add(array.get(j));
                }
            }
        }
        array.removeAll(found);
        return array;
    }

    public List<List<String>> removeColumns(List<List<String>> array) {
        //for each column
        var indexToRemove = new ArrayList<Integer>();
        var found = false;
        for (var i = 0; i < array.get(0).size(); i++) {
            //for each row of the column
            for (List<String> strings : array) {
                if (!strings.get(i).equals("0")) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                indexToRemove.add(i);
            }
            found = false;
        }
        Collections.reverse(indexToRemove);
        array.forEach(row -> indexToRemove.forEach(idx -> row.remove(idx.intValue())));
        return array;
    }

    //implement convert from list to 2D array
    public String[][] convertFromListToArray(List<List<String>> array) {
        return array.stream().map(l -> l.toArray(String[]::new)).toArray(String[][]::new);
    }

}
