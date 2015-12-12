package structure;

public class Jiugongge implements Parent{
	private Cell Cells[][];
	public Jiugongge(Cell inputCells[][]){
		Cells = inputCells;
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				Cells[y][x].setParent(this);
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
	public void check() {
		// TODO Auto-generated method stub
		
	}
}
