package structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {
	private HashSet<Integer> CandidateNumbers;
	private int Value;
	private List<updateParent> UpdateParent;
	private checkParent CheckParent;
	private MapStatus Status;
	
	private int LocationX, LocationY;
	static int DafaultNullNumber = 0; 
	protected Cell(MapStatus inputStatus, int y, int x){
		CandidateNumbers = new HashSet<Integer>();
		for(int i = 0 ; i < 9 ; i++ ) CandidateNumbers.add(i+1);
		
		UpdateParent = new ArrayList<updateParent>();
		
		Status = inputStatus;
		
		LocationX = x;
		LocationY = y;
	}
	protected void setUpdateParent(updateParent inputParent){
		UpdateParent.add(inputParent);
		if(UpdateParent.size() > 3){
			Status.setWrongStatus(ErrorCode.UpdataParentTooMany);
			return;
		}
	}
	
	protected void setCheckParent(checkParent inputParent){
		CheckParent = inputParent;
	}
	
	protected HashSet<Integer> getCandidateNumberSet(){
		
		return (HashSet<Integer>) CandidateNumbers.clone();
		
	}
	
	protected void setCandidateNumberSet(HashSet<Integer> inputCandidateNumberSet){
		
		CandidateNumbers = (HashSet<Integer>) inputCandidateNumberSet.clone();
		
	}
	
	protected boolean containsCandidateNumber(int input){
		return CandidateNumbers.contains(input);
	}
	protected void removeCandidateNumber(int inputValue){
		
		if(Value != DafaultNullNumber) return;
		if(inputValue == DafaultNullNumber) return;
		
		CandidateNumbers.remove(inputValue);
				
	}
	protected void setValue(int inputValue, boolean needUpdate){
		
		if(Value != DafaultNullNumber) {
//			System.out.println("The value of this cell is not DafaultNullNumber!");
//			System.out.println("x: " + this.LocationX + " y: " + this.LocationY + " inputValue: " + inputValue);
			return;
		}
		Value = inputValue;
		
		if(inputValue == DafaultNullNumber) return; 
		
		CandidateNumbers.clear();
		
		if(!needUpdate) return;
		
		for(int i = 0 ; i < 3 ; i++){
			//if(Status.getWrongStatus()) return;
			
			UpdateParent.get(i).update(inputValue);

		}

	}
	protected int getValue(){
		return Value;
	}
	public String toString(){
		if(Value != DafaultNullNumber) return "" + Value;
		return "~";
	}
}
