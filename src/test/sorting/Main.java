package test.sorting;

public class Main {

    public static void main(String[] args) {
//        System.out.println("=====[선택정렬]=====");
//        int[] a = {68, 9, 32, 2, 14, 7, 31, 26};
        Sort sort = new Sort();

//        System.out.printf("\n정렬할 원소 :");
//        for (int v : a) {
//            System.out.printf("%d ", v);
//        }
//        System.out.println();
//        sort.selctionSort(a);

        System.out.println("=====[버블정렬]=====");
        int[] b = {68, 9, 32, 2, 14, 7, 31, 26};
        System.out.printf("\n정렬할 원소 :");
        for(int v : b) {
            System.out.printf("%3d ", v);
        }
        System.out.println();
        sort.bubbleSort(b);
//
//        System.out.println("=====[삽입정렬]=====");
//        int[] c = {68, 9, 32, 2, 14, 7, 31, 26};
//        System.out.printf("\n정렬할 원소 :");
//        for(int v : c) {
//            System.out.printf("%3d ", v);
//        }
//        System.out.println();
//        sort.insertionSort(c);
    }
}
