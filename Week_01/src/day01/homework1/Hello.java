package day01.homework1;

public class Hello {
    public static void main(String[] args) {
        int base = 5;
        int[] nums = {1,3,5,7,9};
        int sum = 0;
        for (int num: nums) {
            if (num > base) sum += num;
        }
        System.out.println("大于5的总和："+sum);
    }
}
