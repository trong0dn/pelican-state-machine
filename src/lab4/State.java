package lab4;

/**
 * State interface for Pelican states.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public interface State {
	
	/**
	 * Pedestrian actions within the state machine.
	 */
	public static enum PedestrianActions {DONT_WALK, PEDESTRIAN_WAITING, WALK, BLANK};
	
	/**
	 * Vehicle actions within the state machine.
	 */
	public static enum VehicleActions {GREEN, YELLOW, RED};
	
	/**
	 * Handle pedestrian signal upon pedestrian action.
	 * @param pedestrianActions PedestrianActions, the pedestrian action
	 */
	public void signalPedestrians(PedestrianActions pedestrianActions);
	
	/**
	 * Handle vehicle signal upon vehicle action.
	 * @param vehicleActions VehicleActions, the vehicle action
	 */
	public void signalVehicles(VehicleActions vehicleActions);
	
	/**
	 * Set sleep timer.
	 * @param time int, time in milliseconds 
	 */
	public void setTimer(int time);
	
}
