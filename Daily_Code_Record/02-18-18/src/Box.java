import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Box implements Serializable{
    private int width;
    private int height;
    transient public Duck duck = new Duck();

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public static void main(String[] args) {
        Box myBox = new Box();
        myBox.setHeight(30);
        myBox.setWidth(20);

        try {
            FileOutputStream fs = new FileOutputStream("foo.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(myBox);
            os.close();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
