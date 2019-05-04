import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Весёлый студент on 17.09.2015.
 */
public class Stock {
    private JProgressBar occupationBar;
    private Dimension dimension;
    private Point location;

    private ItemType type;
    private static final int capacity = 240;
    public ArrayBlockingQueue<Item> container = new ArrayBlockingQueue<Item>(capacity);

    public Stock(ItemType mtype, Point mlocation){
        this.type = mtype;
        int r = new Random().nextInt(capacity);
        for (int i = 0; i < r; i++)
            this.container.offer(new Item(this.type));
        this.dimension = new Dimension(235,200);
        this.location = mlocation;

        this.occupationBar = new JProgressBar(SwingConstants.VERTICAL);
        this.occupationBar.setBounds(location.x, location.y, dimension.width, dimension.height);
        this.occupationBar.setMinimum(0);
        this.occupationBar.setMaximum(Stock.capacity);
        this.occupationBar.setValue(Stock.capacity - this.container.remainingCapacity());
        this.occupationBar.setStringPainted(true);
        this.occupationBar.setBackground(new Color(255,255,255));
        if (this.type == ItemType.DRY) {
            this.occupationBar.setForeground(new Color(0, 50, 100));
            this.occupationBar.setString("Сыпучие");
        }
        else
            if (this.type == ItemType.FLUID) {
                this.occupationBar.setForeground(new Color(155,30,0));
                this.occupationBar.setString("Горючие");
            }
            else {
                this.occupationBar.setForeground(new Color(100,20,100));
                this.occupationBar.setString("Скоропортящиеся");
            }
    }

    public Component getOccupationBar(){
        return (Component)this.occupationBar;
    }

    public static int getCapacity() {return capacity;}
    public ItemType getType(){return this.type;}

    synchronized public void UpdateBar(){
        this.occupationBar.setValue(capacity - this.container.remainingCapacity());
        //this.occupationBar.updateUI();
    }
}
