import java.awt.*;
import java.util.EmptyStackException;
import java.util.Random;

/**
 * Created by Весёлый студент on 26.09.2015.
 */
public class Dowloading implements Runnable {

    Stock source;
    Train drain;
    Coach tempCoach;

    public Dowloading(Stock msource, Train mdrain){
        this.source = msource;
        this.drain = mdrain;
    }

    @Override
    public void run() {
        try {
            while(true){
                tempCoach = this.drain.getEmptyCoach(source.getType());
                while(tempCoach.container.offer(source.container.take())) {
                    Thread.sleep(new Random().nextInt(20));
                    tempCoach.UpdateBar();
                    this.source.UpdateBar();
                };
            }
        }
        catch (InterruptedException ie){
            System.out.println(ie.getStackTrace().toString());
        }
        catch (EmptyStackException ese){
            //просто завершгаем поток
        }
        finally {
            if (source.getType() == ItemType.DRY) Dispatcher.trainsDryDowloadingStopped = true;
            if (source.getType() == ItemType.FLUID) Dispatcher.trainsFluidDowloadingStopped = true;
            if (source.getType() == ItemType.PERISHABLE) Dispatcher.trainsPerishableDowloadingStopped = true;
        }
    }
}
