package lab4;

/**
 * Concrete state for the Vehicles.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class VehiclesEnabled implements State {

	private Context context;
	private Boolean isPedestrianWaiting = null;

	/**
	 * VehiclesEnable state constructor.
	 * @param context Context, the context of the state.
	 */
	public VehiclesEnabled(Context context) {
		System.out.println(this.getClass().getName());
		this.context = context;
		context.setVehicleState(this);
		signalPedestrians(PedestrianActions.DONT_WALK);
		System.out.println(String.format(": entry/signalPedestrians(%s)", 
				context.getPedestrianActions().toString()));
		vehiclesGreen();
	}

	/**
	 * Handle state when Vehicle action is GREEN.
	 */
	public void vehiclesGreen() {
		System.out.println(String.format("> vehiclesGreen"));
		System.out.println(String.format(": entry/SetTimer(10000)"));
		signalVehicles(VehicleActions.GREEN);
		System.out.println(String.format(": signalVehicles(%s)", context.getVehicleActions().toString()));
		if (isPedestrianWaiting == null) {
			isPedestrianWaiting = false;
		}
		System.out.println(String.format(": isPedestrianWaiting=%s", isPedestrianWaiting));
		setTimer(10000);
		System.out.println(String.format(": exit/KillTimer()"));
		context.timeout();
	}
	
	/**
	 * Handle state when Vehicle action is GREEN and pedestrian is not waiting.
	 */
	public void vehiclesGreenInt() {
		System.out.println(String.format("> vehiclesGreenInt"));
		context.pedestrianWaiting();
	}
	
	/**
	 * Handle state when Vehicle action is YELLOW.
	 */
	public void vehiclesYellow() {
		System.out.println(String.format("> vehiclesYellow"));
		System.out.println(String.format(": entry/SetTimer(3000)"));
		signalVehicles(VehicleActions.YELLOW);
		System.out.println(String.format(": signalVehicles(%s)", context.getVehicleActions().toString()));
		setTimer(3000);
		System.out.println(String.format(": exit/KillTimer()"));
		context.timeout();
	}
	
	/**
	 * Get whether pedestrian is waiting or not.
	 * @return boolean, true if the pedestrian is waiting, otherwise false
	 */
	public boolean getIsPedestrianWaiting() {
		return this.isPedestrianWaiting;
	}
	
	/**
	 * Set pedestrian to wait.
	 */
	public void setIsPedestrianWaiting() {
		this.isPedestrianWaiting = true;
	}
	
	/**
	 * Set pedestrian to not wait.
	 */
	public void setIsNotPedestrianWaiting() {
		this.isPedestrianWaiting = false;
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
