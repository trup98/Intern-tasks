package ImplimentingRunnable;

class StartThread implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 8; i++) {
            System.out.println("Run method");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class RunnableExample {
    public static void main(String[] args) {
//      RunnableExample runnableExample = new RunnableExample();
//      Thread thread = new Thread(runnableExample);
        Thread thread = new Thread(new StartThread());
        thread.start();
        for (int i = 1; i <= 8; i++) {
            System.out.println("Main method");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
