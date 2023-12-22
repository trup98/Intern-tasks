package AnnonymusRunnable;

public class AnnyRunnableExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println("Run method");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        for (int i = 0; i <= 5; i++) {
            System.out.println("Main method");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
