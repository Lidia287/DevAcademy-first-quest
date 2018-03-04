import java.util.Comparator;
import java.util.List;
import java.util.Arrays;



public class Sorter{

    
    static private Comparator<Boat> ascName;
    static private Comparator<Boat> ascOffset;

    
    static {
        ascName = new Comparator<Boat>(){
            @Override
            public int compare(Boat b1, Boat b2){
                return b1.name.compareTo(b2.name);
            }
        };

        ascOffset = new Comparator<Boat>(){
            @Override
            public int compare(Boat b1, Boat b2){
                
                return Integer.compare(b1.moves.moves[b1.moves.noMoves-1].offset,b2.moves.moves[b2.moves.noMoves-1].offset );
                
            }
        };
    }

    private Boat[] boats;
    public Boat[] getBoats(){ return boats; }

    public void sortAscName(){
        Arrays.sort(boats, ascName);
    }

    public void sortAscOffset(){
        Arrays.sort(boats, ascOffset);
    }

    public Sorter(Boat[] boats){
        this.boats = boats;
    }

}
