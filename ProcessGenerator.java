import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ProcessGenerator {
	private int np; // number of process: 50 and 100
	private List<Process> processes;
	public Random r1 = new Random();
	public Random r2 = new Random();
	public ProcessGenerator(int numberOfProcess) {
		np = numberOfProcess;
		processes = new ArrayList<Process>();
	}
	public List<Process> generate()
	{
		for (int i = 1; i <= np ; i++)
		{
			processes.add(new Process(i, randomArrivalTime(0, 200), randomBurstTime(0.1, 10)));
		}
		return processes;
	}
	public double randomArrivalTime(double min, double max)
	{
		double random = r1.nextDouble();
		double result = min + (random * (max - min));
		return result;
	}
	public double randomBurstTime(double min, double max)
	{
		double random = r2.nextDouble();
		double result = min + (random * (max - min));
		return result;
	}
	public void clear()
	{
		processes.clear();
	}
}
