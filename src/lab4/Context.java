package lab4;

import lab4.State.PedestrianActions;
import lab4.State.VehicleActions;
import lab4.Operational.Operational;
import lab4.Operational.Pedestrians.PedestriansEnabled;
import lab4.Operational.Pedestrians.PedestriansFlash;
import lab4.Operational.Pedestrians.PedestriansWalk;
import lab4.Operational.Vehicles.VehiclesEnabled;
import lab4.Operational.Vehicles.VehiclesGreenInt;
import lab4.Operational.Vehicles.VehiclesYellow;

/**
 * The context of the State Design Pattern that refers to the State 
 * interface for performing state-specific behavior.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class Context { 
	
	private State vehicleState;
	private State pedestrianState;
	private VehicleActions vehicleActions;
	private PedestrianActions pedestrianActions;
	
	public boolean isPedestrianWaiting;
	public int pedestrianFlashCtr;
	
	/**
	 * Constructor for Context.
	 */
	public Context() {
		new Operational(this);
	}
	
	/**
	 * Pedestrian waiting event use upon state machine transition.
	 */
	public void pedestrianWaiting() {
		System.out.println("PEDESTRIAN_WAITING");
		setIsPedestrianWaiting();
		new VehiclesYellow(this);
	}
	
	/**
	 * Timeout event use upon state machine transition.
	 */
	public void timeout() {
		System.out.println("TIMEOUT");
		// GREEN
		if (getVehicleActions().equals(VehicleActions.GREEN)) {
			// junction point
			if (getIsPedestrianWaiting()) {
				// vehiclesYellow
				System.out.println("[isPedestrianWaiting]");
				// UPDATE: set PedestrianWaiting to false
				setIsNotPedestrianWaiting();
				new VehiclesYellow(this);
			} else {
				// vehiclesGreenInt
				System.out.println("[else]");
				new VehiclesGreenInt(this);
			}
		}
		// YELLOW
		else if (getVehicleActions().equals(VehicleActions.YELLOW)) {
			// pedestrianEnabled
			new PedestriansEnabled(this);
		}
		// RED
		else {
			// pedestrianFlash
			State pedestriansFlash;
			if (getPedestrianState().getClass().equals(PedestriansWalk.class)) {
				pedestriansFlash = new PedestriansFlash(this);
			} else {
				// dynamic choice point
				pedestriansFlash = getPedestrianState();
				if ((((PedestriansFlash) pedestriansFlash).getPedestrianFlashCtr()) == 0) {
					System.out.println("[pedestrianFlashCtr==0]");
					// UPDATE: exit/signalPedestrians(DONT_WALK)
					((PedestriansFlash) pedestriansFlash).signalPedestrians(PedestrianActions.DONT_WALK);
					new VehiclesEnabled(this);
				}
				else if (((((PedestriansFlash) pedestriansFlash).getPedestrianFlashCtr()) & 1) == 0) {
					System.out.println("[(pedestrianFlashCtr&1)==0]");
					((PedestriansFlash) pedestriansFlash).signalPedestrians(PedestrianActions.DONT_WALK);
					((PedestriansFlash) pedestriansFlash).instance();
				} 
				else {
					System.out.println("[else]");
					((PedestriansFlash) pedestriansFlash).signalPedestrians(PedestrianActions.BLANK);
					((PedestriansFlash) pedestriansFlash).instance();
				}
			}
		}
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
	 * @param vehicleState State, the vehicle state
	 */
	public void setVehicleState(State vehicleState) {
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
	 * @param pedestrianState State, the pedestrian state
	 */
	public void setPedestrianState(State pedestrianState) {
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
	 * Get whether pedestrian is waiting or not.
	 * @return boolean, true if the pedestrian is waiting, otherwise false
	 */
	public boolean getIsPedestrianWaiting() {
		return this.isPedestrianWaiting;
	}
	
	/**
	 * Set pedestrian to wait.
	 */
	public void setIsPedestrianWaiting() {
		this.isPedestrianWaiting = true;
	}
	
	/**
	 * Set pedestrian to not wait.
	 */
	public void setIsNotPedestrianWaiting() {
		this.isPedestrianWaiting = false;
	}
	
}
