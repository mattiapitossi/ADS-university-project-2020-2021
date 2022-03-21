package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MVariant1 {

    public ArrayList<ArrayList<String>> mVariant1(String[][] matrix) {

        var output = new ArrayList<ArrayList<String>>();
        var queueSingoletti = new ArrayList<ArrayList<String>>();
        var lambda = new String[matrix.length];
        var queue = new LinkedList<ArrayList<String>>();
        // UTILI DOPO PER MVARIANT
        var listaTabu = new HashMap<ArrayList<ArrayList<String>>, ArrayList<ArrayList<String>>>();
        var queueDomains = new ArrayList<ArrayList<ArrayList<String>>>();

        for (var j = 0; j < matrix[0].length; j++) {
            for (var i = 0; i < matrix.length; i++) {
                lambda[i] = matrix[i][j];
            }
            var lambdaArrayList = new ArrayList<>(Arrays.asList(lambda).subList(0, matrix.length));
            queueDomains.add(getColumnDomainSingleton(lambdaArrayList));
            if (checkSingleton(lambdaArrayList).equals("ok")) {
                // ok ma non ultimo aggiunge alla coda
                if (j != matrix[0].length - 1) {
                    queue.add(lambdaArrayList);
                }
            } else if (checkSingleton(lambdaArrayList).equals("mhs")) {
                output.add(lambdaArrayList);
                for (var k = 0; k < queueDomains.size(); k++) {
                    listaTabu.put(queueDomains.get(k), getColumnDomainSingleton(lambdaArrayList));
                }
            }
            queueSingoletti.add(lambdaArrayList);
        }

        while (queue.peek() != null) {
            var vector = queue.peek();
            var max = getMaxVectorProjection(vector);
            for (var j = max + 1; j < queueSingoletti.size(); j++) {
                var e = queueSingoletti.get(j);
                var sigma = calculateVectorUnion(vector, e, listaTabu);
                var maxSigma = getMaxVectorProjection(sigma);
                if (checkUnion(sigma, vector, e).equals("ok") && maxSigma != matrix[0].length - 1) {
                    queue.add(sigma);
                    // qua sigma sarebbe come gamma nel metodo, è sbagliato il nome
                    listaTabu.put(getColumnDomainSingleton(sigma), inheritTabu(vector, sigma, listaTabu));
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

    private ArrayList<String> calculateVectorUnion(ArrayList<String> vectorA, ArrayList<String> vectorB,
            HashMap<ArrayList<ArrayList<String>>, ArrayList<ArrayList<String>>> listaTabu) {

        var sigmaLength = vectorA.size();
        var sigma = new ArrayList<String>();

        // considerando i tre vettori A, B e sigma della stessa dimensione sigmaLength
        // (fare un controllo?)
        if (!isTabu(vectorA, vectorB, listaTabu)) {
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
        }

        return sigma;

    }

    private String checkUnion(ArrayList<String> sigma, ArrayList<String> vector, ArrayList<String> e) {
        var domain = getColumnDomain(vector, e);
        if (!domain.contains(null) && !sigma.equals("0") && sigma.equals(domain)) {
            return "mhs";
        }
        if (!domain.contains(null) && sigma.equals("0") && sigma.equals(domain)) {
            return "ok";
        } else {
            return "ko";
        }
    }

    private List<ArrayList<String>> getColumnDomain(ArrayList<String> vector, ArrayList<String> e) {
        var res = new ArrayList<ArrayList<String>>();
        if (e.stream().allMatch("0"::equals)) {
            res.add(null);
        } else {
            for (String s : vector) {
                if (!s.equals("0") && !s.equals("x") && !res.equals(s)) {
                    var s1 = new ArrayList<>(Arrays.asList(s));
                    res.add(s1);
                }
            }
            for (String s : e) {
                if (!s.equals("0") && !s.equals("x") && !res.equals(s)) {
                    var s1 = new ArrayList<>(Arrays.asList(s));
                    res.add(s1);
                }
            }
        }
        return res;
    }

    private ArrayList<ArrayList<String>> getColumnDomainSingleton(ArrayList<String> vector) {
        var res = new ArrayList<ArrayList<String>>();
        for (String s : vector) {
            if (!s.equals("0") && !res.equals(s)) {
                var s1 = new ArrayList<>(Arrays.asList(s));
                res.add(s1);
            }
        }
        return res;
    }

    public boolean isTabu(ArrayList<String> vectorA, ArrayList<String> vectorB,
            HashMap<ArrayList<ArrayList<String>>, ArrayList<ArrayList<String>>> listaTabu) {
        var listaTabuVectorA = listaTabu.get(getColumnDomainSingleton(vectorA)).stream().toList();
        return listaTabuVectorA.equals(vectorB);
    }

    public ArrayList<ArrayList<String>> inheritTabu(ArrayList<String> lambda, ArrayList<String> gamma,
            HashMap<ArrayList<ArrayList<String>>, ArrayList<ArrayList<String>>> listaTabu) {

        var listaTabuLambda = listaTabu.get(lambda);

        var maxGamma = gamma.size() - 1;

        var copyListaTabuLambda = new ArrayList<>(listaTabuLambda);

        var listaTabuGamma = new ArrayList<>(copyListaTabuLambda.stream() //
                .filter(s -> compareStrings(s.get(0), gamma.get(maxGamma))).toList());

        listaTabuGamma.stream().forEach(s -> {
            if (s.get(0).equals(gamma.get(maxGamma))) {
                s.remove(0);
            }
        });

        for (var elementoListaTabuGamma : listaTabuGamma) {
            for (var s : elementoListaTabuGamma) {
                System.out.printf("%s ", s);
            }
            System.out.println("\n");
        }

        return listaTabuGamma;
    }

    public static boolean compareStrings(String s, String gamma) {
        s = s.replaceAll("[^\\d.]", "");
        gamma = gamma.replaceAll("[^\\d.]", "");
        return Integer.parseInt(s) >= Integer.parseInt(gamma);
    }

}
