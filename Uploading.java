import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Весёлый студент on 17.09.2015.
 */
public class Uploading implements Runnable {

    Truck source;
    Stock drain;

    public Uploading(Truck msource, Stock mdrain){
        this.source = msource;
        this.drain = mdrain;
    }

    @Override
    public void run() {
        if (this.drain.getType() == this.source.getType()) {
            try {
                while (this.source.container.peek() != null) {
                    this.drain.container.put(this.source.container.poll());
                    Thread.sleep(new Random().nextInt(20));
                    this.drain.UpdateBar();
                    this.source.UpdateBar();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace().toString());
            }
            finally {
                if (source.getType() == ItemType.DRY) Dispatcher.dryTrucksUploadingStopped = true;
                if (source.getType() == ItemType.FLUID) Dispatcher.fluidTrucksUploadingStopped = true;
                if (source.getType() == ItemType.PERISHABLE) Dispatcher.perishableTrucksUploadingStopped = true;
            }
        }
    }
}
