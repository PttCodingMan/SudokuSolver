package structure;

public class Line implements Parent{
	private Cell Cells[];
	private boolean isColumnMode;
	private ContradictionStatus Status;
	public Line(ContradictionStatus inputStatus){
		Status = inputStatus;
	}
	public void setValue(Cell inputCells[], boolean inputIsColumnMode){
		Cells = inputCells;
		isColumnMode = inputIsColumnMode;
		
		for(int i = 0 ; i < 9 ; i++ ){
			Cells[i].setParent(this);
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
	public void check(int intputValue) {
		
	}
}
