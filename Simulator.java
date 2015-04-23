import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter one of the CPU scheduling algorithms (FCFS , SJF, RR) :");
		
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		String algorithms = sc.nextLine();
		int numberProcess = 0;
		int timeSlice = 0;
		if ( algorithms.equalsIgnoreCase("FCFS") || algorithms.equalsIgnoreCase("SJF") ||algorithms.equalsIgnoreCase("RR") )
		{
			System.out.println("Enter Number of process (50 or 100):");
			String numberOfProcess = sc2.nextLine();
			try {
				 numberProcess = Integer.parseInt(numberOfProcess);
			}
			catch (NumberFormatException e)
			{
				System.out.println("Enter a valid number.");
			}
			ProcessGenerator generator = new ProcessGenerator(numberProcess);
			if (algorithms.equalsIgnoreCase("RR"))
			{
				System.out.println("Enter a time slice ( 1  or 2 ).");
				String ts = sc3.nextLine();
				timeSlice = Integer.parseInt(ts);
			}
			
				double totalTurnaroundTime =0;
				double totalWaitingTime = 0 ;
				double totalResponseTime = 0;
				double totalthroughput = 0;
				for ( int i = 1; i<=5 ; i++)
				{
					
					// **************************** RR *******************************
					if (algorithms.equalsIgnoreCase("RR"))
					{
						
						
							 
							RoundRobin rr = new RoundRobin(generator.generate(), timeSlice);
						rr.run();
						totalResponseTime += rr.avgResponseTime;
						totalWaitingTime += rr.avgWaitingTime;
						totalTurnaroundTime += rr.avgTurnAroundTime;
						totalthroughput += rr.throughput;
						generator.clear();	
					}
					// **************************** FCFS *******************************
					if (algorithms.equalsIgnoreCase("FCFS") )
					{
						FirstComeFirstServed fcfs = new FirstComeFirstServed( generator.generate() );
						fcfs.run();
						totalResponseTime += fcfs.avgResponseTime;
						totalWaitingTime += fcfs.avgWaitingTime;
						totalTurnaroundTime += fcfs.avgTurnAroundTime;
						totalthroughput += fcfs.throughput;
						generator.clear();	
					}
					// **************************** SJF *******************************
					if (algorithms.equalsIgnoreCase("SJF") )
					{
					ShortestJobFirst sjf = new ShortestJobFirst( generator.generate());
					sjf.run();
					totalResponseTime += sjf.avgResponseTime;
					totalWaitingTime += sjf.avgWaitingTime;
					totalTurnaroundTime += sjf.avgTurnAroundTime;
					totalthroughput += sjf.throughput;
					generator.clear();
				}
				
				
			}
				 System.out.println("===============================================");
				 System.out.println("===============================================");
				 System.out.println("===============================================");
		    	System.out.println( "After 5 runs,");
		    	 System.out.println("===============================================");
		         System.out.println("Avg waiting time:"+totalWaitingTime/5);
		         System.out.println("===============================================");
		         System.out.println("Avg turn around time:"+totalTurnaroundTime/5);
		         System.out.println("===============================================");
		         System.out.println("Avg response time:" + totalResponseTime/5);
		         System.out.println("===============================================");
		         System.out.println("throughput : " + totalthroughput/5 +" processes per time unit ");
			
		}
		else 
		{
			System.out.println("NO such algorithms, enter again.");
		}
		sc.close();
		sc2.close();
		sc3.close();
	}

}
