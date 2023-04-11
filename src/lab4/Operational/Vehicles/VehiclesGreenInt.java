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
	
	Context context;
	
	/**
	 * VehiclesGreenInt state constructor.
	 * @param context Context, the context of the state.
	 */
	public VehiclesGreenInt(Context context) {
		this.context = context;
		context.setCurrentState(this);
		System.out.print("STATE: ");
		System.out.println(this.getClass().getSimpleName());
		while (!context.getIsPedestrianWaiting()) {
			setTimer(100);
		}
		killTimer();
	}

	/**
	 * Set sleep timer upon state entry.
	 * @param time int, time in milliseconds 
	 */
	@Override
	public void setTimer(int timer) {
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
		new VehiclesYellow(context);
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
