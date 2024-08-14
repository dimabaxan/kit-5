import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {
        final int NUMBER_OF_PHILOSOPHERS = 5;
        final int EAT_LIMIT = 3;

        Philosopher[] philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
        Semaphore[] forks = new Semaphore[NUMBER_OF_PHILOSOPHERS];


        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);
        }


        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            Semaphore leftFork = forks[i];
            Semaphore rightFork = forks[(i + 1) % NUMBER_OF_PHILOSOPHERS];


            if (i == NUMBER_OF_PHILOSOPHERS - 1) {
                philosophers[i] = new Philosopher(i + 1, rightFork, leftFork, EAT_LIMIT);
            } else {
                philosophers[i] = new Philosopher(i + 1, leftFork, rightFork, EAT_LIMIT);
            }

            philosophers[i].start();
        }


        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All philosophers have finished eating.");
    }
}

