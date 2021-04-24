import java.util.Arrays;

public class SearchTest3 {
    public static boolean solution(int[] array1, int n) {
        Arrays.sort(array1);
        int[] array2 = new int[array1.length];

        System.arraycopy(array1, 0, array2, 0, array1.length);
        for (int i = 0; i < array2.length; i++) {
            array2[i] = n - array2[i];
        }

        int array1Index = array1.length - 1;
        int array2Index = 0;

        while (array1Index != array2Index) {
            int array1Value = array1[array1Index];
            int array2Value = array2[array2Index];

            if (array1Value == n / 2 || array2Value == n / 2) {
                return false;
            }

            if (array1Value == array2Value)
                return true;

            if (array1Value > array2Value) {
                array1Index--;
            } else
                array2Index++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 3, 9, 13}, 8));

    }
}
