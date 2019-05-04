//import javafx.geometry.Bounds;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Весёлый студент on 18.09.2015.
 */
public class Coach {

    private JProgressBar occupationBar;
    private Dimension dimension;
    private Point location;

    private static final int capacity = 120;
    private ItemType type;
    public ArrayBlockingQueue<Item> container = new ArrayBlockingQueue<Item>(Coach.capacity);

    public Coach(Point mlocation){
        switch (new Random().nextInt(3))
        {
            case 0 : this.type = ItemType.DRY; break;
            case 1 : this.type = ItemType.FLUID; break;
            case 2 : this.type = ItemType.PERISHABLE; break;
            default: this.type = ItemType.DRY; break;
        }
        this.dimension = new Dimension(30,15);
        this.location = mlocation;

        this.occupationBar = new JProgressBar(SwingConstants.HORIZONTAL);
        this.occupationBar.setSize(this.dimension);
        this.occupationBar.setLocation(this.location);
        this.occupationBar.setMinimum(0);
        this.occupationBar.setMaximum(Coach.capacity);
        this.occupationBar.setValue(0);
        this.occupationBar.setStringPainted(false);
        this.occupationBar.setBackground(new Color(255, 255, 255));
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

    synchronized public void UpdateBar(){
        this.occupationBar.setValue(Coach.capacity - this.container.remainingCapacity());
        //this.occupationBar.updateUI();
    }

    public static int getCapacity() {return capacity;}
    public ItemType getType(){return this.type;}
    public int getFree(){return this.container.remainingCapacity();}
    public Point getLocation(){return this.location;}
    public JProgressBar getOccupationBar(){return occupationBar;}
}
