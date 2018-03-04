import java.util.LinkedList;
import java.util.*;

public class Main {

	
	public String[] limitsCompetition ; 
	public String[] limitsStart;
	public String[] limitsCheckpoint;
	public int noBoats;
	public Boats boats ;
	
	

	public void readFromFile(File fp) {  //a separate method to read all data 
		
		fp.open('r');

		String line = fp.readLine();

		limitsCompetition = line.split(" ");

		line = fp.readLine();

		limitsStart = line.split(" ");

		line = fp.readLine();

		limitsCheckpoint = line.split(" ");

		
		line = fp.readLine();

		noBoats = Integer.parseInt(line);
		
		int m = Integer.parseInt(limitsCompetition[0]);
		int n = Integer.parseInt(limitsStart[0]);

		

		boats  = new Boats(noBoats);

		int i,j,k,c; // contors

		i = 0;
		

		while(i < noBoats) {
			j = 0;

			line = fp.readLine();

			String[] delimiter = line.split(" ");

			Float d2 = Float.parseFloat(delimiter[2]);
			Integer d3 = Integer.parseInt(delimiter[3]);
			
			boats.boats[i] = new Boat(delimiter[0], delimiter[1],d2, d3);

			int noMembersInBoat = Integer.parseInt(delimiter[3]);
	
			 
			boats.boats[i].crew = new Crew(noMembersInBoat);

			for(k = 0 ; k < noMembersInBoat ; k ++) {
 
				line = fp.readLine();

				delimiter = line.split(" ");

				Integer d5 = Integer.parseInt(delimiter[3]);
				Integer d4 = Integer.parseInt(delimiter[4]);
	
				boats.boats[i].crew.team[k] = new Member(delimiter[0], delimiter[1] , delimiter[2], d5, d4);
				}

			i++;

			}

		
		
		 i = 0; j=0;

		boats.boats[i].moves = new Moves();

		while(line != null) {

			line = fp.readLine();

			if(line != null) {

				String[] delimiter = line.split(" ");
					
				if(delimiter[0].equals(boats.boats[i].ID)) {
						
					Integer d3 = Integer.parseInt(delimiter[1]);
					Integer d4 = Integer.parseInt(delimiter[2]);
					Integer d5 = Integer.parseInt(delimiter[3]);
	
					boats.boats[i].moves.moves[j] = new Move(delimiter[0], d3, d4, d5);
					boats.boats[i].moves.noMoves  = boats.boats[i].moves.noMoves + 1 ; 
						
					j++;
				}

				if(! delimiter[0].equals(boats.boats[i].ID)) {
				
					i++;
					boats.boats[i].moves = new Moves();
					j = 0;
				}
				

			}

		}

		
		fp.close();


	}




	public int outsideCompetitionArea(Main g , int[] v) { // g - instance of class Main where the data is stored
							      // v - array of int , a position is 1 if the boat went outside the Competition Area
							      // the position is 0 if the boat never went outside
							      // the position i in v signifies the i boat from g		
	int noBoatsOutsideCompetitionArea = 0 ; 
	
	int k = 0 ;

	for(int i = 0 ; i < g.noBoats ; i ++) {

		for(int j = 0 ; j < g.boats.boats[i].moves.noMoves ; j ++) {

			if((g.boats.boats[i].moves.moves[j].boatX < Integer.parseInt(g.limitsCompetition[0])) | 
				(g.boats.boats[i].moves.moves[j].boatX > Integer.parseInt(g.limitsCompetition[2])) | 					(g.boats.boats[i].moves.moves[j].boatY < Integer.parseInt(g.limitsCompetition[1])) |
				(g.boats.boats[i].moves.moves[j].boatY > Integer.parseInt(g.limitsCompetition[3])) )

					{

					noBoatsOutsideCompetitionArea++; 
					k++;
					j = g.boats.boats[i].moves.noMoves ; 
					v[i] = 1; 

					}
		}		

	}

	return noBoatsOutsideCompetitionArea;
	}



	public int notIntheCheckpointArea(Main g , int[] v) { // g - instance of class Main where the data is stored
							      // v - array of int , a position is 1 if the boat went into the Checkpoint Area
							      // the position is 0 if the boat never went into the Checkpoint
							      // the position i in v signifies the i boat from g	

	int noBoatsNotInTheCheckpointArea = 0 ; 
	int noBoatsInTheCheckpointArea = 0 ;
	
	int k = 0 ;

	for(int i = 0 ; i < g.noBoats ; i ++) {

		for(int j = 0 ; j < g.boats.boats[i].moves.noMoves ; j ++) {

				if((g.boats.boats[i].moves.moves[j].boatX >= Integer.parseInt(g.limitsCheckpoint[0])) & 
				(g.boats.boats[i].moves.moves[j].boatX <= Integer.parseInt(g.limitsCheckpoint[2])) & 					(g.boats.boats[i].moves.moves[j].boatY >= Integer.parseInt(g.limitsCheckpoint[1])) &
				(g.boats.boats[i].moves.moves[j].boatY <= Integer.parseInt(g.limitsCheckpoint[3])) )

					{

					noBoatsInTheCheckpointArea++;
					k++;
					j = g.boats.boats[i].moves.noMoves ;
					v[i] = 1; 

					}
		}		

	}

	return (g.noBoats - noBoatsInTheCheckpointArea);
	}




