import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerTask {
    private final Random rnd = new Random();
    ProgressTracker pt = new ProgressTracker();
    Runnable to = () -> {
        long startTime = System.currentTimeMillis();
        long timeOuts = 5000;

        while(System.currentTimeMillis() - startTime<timeOuts){
            System.out.println("Observer get counter:" + pt.getCounter());
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException ex){
                Thread.currentThread().interrupt();
                break;
            }
        }
    };
    Runnable tw = () -> {
        long startTime = System.currentTimeMillis();
        long timeOuts = 5000;
        while(System.currentTimeMillis() - startTime<timeOuts){
            System.out.println("Start Worker " + Thread.currentThread().threadId());
            pt.increment();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            System.out.println("Worker " + Thread.currentThread().threadId() + " end");
        }
    };

    public void WorkWithThreads(){
        List<Thread> lstThreads = new ArrayList<>(List.of(
                new Thread(tw,"Worker 1"),
                new Thread(tw,"Worker 2"),
                new Thread(tw,"Worker 3")
        ));
        Thread tto = new Thread(to,"Observer 1");

        for(var th: lstThreads){
            th.start();
        }
        tto.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(var th: lstThreads){
            try {
                th.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try{
            tto.join();
        }
        catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }

        System.out.println("Result counter: " + pt.getCounter());
    }
}
