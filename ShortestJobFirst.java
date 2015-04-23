import java.util.*;


public class ShortestJobFirst extends SchedulingAlgorithms {

	
	private LinkedList<Process> q;

	public ShortestJobFirst(List<Process> processes) {
		super(processes);
		q = new LinkedList<Process>();
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
			if (!ps.done) // unprocessed process
			{
				if (q.isEmpty()) // if no ps waiting
				{
					if (!running) // first ps
					{
						
						ps.scheduledTime = ps.getArrivalTime();
						ps.processCompletionTime = ps.scheduledTime
								+ ps.getBurstTime();
					} else {
						ps.scheduledTime = (ps.getArrivalTime() < systemTime) ? systemTime
								: ps.getArrivalTime();
						ps.processCompletionTime = ps.scheduledTime
								+ ps.getBurstTime();
					}
					systemTime = ps.processCompletionTime;
					ps.turnAroundTime = ps.processCompletionTime
							- ps.getArrivalTime();
					ps.waitingTime = ps.scheduledTime - ps.getArrivalTime();
					ps.responseTime = ps.turnAroundTime;
					ps.done = true;
					addWaitingProcess();
					print(ps);
				} else {
					while (!q.isEmpty()) {
						Process proc = q.getFirst();

						proc.scheduledTime = systemTime;
						proc.processCompletionTime = proc.scheduledTime
								+ proc.getBurstTime();
						systemTime = proc.processCompletionTime;
						proc.turnAroundTime = proc.processCompletionTime
								- proc.getArrivalTime();
						proc.waitingTime = proc.scheduledTime
								- proc.getArrivalTime();
						proc.responseTime = proc.turnAroundTime;
						proc.done = true;
						print(proc);
						q.removeFirst();
						addWaitingProcess();

					}
				}
				
				stat(processes.size());
			}
		}
	}

	

	public void addWaitingProcess() {
		for (Process ps : processes) {
			if (!ps.done && ps.getArrivalTime() < systemTime && !q.contains(ps)) {
				q.add(ps);
			}
		}
		sortByBurstTime();

	}
	public void sortByBurstTime() {
		Collections.sort(q, new Comparator<Process>() {

			@Override
			public int compare(Process o1, Process o2) {
				if (o1.getBurstTime() - o2.getBurstTime() > 0)
					return 1;
				else if (o1.getBurstTime() - o2.getBurstTime() < 0)
					return -1;
				else
					return 0;
			}

		});

	}

}
