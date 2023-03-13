package lab4;

/**
 * Concrete state for the Pedestrians.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class PedestriansEnabled implements State {

	private Context context;
	private int pedestrianFlashCtr;
	
	/**
	 * PedestrianEnable state constructor.
	 * @param context Context, the context of the state.
	 */
	public PedestriansEnabled(Context context) {
		System.out.println(this.getClass().getName());
		this.context = context;
		context.setPedestrianState(this);
		signalVehicles(VehicleActions.RED);
		setPedestrianFlashCtr(7);
		System.out.println(String.format(": entry/signalVehicles(%s)", context.getVehicleActions().toString()));
		pedestriansWalk();
	}
	
	/**
	 * Handle state when Pedestrian action signal is WALK.
	 */
	public void pedestriansWalk() {
		System.out.println(String.format("> pedestriansWalk"));
		System.out.println(String.format(": entry/SetTimer(15000)"));
		signalPedestrians(PedestrianActions.WALK);
		System.out.println(String.format(": signalPedestrians(%s)", context.getPedestrianActions().toString()));
		setTimer(15000);
		System.out.println(String.format(": exit/KillTimer()"));
		context.timeout();
	}
	
	/**
	 * Handle state with Pedestrian action signal is Flash.
	 */
	public void pedestriansFlash() {
		System.out.println(String.format("> pedestriansFlash"));
		System.out.println(String.format(": entry/SetTimer(1000)"));
		System.out.println(String.format(": pedestrianFlashCtr=%d", pedestrianFlashCtr));
		pedestrianFlashCtr--;
		System.out.println(String.format(": signalPedestrians(%s)", context.getPedestrianActions().toString()));
		setTimer(1000);
		System.out.println(String.format(": exit/KillTimer()"));
		context.timeout();
	}
	
	/**
	 * Get pedestrian flash counter.
	 * @return int, flash counter
	 */
	public int getPedestrianFlashCtr() {
		return this.pedestrianFlashCtr;
	}
	
	/**
	 * Set pedestrian flash counter.
	 * @param counter int, flash counter
	 */
	public void setPedestrianFlashCtr(int counter) {
		this.pedestrianFlashCtr = counter;
	}
	
	/**
	 * Handle pedestrian signal upon pedestrian action.
	 * @param pedestrianActions PedestrianActions, the pedestrian action
	 */
	@Override
	public void signalPedestrians(PedestrianActions pedestrianActions) {
		this.context.setPedestrianActions(pedestrianActions);
		
	}

	/**
	 * Handle vehicle signal upon vehicle action.
	 * @param vehicleActions VehicleActions, the vehicle action
	 */
	@Override
	public void signalVehicles(VehicleActions vehicleActions) {
		this.context.setVehicleActions(vehicleActions);
	}
	
	/**
	 * Set sleep timer.
	 * @param time int, time in milliseconds 
	 */
	@Override
	public void setTimer(int timer) {
		try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
