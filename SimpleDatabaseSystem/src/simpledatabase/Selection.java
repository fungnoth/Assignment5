package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator implements Cloneable{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		if(!child.from.equals(whereTablePredicate)){
			return child.next();
		}
		Tuple tmp = child.next();
		while(tmp != null){
			for (int i = 0; i < tmp.getAttributeList().size(); i++){
				if(tmp.getAttributeName(i).equals(whereAttributePredicate)){
					if(tmp.getAttributeValue(i).equals(whereValuePredicate)){
						return tmp;
					}
				}
			}
			tmp = child.next();
		}
		return tmp;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}
	

	
}