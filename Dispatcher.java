import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Весёлый студент on 26.09.2015.
 */
public class Dispatcher implements Runnable {
    public static boolean dryTrucksUploadingStopped = true;
    public static boolean fluidTrucksUploadingStopped = true;
    public static boolean perishableTrucksUploadingStopped = true;

    public static boolean trainsDryDowloadingStopped = true;
    public static boolean trainsFluidDowloadingStopped = true;
    public static boolean trainsPerishableDowloadingStopped = true;

    private static Truck tempDryTruck = null;
    private static Truck tempFluidTruck = null;
    private static Truck tempPerishableTruck = null;
    private static Train tempTrain = null;

    private static Stock dryStock;
    private static Stock fluidStock;
    private static Stock perishableStock;

    @Override
    public void run() {
        JFrame f = MainClass.getFrame();
        Stock dryStock = new Stock(ItemType.DRY,new Point(50, 70));
        f.add(dryStock.getOccupationBar());
        Stock fluidStock = new Stock(ItemType.FLUID,new Point(285, 70));
        f.add(fluidStock.getOccupationBar());
        Stock perishableStock = new Stock(ItemType.PERISHABLE,new Point(520, 70));
        f.add(perishableStock.getOccupationBar());

        while (MainClass.trains.peek() != null) {
            if (dryTrucksUploadingStopped && (MainClass.dryTrucks.peek() != null)) {
                dryTrucksUploadingStopped = false;
                if (tempDryTruck != null) {
                    tempDryTruck.getOccupationBar().setVisible(false);
                    f.remove(tempDryTruck.getOccupationBar());
                }

                tempDryTruck = MainClass.dryTrucks.poll();
                f.add(tempDryTruck.getOccupationBar());
                new Thread(new Uploading(tempDryTruck,dryStock)).start();
            }
            if (fluidTrucksUploadingStopped && (MainClass.fluidTrucks.peek() != null)) {
                fluidTrucksUploadingStopped = false;
                if (tempFluidTruck != null) {
                    tempFluidTruck.getOccupationBar().setVisible(false);
                    f.remove(tempFluidTruck.getOccupationBar());
                }

                tempFluidTruck = MainClass.fluidTrucks.poll();
                f.add(tempFluidTruck.getOccupationBar());
                new Thread(new Uploading(tempFluidTruck,fluidStock)).start();
            }
            if (perishableTrucksUploadingStopped && (MainClass.perishableTrucks.peek() != null)) {
                perishableTrucksUploadingStopped = false;
                if (tempPerishableTruck != null) {
                    tempPerishableTruck.getOccupationBar().setVisible(false);
                    f.remove(tempPerishableTruck.getOccupationBar());
                }

                tempPerishableTruck = MainClass.perishableTrucks.poll();
                f.add(tempPerishableTruck.getOccupationBar());
                new Thread(new Uploading(tempPerishableTruck,perishableStock)).start();
            }

            if (trainsDryDowloadingStopped && trainsFluidDowloadingStopped
                    && trainsPerishableDowloadingStopped && (MainClass.trains.peek() != null)) {
                trainsDryDowloadingStopped = false;
                trainsFluidDowloadingStopped = false;
                trainsPerishableDowloadingStopped = false;
                if (tempTrain != null)
                    for (int i = 0; i < tempTrain.getLength(); i++) {
                        tempTrain.getCoach(i).getOccupationBar().setVisible(false);
                        f.remove(tempTrain.getCoach(i).getOccupationBar());
                    }

                tempTrain = MainClass.trains.poll();
                for (int i = 0; i < tempTrain.getLength(); i++) {
                    f.add(tempTrain.getCoach(i).getOccupationBar());
                    tempTrain.getCoach(i).getOccupationBar().setVisible(true);
                    tempTrain.getCoach(i).getOccupationBar().setValue(1);
                    tempTrain.getCoach(i).getOccupationBar().setValue(0);
                    tempTrain.getCoach(i).getOccupationBar().updateUI();
                }
                new Thread(new Dowloading(dryStock, tempTrain)).start();
                new Thread(new Dowloading(fluidStock, tempTrain)).start();
                new Thread(new Dowloading(perishableStock, tempTrain)).start();
            }
            try {
                Thread.sleep(100 + (new Random().nextInt(600)));
            }
            catch (InterruptedException e){
                //просто уйдем тогда
            }
        }
    }
}
