package structure;

import java.util.HashSet;
import java.util.Set;

public class Cell {
	private Set<Integer> CandidateNumbers;
	private int Value;
	private Parent Parent[];
	private ContradictionStatus Status;
	
	static int DafaultNullNumber = 0; 
//	public Cell(){
//		CandidateNumbers = new HashSet<Integer>();
//		for(int i = 0 ; i < 9 ; i++ ) CandidateNumbers.add(i+1);
//		Value = DafaultNullNumber;
//	}
	public Cell(ContradictionStatus inputStatus){
		CandidateNumbers = new HashSet<Integer>();
		for(int i = 0 ; i < 9 ; i++ ) CandidateNumbers.add(i+1);

//		Value = inputValue;
//		CandidateNumbers.remove(inputValue);
		
		Parent = new Parent[3];
		
		Status = inputStatus;
	}
	public void setParent(Parent inputParent){
		for(int i = 0 ; i < 3 ; i++){
			if(Parent[i] == null){
				Parent[i] = inputParent;
				return;
			}
		}
	}
	public Set<Integer> getCandidateNumberSet(){
		return CandidateNumbers;
	}
	public boolean containsCandidateNumber(int input){
		return CandidateNumbers.contains(input);
	}
	public void setValue(int inputValue){
		
		if(Value != DafaultNullNumber) CandidateNumbers.add(Value);
		CandidateNumbers.remove(inputValue);
		
		Value = inputValue;
		
		if(CandidateNumbers.size() == 0){
			Status.setSatus(true);
			return;
		}
		
		for(int i = 0 ; i < 3 ; i++){
			if(Status.getStatus() == true) return;
			if(Parent[i] != null) Parent[i].check();
		}
	}
	public int getValue(){
		return Value;
	}
	public void resetValue(){
		Value = DafaultNullNumber;
	}
	public String toString(){
		if(Value != DafaultNullNumber) return "" + Value;
		return " ";
	}
}
