package ExtendingThreadClass;

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("Run method");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();

        for (int i = 1; i <= 10; i++) {
            System.out.println("Main Method");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
