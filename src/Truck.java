import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Весёлый студент on 17.09.2015.
 */
public class Truck {

    private JProgressBar occupationBar;
    private Dimension dimension;
    private Point location;

    private ItemType type;
    private static final int capacity = 120;
    private static int drySerialNumber = 1;
    private static int fluidSerialNumber = 1;
    private static int perishableSerialNumber = 1;

    public ArrayBlockingQueue<Item> container = new ArrayBlockingQueue<Item>(Truck.capacity);

    public Truck(ItemType mtype){

        this.occupationBar = new JProgressBar(SwingConstants.HORIZONTAL);

        this.type = mtype;
        this.dimension = new Dimension(30,15);

        if (this.type == ItemType.DRY) {
            this.occupationBar.setForeground(new Color(0, 50, 100));
            this.location = new Point(50,275);
            this.occupationBar.setString(String.valueOf(Truck.drySerialNumber));
            Truck.drySerialNumber++;
        }
        else
        if (this.type == ItemType.FLUID) {
            this.occupationBar.setForeground(new Color(155,30,0));
            this.location = new Point(285,275);
            this.occupationBar.setString(String.valueOf(Truck.fluidSerialNumber));
            Truck.fluidSerialNumber++;
        }
        else {
            this.occupationBar.setForeground(new Color(100,20,100));
            this.location = new Point(520,275);
            this.occupationBar.setString(String.valueOf(Truck.perishableSerialNumber));
            Truck.perishableSerialNumber++;
        }


        while(container.offer(new Item(this.type)));
        this.occupationBar.setLocation(this.location);
        this.occupationBar.setSize(this.dimension);
        //this.occupationBar.setBounds(location.x, location.y, dimension.width, dimension.height);
        this.occupationBar.setMinimum(0);
        this.occupationBar.setMaximum(Truck.capacity);
        this.occupationBar.setValue(120);
        this.occupationBar.setStringPainted(true);
        this.occupationBar.setBorderPainted(true);
        this.occupationBar.setBackground(new Color(255, 255, 255));

    }

    synchronized public void UpdateBar() {
        this.occupationBar.setValue(Truck.capacity - this.container.remainingCapacity());
        //this.occupationBar.updateUI();
    }

    public static int getCapacity() {return capacity;}
    public ItemType getType(){return this.type;}
    public Point getLocation(){return this.location;}
    public JProgressBar getOccupationBar() { return this.occupationBar;}

}
