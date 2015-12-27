package structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Jiugongge implements updateParent, checkParent{
	private Cell Cells[][];
	private MapStatus Status;
	protected Jiugongge(MapStatus inputStatus){
		
		Status = inputStatus;
		
	}
	protected void setValue(Cell inputCells[][]){
		
		Cells = inputCells;
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				Cells[y][x].setUpdateParent(this);
			}
		}
		
	}
	protected void show(){
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				System.out.print(" " + Cells[y][x].toString());
			}
			System.out.println();
		}
	}
	@Override
	public boolean check() {
		
		if(Status.getWrongStatus()) return false;
		
		boolean isChanged = false;
		
		//Start of checking repeat
		Set<Integer> CheckList = new HashSet<Integer>();
		for(int i = 0 ; i < 9 ; i++ ) CheckList.add(i+1);
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				
				int ValueTemp = Cells[y][x].getValue();
				
				if(ValueTemp == 0) continue;
				
				if(CheckList.contains(ValueTemp)){
					CheckList.remove(ValueTemp);
				}
				else{
					Status.setWrongStatus(ErrorCode.ValueRepeat);
					return false;
				}
				
			}
		}
		//End of checking repeat
		
		//Start of checking the only one choice
		int CheckValueArray[] = new int[10];
		
		for(int i = 0 ; i < 10 ; i++) CheckValueArray[i] = 0;
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				
				int CellValueTemp = Cells[y][x].getValue();
				
				if(CellValueTemp == 0){
					Iterator<Integer> NumberIterator = Cells[y][x].getCandidateNumberSet().iterator();
					while(NumberIterator.hasNext()){
						
						int tmp = NumberIterator.next();
						CheckValueArray[tmp]++;
						
					}
				}
				
			}
		}
		
		for(int i = 1 ; i < 10 ; i++ ){
			
			if(CheckValueArray[i] == 1){
				
				for(int y = 0 ; y < 3 ; y++){
					for(int x = 0 ; x < 3 ; x++){
						if(Cells[y][x].containsCandidateNumber(i)){
							
							Cells[y][x].setValue(i, true);
							
							isChanged = true;
							y = 3;
							x = 3;
						}
					}
				}
				
			}
		}
		
		//End of checking the only one choice
		
		return isChanged;
	}
	@Override
	public void update(int inputValue) {
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){

				Cells[y][x].removeCandidateNumber(inputValue);
				
			}
		}
		
	}
}
