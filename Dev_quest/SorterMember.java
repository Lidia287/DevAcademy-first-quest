import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
import java.util.*;



public class SorterMember{

    
    static private Comparator<Member> ascName;
    static private Comparator<Member> descYears;

    
    static {
        ascName = new Comparator<Member>(){
            @Override
            public int compare(Member b1, Member b2){
                return b1.firstName.compareTo(b2.firstName);
            }
        };

        descYears = new Comparator<Member>(){
            @Override
            public int compare(Member b1, Member b2){
                
                return Integer.compare(b1.yearsOfSailingExp, b2.yearsOfSailingExp);
                
            }
        };
    }

    private Member[] members;
    public Member[] getMembers(){ return members; }

    public void sortAscName(){
        Arrays.sort(members, ascName);
    }

    public void sortDescYears(){
        Arrays.sort(members, Collections.reverseOrder(descYears));
    }

    public SorterMember(Member[] members){
        this.members = members;
    }

  
}
