
public class Boat {

	
	public String ID ;
	public String name;
	public Float handicap;
	public Integer noCrewMembers;
	
	public Crew crew ;
	public Moves moves;


	public Boat(String ID, String name , Float handicap, Integer noCrewMembers)
	{
	this.ID = ID;
	this.name = name;
	this.handicap = handicap;
	this.noCrewMembers = noCrewMembers;
	}	


}
