package structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Line implements updateParent, checkParent{
	private Cell Cells[];
	private boolean isColumnMode;
	private MapStatus Status;
	public Line(MapStatus inputStatus){
		Status = inputStatus;
	}
	public void setValue(Cell inputCells[], boolean inputIsColumnMode){
		Cells = inputCells;
		isColumnMode = inputIsColumnMode;
		
		for(int i = 0 ; i < 9 ; i++ ){
			Cells[i].setUpdateParent(this);
		}
	}
	public void show(){
		for(int i = 0 ; i < 9 ; i++ ){
			System.out.print(" " + Cells[i].toString());
			if(isColumnMode) System.out.print("\n");  
		}
		System.out.print("\n");
	}
	public Cell[] getPart(int inputPart){ 
		//0 TOP 3 
		//1 min 3
		//2 Last 3
		if( 0 > inputPart || inputPart >2) return null;
		Cell result[] = new Cell[3];
		for(int i = inputPart * 3 ; i < inputPart * 3 + 3 ; i++){
			result[i - inputPart * 3] = Cells[i];
		}
		return result;
	}
	@Override
	public void check(int inputValue) {
		
		if(Status.getFinishStatus()) return;
		
		//Start of checking repeat
		
		Set<Integer> CheckList = new HashSet<Integer>();
		for(int i = 0 ; i < 9 ; i++ ) CheckList.add(i+1);
		
		for(int i = 0 ; i < 9 ; i++ ){
			
			if(Cells[i].getValue() == 0) continue;
			
			if(CheckList.contains(Cells[i].getValue())){
				CheckList.remove(Cells[i].getValue());
			}
			else{
				Status.setFinishStatus(true);
				return;
			}
			
		}
		
		//End of checking repeat
		
		//Start of checking the only one in group
		int CheckArray[] = new int[10];
		
		for(int i = 0 ; i < 10 ; i++){
			CheckArray[i] = 0;
		}
		
		for(int i = 0 ; i < 9 ; i++ ){
			
			int CellValueTemp = Cells[i].getValue();
			
			if(CellValueTemp == 0){
				Iterator<Integer> NumberIterator = Cells[i].getCandidateNumberSet().iterator();
				while(NumberIterator.hasNext()){
					int tmp = NumberIterator.next();
					CheckArray[tmp]++;
				}
			}
			else {
				CheckArray[CellValueTemp]++;
			}
		}
		
		for(int i = 1 ; i < 10 ; i++ ){
			
			if(Status.getFinishStatus()) return;
			else if(CheckArray[i] == 1){
				
				for(int t = 0 ; t < 9 ; t++ ){
					if(Cells[t].getCandidateNumberSet().contains(i)){
						
						Cells[t].setValue(i);
						
						break;
					}
				}
				
			}
		}
		//End of checking the only one in group
		
	}
	@Override
	public void update(int inputValue) {
		
		for(int i = 0 ; i < 9 ; i++ ){
			
			if(Status.getFinishStatus() == true) return;
			Cells[i].removeCandidateNumber(inputValue);
			
		}
		
	}
}
