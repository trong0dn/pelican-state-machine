```
Carleton University 
Department of Systems and Computer Engineering 
SYSC 3303A Real-Time Concurrent Systems Winter 2023 
Assignment 4 - State Machines 

@author Trong Nguyen
@version 1.0
@date 18/03/23
```
---------------------------------------------------------------------------------

# State model of a PELICAN (PEdestrian LIght CONtrolled) crossing

## Problem Description

The goal is to build a very basic state machine used to control a pedestrian 
crossing.

![state-machine](/src/state-machine.png)

## Project Structure

```
PELICAN-STATE-MACHINE
|   .classpath
|   .gitignore
|   .project
|   README.md
|   README.txt
|
+---.settings
|       org.eclipse.jdt.core.prefs
|
+---bin
|   |   module-info.class
|   |
|   \---lab4
|           Context.class
|           Main.class
|           PedestriansEnabled.class
|           State$PedestrianActions.class
|           State$VehicleActions.class
|           State.class
|           VehiclesEnabled.class
|
\---src
    |   module-info.java
    |
    \---lab4
            Context.java
            Main.java
            PedestriansEnabled.java
            State.java
            VehiclesEnabled.java
```
            
## Requirements and Dependencies

This application was created on Windows 10 OS using Eclipses IDE.
Run on the latest version of jdk-17 using Java 17.

No other external dependencies required.

## Compiling and Running the Application

Download and extract the .zip file. Then import the source code directly and 
run the program in local IDE.

```console
> cd C:\..\bin\					// Navigate to the src directory	
> java -cp . lab4.Main			// Set classpath to run application
```

## Questions

1. There is a defect in the design which will annoy law-abiding pedestrians.

The TIMEOUT event, which triggers the transition from the pedestrianFlash
state should have signalPedestrian(DONT_WALK) set upon [pedestrianFlashCtr==0],
as the last signalPedestrian(BLANK) remains. Hence, a BLANK signal upon exit
from the pedestrianEnabled state means that there is no DONT_WALK signal being
displayed to the pedestrian during the transition to the vehiclesEnabled state. 

To be compliant with the Liskov substitution principle (LSP), none of the 
substates of vehiclesEnabled should disable the vehicles or enable the 
pedestrians. In particular, disabling vehicles (by switching the red light), or 
enabling the pedestrians (by displaying the WALK signal) in any of the nested 
states vehiclesGreen or vehiclesYellow would be inconsistent with being in the 
superstate vehiclesEnabled and would be a violation of the LSP (it will also be 
a safety hazard in this case).

Solution: Modify the state machine to implement the alternative design of 
switching the DONT_WALK signal in the exit action from pedestriansEnabled 
and switching the red light for vehicles in the exit action from 
vehiclesEnabled.

2. There is second error in the design.

The traffic light system operates by default with vehicles enabled and 
pedestrians disabled, but pedestrians can activate the traffic light switch 
by pushing a button (PEDESTRIAN_WAITING). When the button is pushed, vehicles 
are given a yellow light, followed by a red light, which triggers a WALK signal 
for pedestrians, which then changes to a flashing DONT_WALK signal. Once the 
flashing stops, vehicles are given a green light, and the cycle repeats. 
However, the traffic lights do not respond to the PEDESTRIAN_WAITING event 
immediately after this cycle. The system remembers that the button was pushed 
and ensures that vehicles receive a minimum of several seconds of green light 
before the cycle is repeated.

The state model used in the system ensures the minimum green light time for 
vehicles by incorporating two states, vehiclesGreen and vehiclesGreenInt, and 
the isPedestrianWaiting flag. The vehiclesGreen state corresponds to the 
uninterrupted green light for vehicles. When the PEDESTRIAN_WAITING event 
occurs in this state, the system sets the isPedestrianWaiting flag to indicate 
that the button was pushed. The only condition for leaving the vehiclesGreen 
state is the TIMEOUT event, which triggers a transition to either the 
vehiclesYellow state (if the isPedestrianWaiting flag is set) or to the 
vehiclesGreenInt state, which represents the interruptible green light for 
vehicles.

Solution: Modify the initial transition in the vehiclesEnabled state to enter 
the vehiclesGreenInt substate. In addition, change the target of the TIMEOUT 
transition for state pedestriansFlash from vehiclesEnabled to vehiclesGreen.

## Technical Specifications

### Context

The Context of the State Design Pattern.

### State

State interface for performing state-specific behavior.

### PedestriansEnabled

Implement the State interface, that is, implement (encapsulate) the 
state-specific behavior for the PedestrianEnabled state.

## VehiclesEnabled

Implement the State interface, that is, implement (encapsulate) the 
state-specific behavior for the VehicleEnabled state.

## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, allowance is 
made for fair use for purposes such as criticism, comment, news reporting, 
teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise be 
infringing.