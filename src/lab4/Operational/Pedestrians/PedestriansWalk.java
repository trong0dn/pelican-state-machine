package lab4.Operational.Pedestrians;

import lab4.Context;
import lab4.State;

/**
 * This state models pedestrians walking 
 * within pedestrians enabled state.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class PedestriansWalk implements State {

	private Context context;
	
	/**
	 * PedestrianWalk state constructor.
	 * @param context Context, the context of the state.
	 */
	public PedestriansWalk(Context context) {
		this.context = context;
		context.setCurrentState(this);
		System.out.println(this.getClass().getName());
		System.out.print("entry/");
		signalPedestrians(PedestrianActions.WALK);
		setTimer(15000);
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
		// TODO Auto-generated method stub
		
	}

}
