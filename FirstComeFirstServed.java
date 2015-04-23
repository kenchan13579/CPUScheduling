
import java.util.*;

public class FirstComeFirstServed extends SchedulingAlgorithms {

	public FirstComeFirstServed(List<Process> processes) {
		super(processes);
}

	@Override
	public void run() {
		sortByArrivalTime();
		boolean running = false;
		System.out
				.println("============================================================");
		System.out
				.println("Process ID | Turnaround time | Waiting time | Response time ");
		System.out
				.println("============================================================");
		
		for (Process ps : processes) {
			if (!running) {
				ps.scheduledTime = ps.getArrivalTime();
				ps.processCompletionTime = ps.getArrivalTime()
						+ ps.getBurstTime();
				
			} else {
				ps.scheduledTime = (ps.getArrivalTime() < systemTime) ? systemTime
						: ps.getArrivalTime();
				ps.processCompletionTime = ps.scheduledTime + ps.getBurstTime();

			}
			
			ps.turnAroundTime = round(
					ps.processCompletionTime - ps.getArrivalTime(), 2);
			systemTime = ps.processCompletionTime;
		
			ps.waitingTime = round(ps.scheduledTime - ps.getArrivalTime(), 2);
			ps.responseTime = ps.turnAroundTime;
			running = true;
			print(ps);
		}
		stat(processes.size());
	}

}
