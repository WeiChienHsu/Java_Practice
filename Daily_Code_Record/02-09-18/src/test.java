import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class test {
    public static void main(String[] args){
        List<String> nameList = new ArrayList<>();
        nameList.add("Kevin");
        nameList.add("John");
        nameList.add("Peter");
        nameList.add("Steve");
        while(nameList.size() > 0) {
            int index = (int)(Math.random() * (nameList.size()));
            System.out.println(nameList.get(index));
            nameList.remove(index);
        }
        String s = String.format("I have %,d bugs", 10000000);
        System.out.println(s);

        Calendar ca1 = Calendar.getInstance();
        int i = 0;
        while(i++ < 100){
            System.out.println(ca1.getTime());
        }






    }

}
