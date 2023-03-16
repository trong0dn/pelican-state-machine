package lab4;

import lab4.State.PedestrianActions;
import lab4.State.VehicleActions;
import lab4.Operational.Operational;
import lab4.Operational.Pedestrians.PedestriansEnabled;
import lab4.Operational.Pedestrians.PedestriansFlash;
import lab4.Operational.Pedestrians.PedestriansWalk;
import lab4.Operational.Vehicles.VehiclesEnabled;
import lab4.Operational.Vehicles.VehiclesGreen;
import lab4.Operational.Vehicles.VehiclesGreenInt;
import lab4.Operational.Vehicles.VehiclesYellow;

/**
 * The context of the State Design Pattern that refers to the State 
 * interface for performing state-specific behavior.
 * @author Trong Nguyen
 * @version 1.0, 18/03/23
 */
public class Context implements Runnable { 
	
	private Thread thread;
	private State currentState;
	private VehicleActions vehicleActions;
	private PedestrianActions pedestrianActions;
	
	public boolean isPedestrianWaiting;
	public int pedestrianFlashCtr;
	
	/**
	 * Constructor for Context.
	 */
	public Context() {
		this.thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Runnable thread for Context.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		new Operational(this);
	}
	
	/**
	 * Pedestrian waiting event use upon state machine transition.
	 */
	public void pedestrianWaiting() {
		if (getCurrentState().getClass().equals(VehiclesGreen.class) || 
				getCurrentState().getClass().equals(VehiclesGreenInt.class)) {
			System.out.println("PEDESTRIAN_WAITING");
			thread.interrupt();
			setIsPedestrianWaiting();
		}
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
		else if (getVehicleActions().equals(VehicleActions.RED)) {
			// pedestrianFlash
			State pedestriansFlash;
			if (getCurrentState().getClass().equals(PedestriansWalk.class)) {
				pedestriansFlash = new PedestriansFlash(this);
			} else {
				// dynamic choice point
				pedestriansFlash = getCurrentState();
				if ((((PedestriansFlash) pedestriansFlash).getPedestrianFlashCtr()) == 0) {
					System.out.println("[pedestrianFlashCtr==0]");
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
	 * Get current state.
	 * @return State, the current state
	 */
	public State getCurrentState() {
		return this.currentState;
	}
	
	/**
	 * Set the current state.
	 * @param currentState State, the current state
	 */
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
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
