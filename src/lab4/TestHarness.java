package lab4;

/**
 * Main class for running the state machine.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class TestHarness {
	
	public static void main(String[] args) throws InterruptedException {
		Context context = new Context();
		
		// Press PEDESTRIAN_WAITING 5000 ms before TIMEOUT during vehiclesGreen
		// vehiclesGreen -> vehiclesYellow
		Thread.sleep(5000);
		context.pedestrianWaiting();
		
		// Press PEDESTRIAN_WAITING before TIMEOUT during pedestiranFlash, second flash
		// isPedestrianWaiting is set to true for vehiclesGreen state
		Thread.sleep(21000);
		context.pedestrianWaiting();
		
		// Sleep at vehiclesGreenInt for 3000 ms, then Press PEDESTRIAN_WAITING
		// vehiclesGreenInt -> vehiclesYellow
		Thread.sleep(18000);	
		context.pedestrianWaiting();
		
		// Sleep indefinitely at vehiclesGreenInt
		Thread.sleep(40000);
		System.out.print(">>> WAITING FOREVER FOR INTERRUPT");
	}

}
