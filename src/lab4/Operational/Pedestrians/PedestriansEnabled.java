package lab4.Operational.Pedestrians;

import lab4.Context;
import lab4.State;

/**
 * This state models the pedestrians enabled.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class PedestriansEnabled implements State {

	private Context context;
	
	/**
	 * PedestrianEnable state constructor.
	 * @param context Context, the context of the state.
	 */
	public PedestriansEnabled(Context context) {
		this.context = context;
		context.setCurrentState(this);
		System.out.print("STATE: ");
		System.out.println(this.getClass().getSimpleName());
		System.out.print(":    entry/");
		signalVehicles(VehicleActions.RED);
		context.setCurrentState(new PedestriansWalk(context));
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
		this.context.setVehicleActions(vehicleActions);
		System.out.println(String.format("signalVehicles(%s)", 
				context.getVehicleActions().toString()));
	}
	
}
