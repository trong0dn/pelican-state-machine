package lab4.Operational.Pedestrians;

import lab4.Context;
import lab4.State;

/**
 * This state models pedestrians flashing lights 
 * within pedestrians enabled state.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class PedestriansFlash implements State {

	private Context context;
	private int pedestrianFlashCtr;
	
	/**
	 * PedestrianFlash state constructor.
	 * @param context Context, the context of the state.
	 */
	public PedestriansFlash(Context context) {
		this.context = context;
		context.setCurrentState(this);
		System.out.println(this.getClass().getName());
		System.out.print("entry/");
		setTimer(1000);
		pedestrianFlashCtr = 7;
		System.out.println(String.format("pedestrianFlashCtr=%d", pedestrianFlashCtr));
		System.out.print("exit/");
		killTimer();
	}
	
	/**
	 * Get pedestrian flash counter.
	 * @return int, flash counter
	 */
	public int getPedestrianFlashCtr() {
		return this.pedestrianFlashCtr;
	}
	
	/**
	 * Instance of pedestrianFlash upon re-entry to state.
	 */
	public void instance() {
		context.setCurrentState(this);
		System.out.println(this.getClass().getName());
		System.out.print("entry/");
		setTimer(1000);
		System.out.println(String.format("pedestrianFlashCtr=%d", pedestrianFlashCtr));
		System.out.print("exit/");
		killTimer();
	}
	
	/**
	 * Set sleep timer upon state entry.
	 * @param time int, time in milliseconds 
	 */
	@Override
	public void setTimer(int timer) {
		System.out.println(String.format("setTimer(%d)", timer));
		try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			killTimer();
		}
	}
	
	/**
	 * Invoke context timeout upon state exit.
	 */
	@Override
	public void killTimer() {
		System.out.println(String.format("killTimer()"));
		pedestrianFlashCtr--;
		context.timeout();
	}

	/**
	 * Handle pedestrian signal upon pedestrian action.
	 * @param pedestrianActions PedestrianActions, the pedestrian action
	 */
	@Override
	public void signalPedestrians(PedestrianActions pedestrianActions) {
		this.context.setPedestrianActions(pedestrianActions);
		System.out.println(String.format("signalPedestrians(%s)", 
				context.getPedestrianActions().toString()));
	}
	
	/**
	 * Handle vehicle signal upon vehicle action.
	 * @param vehicleActions VehicleActions, the vehicle action
	 */
	@Override
	public void signalVehicles(VehicleActions vehicleActions) {
		this.context.setVehicleActions(vehicleActions);
		System.out.println(String.format("signalVehicles(%s)", 
				context.getVehicleActions().toString()));
	}
	
}
