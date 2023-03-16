package lab4.Operational.Vehicles;

import lab4.Context;
import lab4.State;

/**
 * This state models the vehicles enabled.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class VehiclesEnabled implements State {

	private Context context;
	
	/**
	 * VehiclesEnable state constructor.
	 * @param context Context, the context of the state.
	 */
	public VehiclesEnabled(Context context) {
		this.context = context;
		context.setCurrentState(this);
		System.out.println(this.getClass().getName());
		System.out.print("entry/");
		signalPedestrians(PedestrianActions.DONT_WALK);
		context.setCurrentState(new VehiclesGreen(context));
	}

	/**
	 * Set sleep timer upon state entry.
	 * @param time int, time in milliseconds 
	 */
	@Override
	public void setTimer(int timer) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Invoke context timeout upon state exit.
	 */
	@Override
	public void killTimer() {
		// TODO Auto-generated method stub
		
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
