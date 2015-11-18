package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute = false;
	private Tuple tuple;
	String attributeLine;
	String dataTypeLine;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		String valueLine;
		//Delete the lines below and add your code here
		try {
			if(!getAttribute){
				this.attributeLine = br.readLine();
				this.dataTypeLine = br.readLine();
				valueLine = br.readLine();
				
				tuple = new Tuple(attributeLine, dataTypeLine, valueLine);
				getAttribute = true;
			} else {
				valueLine = br.readLine();
				if(valueLine == null){
					return null;
				}
				tuple = new Tuple(attributeLine, dataTypeLine, valueLine);
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuple;
	}
	

	private Iterator Iterator() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
	public void main(String[] args){
		
	}
}