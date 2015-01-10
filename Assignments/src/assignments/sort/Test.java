/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package assignments.sort;

import static assignments.sort.MergeSort.isSorted;

import java.util.Random;

/**
 *
 * @author Kasun Amarasena
 */
public class Test {

    /**
     * Test using an array of elements of 1000 randomly generated Integers
     */
    public void test() {
        Integer[] arr = new Integer[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            arr[i] = random.nextInt(100000);
        }

        System.out.println("before issorted: " + isSorted(arr));
        MergeSort.sort(arr);
        for (Integer arr1 : arr) {
            System.out.print(" " + arr1);
        }
        System.out.println("");
        System.out.println("after issorted: " + isSorted(arr));
    }
    
    public static void main(String[] arg){
        new Test().test();  
    }
}
