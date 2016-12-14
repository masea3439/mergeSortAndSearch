/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortandsearch;

/**
 *
 * @author masea3439
 */
public class MergesortAndSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Create and print the original unsorted list
        System.out.print("List:");
        int[] list = new int[(int) (10)]; //Set the number of items to be sorted
        for (int i = 0; i < list.length; i++) {
            list[i] = (int) (Math.random() * 20 + 1); //Set the values of the numbers to be sorted
            System.out.print(" " + list[i]); //Print the original unsorted list
        }

        //Sort the unsorted list and print the sorted list
        long startTime = System.currentTimeMillis();
        list = separate(list);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("");
        System.out.print("Sorted:");
        for (int k = 0; k < list.length; k++) {
            System.out.print(" " + list[k]);
        }
        System.out.println("");
        System.out.println("Total time: " + totalTime + "ms");

        //THING TO FIND:
        int numWanted = 10;
        int find = search(list, numWanted);
        System.out.println("Num wanted: " + numWanted);
        System.out.println("Num wanted index: " + find);
    }

    /**
     * This method divides the list into a right and left half PRE: list POST:
     * returns a sorted section of the list (or the entire sorted list)
     */
    public static int[] separate(int list[]) {
        //Declare variables
        int length = list.length;
        int middle = (int) (list.length / 2); //Calculate the middle of the list
        int[] tempL = new int[middle]; //Create a smaller list for the right side
        int[] tempR = new int[list.length - middle]; //Create a smaller list for the left side
        int[] sorted = new int[length];

        //Assigns the left side of the list to the left list
        for (int i = 0; i < middle; i++) {
            tempL[i] = list[i];
        }

        //Sort the list if there are only two values in the list, if not devide them furthur
        if (tempL.length == 2) {
            tempL = merge(tempL);
        } else if (tempL.length != 1) {
            tempL = separate(tempL);
        }

        //Assigns the right side of the list to the right list
        for (int i = middle; i < list.length; i++) {
            tempR[i - middle] = list[i];
        }

        //Sort the list if there are only two values in the list, if not devide them furthur
        if (tempR.length == 2) {
            tempR = merge(tempR);
        } else {
            tempR = separate(tempR);
        }

        //Combine the left and right list using the mergesort method
        sorted = mergesort(tempR, tempL, length);

        //Return the sorted list
        return sorted;
    }

    /**
     * This method takes two values and arranges them from lowest to highest
     * PRE: temp POST: returns sorted temp
     */
    private static int[] merge(int[] temp) {
        int num1 = temp[0];
        int num2 = temp[1];

        //Sort the values from lowest to highest
        if (num1 <= num2) {
            temp[0] = num1;
            temp[1] = num2;
        } else {
            temp[0] = num2;
            temp[1] = num1;
        }

        //Return the sorted values
        return temp;
    }

    /**
     * This method combines the left and right side of the list into a single
     * sorted list PRE: tempR, tempL, length POST: returns a sorted combined
     * list of tempR and tempL
     */
    private static int[] mergesort(int[] tempR, int[] tempL, int length) {

        //Declare variables
        int[] sorted = new int[length];
        int r = 0;
        int l = 0;
        String done = "false";

        //Combine the right and left lists into a single sorted list
        for (int k = 0; k < length; k++) {

            //Fill the rest of the combined list with all remaining values of the left list if the right list is empty
            if ("right".equals(done)) {
                sorted[k] = tempL[l];
                l++;
            }

            //Fill the rest of the combined list with all remaining values of the right list if the left list is empty
            if ("left".equals(done)) {
                sorted[k] = tempR[r];
                r++;
            }

            //Fill the combined list with the lowest value between the leftmost position of the left and right lists until one of the lists are empty
            if ("false".equals(done)) {

                if (tempR[r] <= tempL[l]) {
                    sorted[k] = tempR[r];
                    if (r != tempR.length - 1) {
                        r++;
                    } else {
                        done = "right";
                    }
                } else {
                    sorted[k] = tempL[l];
                    if (l != tempL.length - 1) {
                        l++;
                    } else {
                        done = "left";
                    }
                }
            }
        }

        //Return the sorted combined list
        return sorted;
    }

    private static int search(int[] list, int numWanted) {
        int index = 0;
        int middle = (int) (list.length / 2);
        int[] tempL = new int[middle];
        int[] tempR = new int[list.length - middle];
        boolean doesItExist = false;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == numWanted) {
                doesItExist = true;
            }
        }
        if (doesItExist == true) {
            if (list[middle] > numWanted) {
                for (int i = 0; i < middle; i++) {
                    tempL[i] = list[i];
                }
                index = search(tempL, numWanted);
            } else if (list[middle] < numWanted) {
                for (int i = middle; i < list.length; i++) {
                    tempR[i - middle] = list[i];
                }
                index = search(tempR, numWanted) + list.length / 2;
            } else if (list[middle] == numWanted) {
                index = middle;
            }
        } else {
            System.out.println("NUMBER NOT FOUND");
            System.exit(0);
        }

        return (index);
    }
}
