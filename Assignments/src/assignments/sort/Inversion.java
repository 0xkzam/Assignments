
package assignments.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kasun Amarasena
 */
public class Inversion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        File file = new File("E:\\&_Algorithms Analysis & Design\\Assignements/IntegerArray.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Integer.parseInt(text));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        Integer[] a = list.toArray(new Integer[0]);
        System.out.println("xxx: "+a.length);
        
        System.out.println("yyy: "+new Inversion().invCount(a));
    }

    int merge(Integer[] arr, Integer[] left, Integer[] right) {
        int i = 0, j = 0, count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i + j] = right[j];
                j++;
            } else if (j == right.length) {
                arr[i + j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                count += left.length - i;
                j++;
            }
        }
        return count;
    }

    long invCount(Integer[] arr) {
        if (arr.length < 2) {
            return 0;
        }

        int m = (arr.length + 1) / 2;
        Integer left[] = Arrays.copyOfRange(arr, 0, m);
        Integer right[] = Arrays.copyOfRange(arr, m, arr.length);

        return invCount(left) + invCount(right) + merge(arr, left, right);
    }

}
