package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		newAttributeList = new ArrayList<Attribute>();
		Tuple tmp = child.next();
		while(tmp != null){
			for(Attribute a : tmp.getAttributeList()){
				if(a.attributeName.equals(attributePredicate)){
					newAttributeList.add(a);
					tmp = new Tuple(newAttributeList);
					return tmp;
				}
			}
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