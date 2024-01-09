package synchronization;

public class synchronizationExample {
    private int number = 0;

    public synchronized void incrementNumber() {
        number++;
    }

    public static void main(String[] args) {
        synchronizationExample example = new synchronizationExample();
        example.multiThread();
    }

    public void multiThread() {
        Thread thread = new Thread(()->{
            for (int i = 1; i <=20000 ; i++) {
                incrementNumber();
            }
        });
        Thread thread1 = new Thread(()->{
            for (int i = 1; i <=20000 ; i++) {
                incrementNumber();
            }
        });
        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Number is: " + number);
    }
}