	public int notIntheFinishArea(Main g , int[] v) { // g - instance of class Main where the data is stored
							      // v - array of int , a position is 1 if the boat arrived at the Finish
							      // the position is 0 if the boat never got to the Finish
							      // the position i in v signifies the i boat from g	

	int noBoatsNotInTheFinishArea = 0 ; 
	int noBoatsInTheFinishArea = 0 ;
	
	int k = 0 ;

	for(int i = 0 ; i < g.noBoats ; i ++) {
		for(int j = 1 ; j < g.boats.boats[i].moves.noMoves ; j ++) {
				if((g.boats.boats[i].moves.moves[j].boatX >= Integer.parseInt(g.limitsStart[0])) & 
				(g.boats.boats[i].moves.moves[j].boatX <= Integer.parseInt(g.limitsStart[2])) & 					(g.boats.boats[i].moves.moves[j].boatY >= Integer.parseInt(g.limitsStart[1])) &
				(g.boats.boats[i].moves.moves[j].boatY <= Integer.parseInt(g.limitsStart[3])) )
					{
					noBoatsInTheFinishArea++;
					k++;
					j = g.boats.boats[i].moves.noMoves ;
					v[i] = 1; 
					}	
		}		

	}

	return (g.noBoats - noBoatsInTheFinishArea);
	}



