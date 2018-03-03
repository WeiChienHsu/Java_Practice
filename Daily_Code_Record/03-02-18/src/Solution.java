import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        TwoSum num = new TwoSum();
        num.add(1);
        num.add(3);
        num.add(5);
        num.add(7);
        System.out.println(num.getNumList());
        System.out.println(num.haveSum(10));
    }

}

class TwoSum{
    private List<Integer> numList = null;
    private Map<Integer, Integer> freq = null;

    public TwoSum(){
        numList = new ArrayList<>();
        freq = new HashMap<>();
    }

    public void add(int num){
        numList.add(num);
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    public List<Integer> getNumList() {
        return numList;
    }

    public boolean haveSum(int num){
        for(int i = 0; i < numList.size(); i++) {
            if(freq.containsKey(num - numList.get(i))) {
                return true;
            }
            freq.put(numList.get(i), freq.getOrDefault(numList.get(i), 0) + 1 );
        }

        return false;
    }

}

