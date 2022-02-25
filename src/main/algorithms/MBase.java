package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class MBase {
    public ArrayList<String[]> mbase(String[][] matrix) {
        // var queue = new ArrayList<String[]>(); // !never queried
        var output = new ArrayList<String[]>();
        var arrayOfColumns = new ArrayList<String[]>();
        var lambda = new String[matrix.length];

        // arrayOfColumns (la coda) contiene tutti i singoletti iniziali
        for (var j = 0; j < matrix[0].length - 1; j++) {
            for (var i = 0; i < matrix.length; i++) {
                lambda[i] = matrix[i][j];
            }
            var lambdaArrayList = new ArrayList<>(Arrays.asList(lambda).subList(0, matrix.length));
            var lambdaArray = lambdaArrayList.toArray(new String[0]);
            if (checkSingleton(lambdaArray).equals("ok")) {
                arrayOfColumns.add(lambdaArray);
            } else if (checkSingleton(lambdaArray).equals("mhs")) {
                output.add(lambdaArray);
            }
        }

        // al posto di definire la variabile queue, uso arrayOfColumns come coda.
        for (var i = 0; i < arrayOfColumns.size(); i++) {
            var vector = arrayOfColumns.get(i);
            for (var j = 1; j < arrayOfColumns.size(); j++) {
                var e = arrayOfColumns.get(j);
                var sigma = calculateVectorUnion(vector, e);
                if (checkUnion(sigma, vector, e).equals("ok")) {
                    arrayOfColumns.add(sigma);
                } else if (checkUnion(sigma, vector, e).equals("mhs")) {
                    output.add(sigma);
                }
            }
        }

        return output;

    }

    private String[] calculateVectorUnion(String[] vectorA, String[] vectorB) {

        var sigmaLength = vectorA.length;
        var sigma = new String[sigmaLength];

        // considerando i tre vettori A, B e sigma della stessa dimensione sigmaLength
        // (fare un controllo?)
        for (int i = 0; i < sigmaLength; i++) {
            if (Objects.equals(vectorA[i], "0") && Objects.equals(vectorB[i], "0")) {
                sigma[i] = "0";
            } else if (Objects.equals(vectorA[i], "0") && !Objects.equals(vectorB[i], "0")) {
                sigma[i] = vectorB[i];
            } else if (!Objects.equals(vectorA[i], "0") && Objects.equals(vectorB[i], "0")) {
                sigma[i] = vectorA[i];
            } else {
                sigma[i] = "x";
            }
        }

        return sigma;

    }

    private String checkSingleton(String[] sigma) {
        var targetSet = new HashSet<>(Arrays.asList(sigma));
        if (!targetSet.contains("0"))
            return "mhs";
            // check if contains all zeroes
        else if (targetSet.contains("0") && targetSet.size() <= 1)
            return "ko";
        else
            return "ok";
    }

    private String checkUnion(String[] sigma, String[] vector, String[] e) {
        var targetSet = new HashSet<>(Arrays.asList(sigma));
        var domain1 = getColumnDomain(vector);
        var domain2 = getColumnDomain(e);
        if (!targetSet.contains("0") && targetSet.contains(domain1) //
                && targetSet.contains(domain2) && (domain1!=null) && (domain2!=null))  {
            return "mhs";
        }
        if (targetSet.contains("0") && (targetSet.contains(domain1) //
                || targetSet.contains(domain2)) && (domain1!=null) && (domain2!=null)) {
            return "ok";
        } else {
            return "ko";
        }
    }

    private String getColumnDomain(String[] vector) {
        for (String s : vector) {
            if (!s.equals("0")) {
                return s;
            }
        }
        // should never reach this
        return null;
    }

}
