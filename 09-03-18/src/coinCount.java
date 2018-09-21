public class coinCount {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 9};
        int number = 20;
        int count = countMinCoins1(number);
        System.out.println(count);
    }

    public static int countMinCoins1(int number) {
        int count = 0;
        if(number > 9) {
            count += number / 9;
            number %= 9;
        }

        if(number > 3) {
            count += number / 3;
            number %= 3;
        }

        count += number;

        return count;
    }

    public static int countMinCoins(int[] coins, int number) {
        int count = 0;
        sort(coins);
        for(int coin : coins) {
            if(number < coin) continue;
            System.out.println("Coin = " + coin + " : " + number / coin);
            count += (number / coin);
            number %= coin;

        }
        return count;
    }

    public static void sort(int[] coins) {
        for(int i = 0; i < coins.length - 1; i++) {
            for(int j = i + 1; j < coins.length; j++) {
                if(coins[i] < coins[j]) {
                    int temp = coins[i];
                    coins[i] = coins[j];
                    coins[j] = temp;
                }
            }
        }
    }

}
