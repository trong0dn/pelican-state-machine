package lab4;

/**
 * Main class for running the state machine.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class TestHarness {
	
	public static void main(String[] args) throws InterruptedException {
		Context context = new Context();
		Thread.sleep(12000);
		context.pedestrianWaiting();
		Thread.sleep(30000);
		context.pedestrianWaiting();
	}

}
