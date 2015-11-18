package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	private boolean sorted = false;
	int orderAttrI;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple t;
		if(!sorted){
			while(true){
				t = child.next();
				if( t == null ){
					break;
				}
				tuplesResult.add(t);
			}
			orderAttrI = 0;
			for(Attribute a : tuplesResult.get(0).getAttributeList()){
				if(a.getAttributeName().equals(orderPredicate) ){
					break;
				}
				orderAttrI += 1;
			}
			
			Collections.sort( this.tuplesResult, new Comparator<Tuple>(){
				@Override
				public int compare(Tuple tuples, Tuple otherTuples) {
					return (String.valueOf(tuples.getAttributeValue(orderAttrI))).compareTo(String.valueOf(otherTuples.getAttributeValue(orderAttrI)));
				}
			} );
			sorted = true;
		}
		
		if(!this.tuplesResult.isEmpty()){
			return tuplesResult.remove(0);
		}
		return null;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}