import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public abstract class SchedulingAlgorithms {
	public List<Process> processes;
	public ArrayList<Process> queue;
	public double totalWaitingTime, totalTurnAroundTime, throughput,
	totalResponseTime, prvCompletionTime, proceessArrivalTime,
	waitingTime;
public double avgTurnAroundTime, avgWaitingTime, avgResponseTime;

public double systemTime = 0.0;
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public SchedulingAlgorithms(List<Process> processes)
	{
		this.processes = processes;
	}
	public void stat(int size) {
		 throughput = size/systemTime;
		avgTurnAroundTime = totalTurnAroundTime / size;
		avgWaitingTime = totalWaitingTime / size;
		avgResponseTime = totalResponseTime / size;
		System.out.println("===============================================");
		System.out.println("Avg waiting time:" + avgWaitingTime);
		System.out.println("===============================================");
		System.out.println("Avg turn around time:" + avgTurnAroundTime);
		System.out.println("===============================================");
		System.out.println("Avg response time:" + avgResponseTime);
		System.out.println("===============================================");
		System.out.println("throughput : " + throughput + "per time unit ");
	}

	public void print(Process p) {
		totalTurnAroundTime += p.turnAroundTime;
		totalWaitingTime += p.waitingTime;
		totalResponseTime += p.responseTime;
		System.out.println("   " + p.getID() + "    |   " + "   "
				+ p.turnAroundTime + "    |   " + "   " + p.waitingTime + " "
				+ "    |    " + p.responseTime);
		System.out.println("----------------------------------------");
	}
	public abstract void run();
	public void sortByArrivalTime() {

		Collections.sort(this.processes, new Comparator<Process>() {

			@Override
			public int compare(Process o1, Process o2) {

				if (o1.getArrivalTime() - o2.getArrivalTime() > 0)
					return 1;
				else if (o1.getArrivalTime() - o2.getArrivalTime() < 0)
					return -1;
				else
					return 0;

			}

		});
		

	}
}
