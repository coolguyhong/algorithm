package test.sorting;


public class Sort {

    public void selctionSort(int[] param) {
        for (int i = 0; i < param.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < param.length; j++) {
                if (param[min] > param[j]) {
                    min = j;
                }
            }
            swap(param, min, i);
        }
        for(int v : param) {
            System.out.printf("%d ", v);
        }
    }

    public void bubbleSort(int[] param) {
        int size = param.length;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (param[j] > param[j + 1]) {
                    swap(param, j + 1, j);
                }
            }
        }
        for(int v : param) {
            System.out.printf("%d ", v);
        }
    }

    public void insertionSort(int[] param) {
        for (int i = 1; i < param.length; i++) {
            int temp = param[i];
            int j = i;
            while (j > 0 && param[j-1] > temp) {
                param[j] = param[j-1];
                j--;
            }
            param[j] = temp;
        }

        for(int v : param) {
            System.out.printf("%d ", v);
        }
    }

    private void swap(int[] param, int idx1, int idx2) {
        int temp = param[idx2];
        param[idx2] = param[idx1];
        param[idx1] = temp;
    }
}
