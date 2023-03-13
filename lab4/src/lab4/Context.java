package lab4;

import lab4.State.PedestrianActions;
import lab4.State.VehicleActions;

/**
 * The Context of the State Design Pattern that refers to the State 
 * interface for performing state-specific behavior.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class Context { 
	
	private VehiclesEnabled vehicleState;
	private PedestriansEnabled pedestrianState;
	private PedestrianActions pedestrianActions;
	private VehicleActions vehicleActions;
	
	/**
	 * Context constructor.
	 */
	public Context() {
		vehicleState = new VehiclesEnabled(this);
	}
	
	/**
	 * Get vehicle state.
	 * @return State, the vehicle state
	 */
	public State getVehicleState() {
		return this.vehicleState;
	}
	
	/**
	 * Set vehicle state.
	 * @param vehicleState VehiclesEnabled, state
	 */
	public void setVehicleState(VehiclesEnabled vehicleState) {
		this.vehicleState = vehicleState;
	}
	
	/**
	 * Get pedestrian state.
	 * @return State, the pedestrian state
	 */
	public State getPedestrianState() {
		return this.pedestrianState;
	}
	
	/**
	 * Set pedestrian state.
	 * @param pedestrianState PedestriansEnabled state
	 */
	public void setPedestrianState(PedestriansEnabled pedestrianState) {
		this.pedestrianState = pedestrianState;
	}
	
	/**
	 * Get pedestrian action.
	 * @return PedestrianActions, the pedestrian action
	 */
	public PedestrianActions getPedestrianActions() {
		return this.pedestrianActions;
	}
	
	/**
	 * Set pedestrian action.
	 * @param pedestrianActions PedestrianActions, the pedestrian action
	 */
	public void setPedestrianActions(PedestrianActions pedestrianActions) {
		this.pedestrianActions = pedestrianActions;
	}
	
	/**
	 * Get vehicle action.
	 * @return VehicleActions, the vehicle action
	 */
	public VehicleActions getVehicleActions() {
		return this.vehicleActions;
	}
	
	/**
	 * Set vehicle action.
	 * @param vehicleActions VehicleActions, the vehicle action
	 */
	public void setVehicleActions(VehicleActions vehicleActions) {
		this.vehicleActions = vehicleActions;
	}
	
	/**
	 * Pedestrian waiting event use upon state machine transition.
	 */
	public void pedestrianWaiting() {
		System.out.println(PedestrianActions.PEDESTRIAN_WAITING.toString());
		vehicleState.setIsPedestrianWaiting();
	}
	
	/**
	 * Timeout event use upon state machine transition.
	 */
	public void timeout() {
		System.out.println("TIMEOUT");
		// GREEN
		if (getVehicleActions().equals(VehicleActions.GREEN)) {
			if (vehicleState.getIsPedestrianWaiting()) {
				// vehiclesYellow
				System.out.println("[isPredestrianWaiting]");
				vehicleState.vehiclesYellow();
			} else {
				// vehiclesGreenInt
				System.out.println("[else]");
				vehicleState.vehiclesGreenInt();
			}
		}
		// YELLOW
		else if (getVehicleActions().equals(VehicleActions.YELLOW)) {
			// pedestrianEnabled
			pedestrianState = new PedestriansEnabled(this);
		}
		// RED
		else {
			// pedestrianFlash
			if (pedestrianState.getPedestrianFlashCtr() == 0) {
				System.out.println("[pedestrianFlashCtr==0]");
				vehicleState = new VehiclesEnabled(this);
			}
			else if ((pedestrianState.getPedestrianFlashCtr() & 1) == 0) {
				System.out.println("[(pedestrianFlashCtr&1)==0]");
				pedestrianState.signalPedestrians(PedestrianActions.DONT_WALK);
				pedestrianState.pedestriansFlash();
			} 
			else {
				System.out.println("[else]");
				pedestrianState.signalPedestrians(PedestrianActions.BLANK);
				pedestrianState.pedestriansFlash();
			}
		}
	}
	
}