	public static void main(String[] args) {
		Main g = new Main();

		

		//I will consider that the args[0] is the filename
		String filename = args[0];
		StringBuffer filein = new StringBuffer();
		filein = filein.append(filename);
		filein = filein.append(".in");	

		String fileIn = new String(filein);

		StringBuffer fileout = new StringBuffer();
		fileout = fileout.append(filename);
		fileout = fileout.append(".out");

		String fileOut = new String(fileout);	
		

		File fpr = new File(fileIn);
		g.readFromFile(fpr);
		
		int[] v = new int[g.noBoats]; // used for g.notIntheFinishArea( g ,  v);
		int[] w = new int[g.noBoats]; // used for g.notIntheCheckpointArea( g , w );
		int[] u = new int[g.noBoats]; // used for g.outsideCompetitionArea( g , u);

		for(int j = 0 ; j < g.noBoats ;j ++)
			{
			v[j] = 0;
			}

		
		File fp = new File(fileOut);
		fp.open('w');

		//Task 1 - “Number of boats outside the competition area: ”
		int o = g.outsideCompetitionArea( g , u);
		fp.writeLine("Number of boats outside the competition area: " + o);


		//Task 2 - “Number of boats that didn’t enter the finish area: ”
		int f = g.notIntheFinishArea( g ,  v);
		fp.writeLine("Number of boats that didn’t enter the finish area: " + f);


		//Task 3 - “Number of boats that didn’t enter the checkpoint area: ”
		int k = g.notIntheCheckpointArea( g , w );
		fp.writeLine("Number of boats that didn’t enter the checkpoint area: " + k);
		
		
		
		//Task 4 - “Boats that didn’t finish the race: ”
		int noFinish = 0 ;

		int[] fin = new int[g.noBoats];
		for(int j = 0 ; j < g.noBoats ;j ++)
			{

			if(u[j] == 1 || v[j] == 0) // I considered that a boat does not finish the race if
						   // it either went outside at least once or if it did not arrive at Finish
				{
				fin[j] = 1;       //the boat did not finish
				noFinish++;				
				}
			else
				{
				fin[j] = 0;       //the boat finished
				}
			}

			

		Boats boats  = new Boats(noFinish);

		int nr = 0;	

		for(int j = 0 ; j < g.noBoats ;j ++)
		{
			if(fin[j] == 1)	
				{
				boats.boats[nr] = g.boats.boats[j]; //I place the boat in a separate array of Boats
				nr++;
				}	
		}
			
		Sorter sort = new Sorter(boats.boats);
		sort.sortAscName(); //I sort the boats by name 
		StringBuffer s = new StringBuffer();

		for(int j = 0 ; j < noFinish ; j ++)
			{
			s = s.append(" ");
			s = s.append(boats.boats[j].name);
			if(j != noFinish - 1) { s = s.append(",");}
			}


		

		fp.writeLine("Boats that didn’t finish the race:" + s);


		//Task 5 - “Most experienced captains: ”
		
		Member[] members1 = new Member[g.noBoats];

		nr = 0;

		for(int j = 0 ; j < g.noBoats ; j++ )
			{
			for(int i = 0 ; i < g.boats.boats[j].noCrewMembers; i ++)
				{
				if(g.boats.boats[j].crew.team[i].captainCheck == 1 && g.boats.boats[j].crew.team[i].yearsOfSailingExp > 2)
					{
					members1[nr] = g.boats.boats[j].crew.team[i];
					nr++; //in the end , nr will contain the number of captains with at least 2 years of experience
					}
				}
			}
	
		Member[] members = new Member[nr];

		for(int j = 0 ; j < nr ; j ++) {

			members[j] = members1[j];

			}	

		SorterMember sorte = new SorterMember(members);
		sorte.sortDescYears();

		StringBuffer se = new StringBuffer();



		for(int j = 0 ; j < nr ; j ++)
			{

			se = se.append(" ");
			se = se.append(members[j].firstName);
			se = se.append(" ");
			se = se.append(members[j].lastName);
			se = se.append(" (");
			se = se.append(members[j].yearsOfSailingExp);
			se = se.append(" years)");
			if(j != nr -1) {se = se.append(",");}

			}

		fp.writeLine("Most experienced captains:" + se);


		//Task 6 - “Partial standings: ”

		int noPartialStanding = 0 ;
		int[] partial = new int[g.noBoats];

		for(int j = 0 ; j < g.noBoats ;j ++)
			{

			if(u[j] == 0 && w[j] == 1 && v[j] == 1)    // to be among the ones that are displayed in the standings , 
								   // I considered that the boat had to have stayed inside the Competition Area
								   // to have gone through the checkpoint and to have arrived at the finish
				{

				partial[j] = 1;
				noPartialStanding++;
				
				}
			else
				{
				partial[j] = 0;
				}

			}

			

		Boats boatsPartial  = new Boats(noPartialStanding);

		 nr = 0;	

		for(int j = 0 ; j < g.noBoats ;j ++)
		{
			if(partial[j] == 1)	
				{

				boatsPartial.boats[nr] = g.boats.boats[j]; //I grouped the boats in an array of boats
				nr++;

				}	
		}

		Sorter sorty = new Sorter(boatsPartial.boats); //and sorted it by the offset
		sorty.sortAscOffset();

		StringBuffer ss = new StringBuffer();

		for(int j = 0 ; j < noPartialStanding ; j ++)
			{

			ss = ss.append(" ");
			ss = ss.append(boatsPartial.boats[j].name);	
			ss = ss.append(" (");

			//part of code where I interchange Integers and Floats
			Integer in = boatsPartial.boats[j].moves.moves[boatsPartial.boats[j].moves.noMoves-1].offset;
			in = in / 60; // i turned the offset from seconds to minutes
			ss = ss.append(in);
			float ff;
			ff = (in.floatValue() * boatsPartial.boats[j].handicap) + in.floatValue() ;
			ff = ff + 0.5f;   // the values differ , for some floats it gets rounded and for some it doesn't
					  // eg: test5.out : Escape with an offset of 31 and handicap of 0.32 gives 40.92 which goes to 41
					  // Whisper with an offset of 28 and handicap of 0.11 gives 31.08 which goes to 31
					  // by making ff = ff + 0.5f i tried to get to that approximation
			int a = (int)ff;
			Integer Aa  = new Integer(a);
			boatsPartial.boats[j].moves.moves[boatsPartial.boats[j].moves.noMoves-1].offset = Aa;

			ss = ss.append(" minutes)");
			
			if(j != noPartialStanding - 1) { ss = ss.append(",");}

			}


		

		fp.writeLine("Partial standings:" + ss);

		//Task 7 -  “Final standings: ”

		Sorter sortyt = new Sorter(boatsPartial.boats);
		sortyt.sortAscOffset();
		StringBuffer ses = new StringBuffer();
		for(int j = 0 ; j < noPartialStanding ; j ++)
			{

			ses = ses.append(" ");
			ses = ses.append(boatsPartial.boats[j].name);	
			ses = ses.append(" (");
			Integer in = boatsPartial.boats[j].moves.moves[boatsPartial.boats[j].moves.noMoves-1].offset;
			
			ses = ses.append(in);
			
			ses = ses.append(" minutes / ");
			ses = ses.append(boatsPartial.boats[j].handicap);
			ses = ses.append(")");
			
			if(j != noPartialStanding - 1) { ses = ses.append(",");}

			}


		

		fp.writeLine("Final standings:" + ses);


		fp.close();

		
	}

}
