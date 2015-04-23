
import java.util.*;

import javax.swing.plaf.SliderUI;

public class RoundRobin extends SchedulingAlgorithms {

	private int timeSlice;
	double currentProcessorTime = 0;

	public RoundRobin(List<Process> processes, int ts) {
		super(processes);
		timeSlice = ts;
		
	}

	

	@Override
	public void run() {
		sortByArrivalTime();
	
		int i = 0;
		int numberOfProcess = processes.size();
	for ( Process p:processes)
	{
		System.out.println(p.getID() +"  |  "+ p.getArrivalTime() + "  |  "+p.getBurstTime());
	}
		// arrival, scheduded time , waiting ,responsetime, turnaround
		System.out
				.println("Process  | 	Turnaround time	| Waiting Time   | Response Time");
		while (!processes.isEmpty()) {
			boolean removedFlag = false;
			Process ps = processes.get(i);
				
				if (ps.getArrivalTime() >= systemTime) {
					systemTime = ps.getArrivalTime();
					ps.scheduledTime = systemTime;
					ps.intialBurstTime = ps.getBurstTime();
				}
				if (ps.getBurstTime() < timeSlice && ps.getBurstTime() > 0) {
					
					ps.turnAroundTime = systemTime - ps.getArrivalTime();
					if (ps.waitingTime == 0 && ps.responseTime == 0) {
						ps.waitingTime = ps.scheduledTime - ps.getArrivalTime();
						ps.responseTime = ps.turnAroundTime;
					} else {
						ps.waitingTime += systemTime - ps.PreemptedTime;

					}
					systemTime += ps.getBurstTime();
					ps.setBurstTime(0);
					print(ps);
					processes.remove(i);
					removedFlag = true;

				} else if (ps.getBurstTime() >= timeSlice) {
					if (ps.waitingTime == 0 && ps.responseTime == 0) {
						ps.waitingTime = ps.scheduledTime - ps.getArrivalTime();
						ps.responseTime = ps.scheduledTime-ps.getArrivalTime()+timeSlice;
					} else {
						ps.waitingTime += systemTime - ps.PreemptedTime;
						

					}
					ps.setBurstTime(ps.getBurstTime() - timeSlice);
					systemTime += timeSlice;
					ps.PreemptedTime = systemTime;
					if (ps.getBurstTime() == 0) {
						ps.turnAroundTime = systemTime - ps.getArrivalTime();
						processes.remove(i);
						removedFlag = true;
					}

				}
				if ( removedFlag )
				{
					if ( i< processes.size())
					{
						
					}
					else 
						i =0;
				}
				else {
						if (i == processes.size() - 1 && processes.size()!=0) {
							i = 0;
						} else if (i < processes.size()
								&& processes.get(i + 1).getArrivalTime() > systemTime) {
							i = 0;
						} else
								i++;}
			
		}
		stat(numberOfProcess);
	}
}

