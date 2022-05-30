package algorithms;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MBase {
    public ArrayList<ArrayList<String>> mbase(String[][] matrix, String fileName, Long timeOut) {
        var executor = Executors.newSingleThreadExecutor();
        var output = new ArrayList<String[]>();
        var queueSingoletti = new LinkedList<String[]>();
        var lambda = new String[matrix.length];
        var queue = new LinkedList<String[]>();

        var future = executor.submit(() -> {

            // arrayOfColumns (la coda) contiene tutti i singoletti iniziali
            //add comment
            for (var j = 0; j < matrix[0].length; j++) {
                for (var i = 0; i < matrix.length; i++) {
                    lambda[i] = matrix[i][j];
                }
                var lambdaArrayList = new ArrayList<>(Arrays.asList(lambda).subList(0, matrix.length));
                var lambdaArray = lambdaArrayList.toArray(new String[0]);
                if (checkSingleton(lambdaArray).equals("ok")) {
                    // ok ma non ultimo aggiunge alla coda
                    if (j != matrix[0].length - 1) {
                        queue.add(lambdaArray);
                    }
                } else if (checkSingleton(lambdaArray).equals("mhs")) {
                    output.add(lambdaArray);
                }
                queueSingoletti.add(lambdaArray);
            }

            while (queue.peek() != null) {
                var vector = queue.peek();
                var max = getVectorColumn(vector, queueSingoletti);
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
            
        });

        executor.shutdown();
        try {
            future.get(timeOut, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.out.println("Timeout expired for " + fileName);
            return getMhsDomain(output);
        }

        return getMhsDomain(output);
    }

    private int getVectorColumn(String[] vector, LinkedList<String[]> queueSingoletti) {
        return queueSingoletti.indexOf(vector);
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
        if (!targetSet.contains("0")) return "mhs";
            // check if contains all zeroes
        else if (targetSet.contains("0") && targetSet.size() <= 1) return "ko";
        else return "ok";
    }

    private String checkUnion(String[] sigma, String[] vector, String[] e) {
        var targetSet = new HashSet<>(Arrays.asList(sigma));
        var domain = getColumnDomain(vector, e);
        if (!domain.contains(null) && !targetSet.contains("0") && targetSet.containsAll(domain)) {
            return "mhs";
        }
        if (!domain.contains(null) && targetSet.contains("0") && targetSet.containsAll(domain)) {
            return "ok";
        } else {
            return "ko";
        }
    }

    private ArrayList<String> getColumnDomain(String[] vector, String[] e) {
        var res = new ArrayList<String>();
        if (Arrays.stream(e).allMatch("0"::equals)) {
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

    private ArrayList<ArrayList<String>> getMhsDomain(ArrayList<String[]> output) {
        var mhs = new ArrayList<ArrayList<String>>();

        for (var i = 0; i < output.size(); i++) {
            var mhsDomain = output.get(i);
            mhs.add(new ArrayList<>());
            for (String s : mhsDomain) {
                if (s.startsWith("c") && !mhs.get(i).contains(s)) {
                    mhs.get(i).add(s);
                }
            }
        }
        return mhs;
    }

    private int getMaxVectorProjection(String[] vector) {
        var max = 0;
        for (String v : vector) {
            if (!v.equals("0") && !v.equals("x")) {
                var digit = Integer.parseInt(v.replace("c", ""));
                if (digit > max) {
                    max = digit;
                }
            }
        }
        return max;
    }

}
