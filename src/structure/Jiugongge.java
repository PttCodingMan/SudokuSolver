package structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Jiugongge implements updateParent, checkParent{
	private Cell Cells[][];
	private MapStatus Status;
	public Jiugongge(MapStatus inputStatus){
		
		Status = inputStatus;
		
	}
	public void setValue(Cell inputCells[][]){
		
		Cells = inputCells;
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				Cells[y][x].setUpdateParent(this);
			}
		}
		
	}
	public void show(){
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				System.out.print(" " + Cells[y][x].toString());
			}
			System.out.println();
		}
	}
	@Override
	public void check(int inputValue) {
		
		//Start of checking repeat
		Set<Integer> CheckList = new HashSet<Integer>();
		for(int i = 0 ; i < 9 ; i++ ) CheckList.add(i+1);
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				
				if(Cells[y][x].getValue() == 0) continue;
				
				if(CheckList.contains(Cells[y][x].getValue())){
					CheckList.remove(Cells[y][x].getValue());
				}
				else{
					Status.setWrongStatus(true);
					return;
				}
				
			}
		}
		//End of checking repeat
		
		//Start of checking the only one choice
		int CheckArray[] = new int[10];
		
		for(int i = 0 ; i < 10 ; i++){
			CheckArray[i] = 0;
		}
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				
				int CellValueTemp = Cells[y][x].getValue();
				
				if(CellValueTemp == 0){
					Iterator<Integer> NumberIterator = Cells[y][x].getCandidateNumberSet().iterator();
					while(NumberIterator.hasNext()){
						int tmp = NumberIterator.next();
						CheckArray[tmp]++;
					}
				}
				else {
					CheckArray[CellValueTemp]++;
				}
				
			}
		}
		
		//End of checking the only one choice
		
	}
	@Override
	public void update(int inputValue) {
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				
				if(Status.getWrongStatus() == true) return;
				Cells[y][x].removeCandidateNumber(inputValue);
				
			}
		}
		
	}
}
