package structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {
	private Set<Integer> CandidateNumbers;
	private int Value;
	private List<updateParent> UpdateParent;
	private checkParent CheckParent;
	private MapStatus Status;
	
	static int DafaultNullNumber = 0; 
	public Cell(MapStatus inputStatus){
		CandidateNumbers = new HashSet<Integer>();
		for(int i = 0 ; i < 9 ; i++ ) CandidateNumbers.add(i+1);
		
		UpdateParent = new ArrayList<updateParent>();
		
		Status = inputStatus;
	}
	public void setUpdateParent(updateParent inputParent){
		UpdateParent.add(inputParent);
		if(UpdateParent.size() > 3){
			System.out.println("Parent Size > 3 !");
			Status.setFinishStatus(true);
			return;
		}
	}
	
	public void setCheckParent(checkParent inputParent){
		CheckParent = inputParent;
	}
	
	public Set<Integer> getCandidateNumberSet(){
		return CandidateNumbers;
	}
	public boolean containsCandidateNumber(int input){
		return CandidateNumbers.contains(input);
	}
	public void removeCandidateNumber(int inputValue){
		CandidateNumbers.remove(inputValue);
		
		
//		If the value of this cell is DafaultNullNumber
//		and the number of candidateNumbers is the only one choice then the value will be the number
//		
//		If the value of this cell is DafaultNullNumber
//		then no number could be a choice
//		It's wrong status
		
		if(Value != DafaultNullNumber) return;
		
		if(CandidateNumbers.size() == 1) Value = (int) CandidateNumbers.toArray()[0];
		else if(CandidateNumbers.size() == 0) Status.setFinishStatus(true);
		
	}
	public void setValue(int inputValue){
		
		if(Value != DafaultNullNumber) {
//			System.out.println("The value of this cell is not DafaultNullNumber!");
//			Status.setFinishStatus(true);
			return;
		}
		
		Value = inputValue;
		
		if(inputValue == DafaultNullNumber) return; 
		
		CandidateNumbers.clear();
		
		for(int i = 0 ; i < 3 ; i++){
			if(Status.getFinishStatus() == true) return;
			UpdateParent.get(i).update(inputValue);
		}
		
		CheckParent.check(inputValue);
	}
	public int getValue(){
		return Value;
	}
	public String toString(){
		if(Value != DafaultNullNumber) return "" + Value;
		return "~";
	}
}
