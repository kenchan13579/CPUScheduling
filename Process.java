
public class Process {
	private double arrivalTime; //0 -200
	private double burstTime; //0.1 -10 
	private int processID;
	public boolean done = false;
	public double intialBurstTime, processCompletionTime, responseTime,	turnAroundTime, waitingTime, scheduledTime;
	public double PreemptedTime=0;
	public Process(int id, double at, double ett )
	{
		processID = id;
		arrivalTime = at;
		burstTime = ett;
		processCompletionTime= 0 ;
		scheduledTime = at;
	}
	public int getID() {
		return processID;
	}
	public void setID(int x) {
		processID = x ;
	}
	public double getArrivalTime()
	{
		return arrivalTime;
	}
	public void setArrivalTime(double x)
	{
		arrivalTime = x;
	}
	public double getBurstTime ()
	{
		return burstTime;
	}
	public void setBurstTime(double x )
	{
		burstTime = x ;
	}
}
