package lab4.Operational.Vehicles;

import lab4.Context;
import lab4.State;

/**
 * This state models vehicles GREEN light 
 * within vehicles enabled state.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class VehiclesGreen implements State {
	
	private Context context;
	
	/**
	 * VehiclesGreen state constructor.
	 * @param context Context, the context of the state.
	 */
	public VehiclesGreen(Context context) {
		this.context = context;
		context.setCurrentState(this);
		System.out.println(this.getClass().getName());
		System.out.print("entry/");
		context.setIsNotPedestrianWaiting();
		signalVehicles(VehicleActions.GREEN);
		setTimer(10000);
		System.out.println(String.format("isPedestrianWaiting=%s", 
				context.getIsPedestrianWaiting()));
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * Handle vehicle signal upon vehicle action.
	 * @param vehicleActions VehicleActions, the vehicle action
	 */
	@Override
	public void signalVehicles(VehicleActions vehicleActions) {
		context.setVehicleActions(vehicleActions);
		System.out.println(String.format("signalVehicles(%s)", 
				context.getVehicleActions().toString()));		
	}

}
