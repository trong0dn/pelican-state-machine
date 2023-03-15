package lab4.Operational.Vehicles;

import lab4.Context;
import lab4.State;

/**
 * This state models vehicles GREEN light interrupt
 * within vehicles enabled state.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class VehiclesGreenInt implements State {
	
	/**
	 * VehiclesGreenInt state constructor.
	 * @param context Context, the context of the state.
	 */
	public VehiclesGreenInt(Context context) {
		context.setVehicleState(this);
		System.out.println(this.getClass().getName());
		context.pedestrianWaiting();
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
