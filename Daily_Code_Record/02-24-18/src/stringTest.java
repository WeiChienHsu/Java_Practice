public class stringTest {

    public static void main(String[] args) {
        String s = "a#b#c#";
        String[] strings = s.split("#");
        StringBuilder sb = new StringBuilder();
        for(String string : strings) {
            sb.append(string);
        }

        System.out.println(sb.toString());



    }
}
