package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MBaseRefactorprova1 {

    public ArrayList<ArrayList<String>> mBaseRefactorprova1(String[][] matrix) {
        var output = new ArrayList<ArrayList<String>>();
        var queueSingoletti = new ArrayList<ArrayList<String>>();
        var lambda = new String[matrix.length];
        var queue = new LinkedList<ArrayList<String>>();
        // UTILI DOPO PER MVARIANT
        // var listaTabu = new HashMap<ArrayList<ArrayList<String>>,
        // ArrayList<ArrayList<String>>>();
        // var queueDomains = new ArrayList<ArrayList<String>>();

        for (var j = 0; j < matrix[0].length; j++) {
            for (var i = 0; i < matrix.length; i++) {
                lambda[i] = matrix[i][j];
            }
            var lambdaArrayList = new ArrayList<>(Arrays.asList(lambda).subList(0, matrix.length));
            if (checkSingleton(lambdaArrayList).equals("ok")) {
                // ok ma non ultimo aggiunge alla coda
                if (j != matrix[0].length - 1) {
                    queue.add(lambdaArrayList);
                }
            } else if (checkSingleton(lambdaArrayList).equals("mhs")) {
                output.add(lambdaArrayList);
            }
            queueSingoletti.add(lambdaArrayList);

        }

        while (queue.peek() != null) {
            var vector = queue.peek();
            var max = getMaxVectorProjection(vector);
            for (var j = max + 1; j < queueSingoletti.size(); j++) {
                var e = queueSingoletti.get(j);
                var sigma = calculateVectorUnion(vector, e);
                var maxSigma = getMaxVectorProjection(sigma);
                if (checkUnion(sigma, vector, e).equals("ok") && maxSigma != matrix[0].length - 1) {
                    queue.add(sigma);
                } else if (checkUnion(sigma, vector, e).equals("mhs")) {
                    output.add(sigma);
                }
            }
            queue.poll();
        }

        return output;

    }

    private String checkSingleton(ArrayList<String> sigma) {
        if (!sigma.contains("0"))
            return "mhs";
        // check if contains all zeroes
        else if (sigma.stream().allMatch("0"::equals))
            return "ko";
        else
            return "ok";
    }

    private int getMaxVectorProjection(ArrayList<String> vector) {
        var max = 0;
        for (var v : vector) {
            if (!v.equals("0") && !v.equals("x")) {
                var digit = Integer.parseInt(v.replace("c", ""));
                if (digit > max) {
                    max = digit;
                }
            }
        }
        return max;
    }

    private ArrayList<String> calculateVectorUnion(ArrayList<String> vectorA, ArrayList<String> vectorB) {

        var sigmaLength = vectorA.size();
        var sigma = new ArrayList<String>();

        // considerando i tre vettori A, B e sigma della stessa dimensione sigmaLength
        // (fare un controllo?)
        for (int i = 0; i < sigmaLength; i++) {
            if ((vectorA.get(i).equals("0")) && (vectorB.get(i).equals("0"))) {
                sigma.add("0");
            } else if ((vectorA.get(i).equals("0")) && !(vectorB.get(i).equals("0"))) {
                sigma.add(vectorB.get(i));
            } else if (!(vectorA.get(i).equals("0")) && (vectorB.get(i).equals("0"))) {
                sigma.add(vectorA.get(i));
            } else {
                sigma.add("x");
            }
        }

        return sigma;

    }

    private String checkUnion(ArrayList<String> sigma, ArrayList<String> vector, ArrayList<String> e) {
        var domain = getColumnDomain(vector, e);
        if (!domain.contains(null) && !sigma.contains("0") && sigma.containsAll(domain)) {
            return "mhs";
        }
        if (!domain.contains(null) && sigma.contains("0") && sigma.containsAll(domain)) {
            return "ok";
        } else {
            return "ko";
        }
    }

    private ArrayList<String> getColumnDomain(ArrayList<String> vector, ArrayList<String> e) {
        var res = new ArrayList<String>();
        if (e.stream().allMatch("0"::equals)) {
            res.add(null);
        } else {
            for (String s : vector) {
                if (!s.equals("0") && !s.equals("x") && !res.contains(s)) {
                    res.add(s);
                }
            }
            for (String s : e) {
                if (!s.equals("0") && !s.equals("x") && !res.contains(s)) {
                    res.add(s);
                }
            }
        }
        return res;
    }

}
