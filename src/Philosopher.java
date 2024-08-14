import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private final int eatLimit;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, int eatLimit) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatLimit = eatLimit;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < eatLimit; i++) {
                think();
                pickUpForks();
                eat();
                putDownForks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void pickUpForks() throws InterruptedException {
        leftFork.acquire();
        System.out.println("Philosopher " + id + " picked up left fork.");
        rightFork.acquire();
        System.out.println("Philosopher " + id + " picked up right fork.");
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void putDownForks() {
        leftFork.release();
        System.out.println("Philosopher " + id + " put down left fork.");
        rightFork.release();
        System.out.println("Philosopher " + id + " put down right fork.");
    }
}



