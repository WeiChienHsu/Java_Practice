public class reverseSum {

    public static void main(String[] args) {
        System.out.println(countTheSum(168));
    }

    public static int countTheSum(int num) {
        int sum = 0;
        int tempNum = num;
        int count = 1;

        sum = tempNum + reverseInteger(tempNum);
        while (!checkHeadAndTail(sum)) {
            tempNum = sum;
            sum = tempNum + reverseInteger(tempNum);
            count++;
        }
        return sum;

    }


    public static boolean checkHeadAndTail(int num) {
        int lastDigit = num % 10;

        if(lastDigit % 2 != 0) return false;

        while (num >= 10) {
            num /= 10;
            if(num % 2 != 0) return false;
        }

        return num == lastDigit;
    }

    public static int reverseInteger(int num) {
        int newNum = 0;

        while(num >= 10) {
            newNum += num % 10;
            num /= 10;
            newNum *= 10;
        }

        newNum += num;
        return newNum;


    }
}
