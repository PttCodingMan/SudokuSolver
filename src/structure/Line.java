package structure;

import java.util.HashSet;
import java.util.Iterator;

public class Line implements updateParent, checkParent{
	private Cell Cells[];
	private boolean isColumnMode;
	private MapStatus Status;
	protected Line(MapStatus inputStatus){
		Status = inputStatus;
	}
	protected void setValue(Cell inputCells[], boolean inputIsColumnMode){
		Cells = inputCells;
		isColumnMode = inputIsColumnMode;
		
		for(int i = 0 ; i < 9 ; i++ ){
			Cells[i].setUpdateParent(this);
		}
	}
	protected void show(){
		for(int i = 0 ; i < 9 ; i++ ){
			System.out.print(" " + Cells[i].toString());
			if(isColumnMode) System.out.print("\n");  
		}
		System.out.print("\n");
	}
	protected Cell[] getPart(int inputPart){ 
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
	public boolean check() {
		
		if(Status.getWrongStatus()) return false;
		
		boolean isChanged = false;
		
		//Start of checking repeat
		
		HashSet<Integer> CheckList = new HashSet<Integer>();
		for(int i = 1 ; i <= 9 ; i++ ) CheckList.add(i);
		
		for(int i = 0 ; i < 9 ; i++ ){
			
			int ValueTemp = Cells[i].getValue();
			
			if(ValueTemp == 0) continue;
			
			if(CheckList.contains(ValueTemp)){
				CheckList.remove(ValueTemp);
			}
			else{
				Status.setWrongStatus(ErrorCode.ValueRepeat);
				return false;
			}
			
		}
		
		//End of checking repeat
		
		//Start of checking the only one in group
		int CheckValueArray[] = new int[10];
		
		for(int i = 0 ; i < 10 ; i++){
			CheckValueArray[i] = 0;
		}
		
		for(int i = 0 ; i < 9 ; i++ ){
			
			int CellValueTemp = Cells[i].getValue();
			
			if(CellValueTemp == 0){
				Iterator<Integer> NumberIterator = Cells[i].getCandidateNumberSet().iterator();
				while(NumberIterator.hasNext()){
					
					int tmp = NumberIterator.next();
					CheckValueArray[tmp]++;
					
				}
			}
		}
		
		for(int i = 1 ; i < 10 ; i++ ){
			
			if(CheckValueArray[i] == 1){
				
				for(int t = 0 ; t < 9 ; t++ ){
					if(Cells[t].getCandidateNumberSet().contains(i)){
						
						Cells[t].setValue(i, true);
						isChanged = true;
						break;
					}
				}
				
			}
		}
		//End of checking the only one in group
		
		return isChanged;
	}
	@Override
	public void update(int inputValue) {
		
		for(int i = 0 ; i < 9 ; i++ ){
			
			Cells[i].removeCandidateNumber(inputValue);
			
		}
		
	}
}
