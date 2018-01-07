import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sort the students by their scores and if its same, sort by Age");
        Student std1 = new Student(90,21,"Kevin");
        Student std2 = new Student(90,19,"Jay");
        Student std3 = new Student(97,22,"Albert");
        Student std4 = new Student(88,39,"Alex");
        Student std5 = new Student(88,11,"Eric");

        List<Student> lists = new ArrayList<>();
        lists.add(std1);
        lists.add(std2);
        lists.add(std3);
        lists.add(std4);
        lists.add(std5);

        Collections.sort(lists, new StuComparator());
        for(Student list : lists) {
            System.out.println(list.getScore()+ " is age " + list.getAge() + " Name: " + list.getName());
        }
    }
}

class Student {
    private int score;
    private int age;
    private String name;

    public Student(int score, int age, String name) {
        this.score = score;
        this.age = age;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class StuComparator implements Comparator<Student>{
    @Override
    public int compare(Student std1, Student std2) {
        int std1Score = std1.getScore();
        int std2Score = std2.getScore();
        int std1Age = std1.getAge();
        int std2Age = std2.getAge();

        if(std1Score > std2Score) {
            return -1;
        } else if (std1Score < std2Score) {
            return 1;
        } else {
            if(std1Age > std2Age) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
