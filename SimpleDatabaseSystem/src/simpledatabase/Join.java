package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;
	ArrayList<Tuple> left = new ArrayList<Tuple>();

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		Tuple tmp1;

		while(true){
			tmp1 = leftChild.next();
			if( tmp1 == null ){
				break;
			}
			left.add(tmp1);
		}
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple tmp1;
		Tuple tmp2 = rightChild.next();
		boolean willJoin = false;
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();

		if(tmp2 == null){
			return null;
		}
		int i, j, k;
		i = j = k = 0;
		tmp1 = left.get(k);
		while( (i < tmp1.getAttributeList().size() || j < tmp2.getAttributeList().size()) && (k < left.size()) ){
			tmp1 = left.get(k);
			if( tmp1.getAttributeName(i).equals(tmp2.getAttributeName(j)) ){
					if( tmp1.getAttributeValue(i).equals(tmp2.getAttributeValue(j))){
						willJoin = true;
						break;
					}
					else{
						i = j = 0;
						k++;
						continue;
					}
			}
			j += 1;
			if( j >= tmp2.attributeList.size() ){
				j = 0;
				i += 1;
			}
		}
		if( willJoin ){
			for(Attribute a1 : tmp1.getAttributeList()){
				attrList.add(a1);
			}
			
			for(Attribute a2 : tmp2.getAttributeList()){
				for(Attribute a1 : tmp1.getAttributeList()){
					if(a1.getAttributeName().equals(a2.getAttributeName())){
						continue;
					}
				}
				attrList.add(a2);
			}
			return new Tuple(attrList);
		}
		return null;
		
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}