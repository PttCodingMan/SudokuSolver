package structure;

public class SudokuMap implements Parent{
	private Cell Cells[];
	private Line Rows[];
	private Line Columns[];
	private Jiugongge Jiugongges[][];
	private boolean isEnable;
	private ContradictionStatus Status;
	public SudokuMap(String inputData){
		isEnable = false;
		Status = new ContradictionStatus();
		while(inputData.contains("  ")) inputData = inputData.replace("  ", " ");
		
		String data[] = inputData.split(" ");
		if(data.length != 81){
			System.out.println("Data length is " + data.length + " not 81");
			return ;
		}
		for(int i = 0 ; i < 81 ; i++ ){
			try{
				if( -1 > Integer.parseInt(data[i]) || Integer.parseInt(data[i]) > 9){
					System.out.println("the values is not between 0~9");
					return ;
				}
			}
			catch(NumberFormatException e){
				System.out.println("The values can not be converted into 0~9");
				return ;
			}
		}
		
		isEnable = true;
		
		Cells = new Cell[81];
		for(int i = 0 ; i < 81 ; i++ ){
			Cells[i] = new Cell(Status);
			Cells[i].setValue(Integer.parseInt(data[i]));
		}
		
		Rows = new Line[9];
		for(int y = 0 ; y < 9 ; y++ ){
			Cell temp[] = new Cell[9];
			for(int x = 0 ; x < 9 ; x++){
				temp[x] = Cells[ 9 * y + x];
			}
			Rows[y] = new Line(temp, false);
		}
		
		Columns = new Line[9];
		for(int x = 0 ; x < 9 ; x++ ){
			Cell temp[] = new Cell[9];
			for(int y = 0 ; y < 9 ; y++){
				temp[y] = Cells[ 9 * y + x];
			}
			Columns[x] = new Line(temp, true);
		}
		
		Jiugongges = new Jiugongge[3][3];
		
		Cell CellTemp [][][] = null;
		
		for(int i = 0 ; i < 9 ; i++ ){
			if(i % 3 == 0) CellTemp = new Cell[3][3][3];
			
			for(int t = 0 ; t < 3 ; t++){
				CellTemp[t][i % 3] = Rows[i].getPart(t);
			}
			
			if(i % 3 == 2 ){
				for(int t = 0 ; t < 3 ; t++){
					Jiugongges[ i / 3 ][t] = new Jiugongge(CellTemp[t]);
				}
			}
		}
		
//		Cells[25].setValue(1);
//		
//		for(int y = 0 ; y < 3 ; y++){
//			for(int x = 0 ; x < 3 ; x++){
//				Jiugongges[y][x].show();
//				System.out.println();
//			}
//		}
//		for(int i = 0 ; i < 9 ; i++){
//			Rows[i].show();
//		}
	}
	public boolean isEnable(){
		return isEnable;
	}
	public void show(){
		//String line = " - - - - - - - - - - - - - - -";
		String line = "";
		String part = " ";
		
		System.out.println(line);
		for(int y = 0 ; y < 9 ; y++){
			System.out.print(part);
			for(int x = 0 ; x < 9 ; x++){
				System.out.print(" " + Cells[9 * y + x].toString() + " ");
				if(x % 3 == 2) System.out.print(part);
			}
			System.out.println();
			if(y % 3 == 2) System.out.println(line);
		}
	}
	@Override
	public void check() {
		if(Status.getStatus() == true) return;
	}
}
