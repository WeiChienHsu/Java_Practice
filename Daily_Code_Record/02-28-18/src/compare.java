import java.util.ArrayList;
import java.util.List;

public class compare {
    public static void main (String[] args) {
        int people = 5;
        int totalAmount = 100;
        List<Double> moneyList = randomGiveRedEvenlope(people, totalAmount);
        for(double num : moneyList) {
            System.out.println(num);
        }
    }

    private static List<Double> randomGiveRedEvenlope(int people, int total) {
        int n = people;
        double money = total * 1.00;

        List<Double> moneyList = new ArrayList<>();
        for(int i = 0; i < people - 1; i++) {
            double weight = Math.random();
            double curMoney = (double) Math.round(money * weight * 100) / 100  ;
            moneyList.add(curMoney);
            money -= curMoney;
            n--;
        }
        moneyList.add(money);
        return moneyList;
    }

}
