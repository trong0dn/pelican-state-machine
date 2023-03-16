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

The goal is to build a basic state machine used to control a pedestrian crossing.
Based on the following blog post:
[Introduction to Hierarchical State Machines](https://barrgroup.com/embedded-systems/how-to/introduction-hierarchical-state-machines)

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
\---src
    |   module-info.java
    |   state-machine.png
    |
    \---lab4
        |   Context.java
        |   State.java
        |   TestHarness.java
        |
        \---Operational
            |   Operational.java
            |
            +---Pedestrians
            |       PedestriansEnabled.java
            |       PedestriansFlash.java
            |       PedestriansWalk.java
            |
            \---Vehicles
                    VehiclesEnabled.java
                    VehiclesGreen.java
                    VehiclesGreenInt.java
                    VehiclesYellow.java
```

`Context.java`
The Context of the State Design Pattern.

`State.java`
State interface for performing state-specific behavior.

`Operational.java`
Superstate of the state machine in operation.

`VehiclesEnabled.java`
Implements the State interface for state-specific behavior for the 
VehicleEnabled state and substate of Operational.

`VehiclesGreen.java`
Implements the State interface and substate of VehiclesEnabled.

`VehiclesGreenInterrupt.java`
Implements the State interface and substate of VehiclesEnabled.

`VehiclesYellow.java`
Implements the State interface and substate of VehiclesEnabled.

`PedestriansEnabled.java`
Implements the State interface for state-specific behavior for the 
PedestrianEnabled state and substate of Operational.

`PedestrianWalk.java`
Implements the State interface and substate of PedestriansEnabled.

`PedestrianFlash.java`
Implements the State interface and substate of PedestriansEnabled.
     
## Requirements and Dependencies

This application was created on Windows 10 OS using Eclipses IDE.
Run on the latest version of jdk-17 using Java 17.

No other external dependencies required.

## Compiling and Running the Application

Download and extract the .zip file. Then import the source code directly and 
run the program in local IDE. Or the file can be executed on Command Prompt
using the following commands:

```console
> cd C:\..\bin\                          // Navigate to the bin directory	
> java -cp . lab4.TestHarness            // Set classpath to run application
```

## Questions/Answers

**1. There is a defect in the design which will annoy law-abiding pedestrians.**

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

Solution: Modify the initial TIMEOUT junction point in the vehiclesGreen 
state to exit upon triggering PEDESTRIAN_WAITING by the vehiclesGreenInt state. 
This way the pedestrians are not forced waiting the entire time duration when 
pressing the PEDESTRIAN_WAITING button.

**2. There is second error in the design.**

The TIMEOUT event choice junction point, which triggers the transition from the 
pedestrianFlash state should have signalPedestrian(DONT_WALK) set upon 
[pedestrianFlashCtr==0], as the last signalPedestrian(BLANK) remains. Hence, a 
BLANK signal upon exit from the pedestrianEnabled state means that there is no 
DONT_WALK signal being displayed to the pedestrian during the transition to the 
vehiclesEnabled state. This is a safety issue as the pedestrian signal remains
BLANK while entry into the vehiclesEnabled state.

To be compliant with the Liskov substitution principle (LSP), none of the 
substates of vehiclesEnabled should disable the vehicles or enable the 
pedestrians. In particular, disabling vehicles (by switching the red light), or 
enabling the pedestrians (by displaying the WALK signal) in any of the nested 
states vehiclesGreen or vehiclesYellow would be inconsistent with being in the 
superstate vehiclesEnabled and would be a violation of the LSP (it will also be 
a safety hazard in this case).

Solution: Modify the state machine to implement the alternative design of 
switching the DONT_WALK signal in the exit action from pedestriansEnabled 
and switching the RED signal for vehicles in the exit action from 
vehiclesEnabled.

## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, allowance is 
made for fair use for purposes such as criticism, comment, news reporting, 
teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise be 
infringing.