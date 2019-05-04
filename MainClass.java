import java.awt.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.*;

/**
 * Created by Весёлый студент on 17.09.2015.
 */
public class MainClass {

    public static Queue<Truck> dryTrucks;
    public static Queue<Truck> fluidTrucks;
    public static Queue<Truck> perishableTrucks;
    public static Queue<Train> trains;
    private static JFrame f = new JFrame("Зенин Кирилл");



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                    createAndShowGUI();
                }
        });
    }

    private static void createAndShowGUI() {

        int temp;

        dryTrucks = new ArrayBlockingQueue<Truck>(100);
        fluidTrucks = new ArrayBlockingQueue<Truck>(100);
        perishableTrucks = new ArrayBlockingQueue<Truck>(100);
        trains = new ArrayBlockingQueue<Train>(100);

        f.setLayout(null);
        f.setMinimumSize(new Dimension(805, 340));
        f.setMaximumSize(new Dimension(805, 340));
        f.setPreferredSize(f.getMinimumSize());
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        temp = 5 + (new Random().nextInt(10));
        for (int i = 0; i < temp;i++)
            trains.offer(new Train(2 + (new Random().nextInt(18))));

        temp = 40 + (new Random().nextInt(20));
        for (int i = 0; i < temp;i++)
            dryTrucks.offer(new Truck(ItemType.DRY));

        temp = 40 + (new Random().nextInt(20));
        for (int i = 0; i < temp;i++)
            fluidTrucks.offer(new Truck(ItemType.FLUID));

        temp = 40 + (new Random().nextInt(20));
        for (int i = 0; i < temp;i++)
            perishableTrucks.offer(new Truck(ItemType.PERISHABLE));

        new Thread(new Dispatcher()).start();
    }

    public static JFrame getFrame(){
        return f;
    }
}
