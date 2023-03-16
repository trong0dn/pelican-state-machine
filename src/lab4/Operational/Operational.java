package lab4.Operational;

import lab4.Context;
import lab4.State;
import lab4.Operational.Vehicles.VehiclesEnabled;

/**
 * This superstate models the operational state.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class Operational implements State {

	/**
	 * Constructor for Operational state.
	 * @param context Context
	 */
	public Operational(Context context) {
		System.out.println(this.getClass().getName());
		context.setCurrentState(new VehiclesEnabled(context));
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
		// TODO Auto-generated method stub
		
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
