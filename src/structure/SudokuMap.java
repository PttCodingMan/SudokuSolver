package structure;

public class SudokuMap implements checkParent,Runnable{
	private Cell Cells[][];
	private Line Rows[];
	private Line Columns[];
	private Jiugongge Jiugongges[][];
	private boolean isEnable;
	private MapStatus Status;
	public SudokuMap(String inputData){
		isEnable = false;
		Status = new MapStatus();
		while(inputData.contains("  ")) inputData = inputData.replaceAll("  ", " ");
		while(inputData.contains("\n")) inputData = inputData.replaceAll("\n", "");
		while(inputData.contains("\r")) inputData = inputData.replaceAll("\r", "");
		
		String data[] = inputData.split(" ");
		if(data.length == 81){
			
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
			
		}
		else if(data.length == 1){
			
			data = inputData.split("");

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
			
		}
		else{
			System.out.println("the input value can't translate to SudokuMap");
			return;
		}
		
		isEnable = true;
		
		Cells = new Cell[9][9];
		for(int y = 0 ; y < 9 ; y++ ){
			for(int x = 0 ; x < 9 ; x++ ){
				Cells[y][x] = new Cell(Status);
				Cells[y][x].setCheckParent(this);
			}
		}
		
		Rows = new Line[9];
		for(int y = 0 ; y < 9 ; y++ ){
			Cell temp[] = new Cell[9];
			for(int x = 0 ; x < 9 ; x++){
				temp[x] = Cells[y][x];
			}
			Rows[y] = new Line(Status);
			Rows[y].setValue(temp, false);
		}
		
		Columns = new Line[9];
		for(int x = 0 ; x < 9 ; x++ ){
			Cell temp[] = new Cell[9];
			for(int y = 0 ; y < 9 ; y++){
				temp[y] = Cells[y][x];
			}
			Columns[x] = new Line(Status);
			Columns[x].setValue(temp, true);
		}
		
		Jiugongges = new Jiugongge[3][3];
		
		Cell CellTemp [][][] = null;
		
		for(int i = 0 ; i < 9 ; i++ ){
			if(i % 3 == 0) CellTemp = new Cell[3][3][3];
			
			for(int t = 0 ; t < 3 ; t++){
				CellTemp[t][i % 3] = Rows[i].getPart(t);
			}
			
			if(i % 3 == 2 ){
				for(int t = 0 ; t < 3 ; t++){//
					Jiugongges[ i / 3 ][t] = new Jiugongge(Status);
					Jiugongges[ i / 3 ][t].setValue(CellTemp[t]);
				}
			}
		}
		
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++ ){
				
				if(Status.getFinishStatus() == true) {
					isEnable = !Status.getFinishStatus();
					return;
				}
				
				Cells[y][x].setValue(Integer.parseInt(data[ 9 * y + x]));
			}
		}	
		
		//System.out.println("isEnable = " + isEnable + " Status.getStatus() = " + Status.getStatus());
		Cells[4][4].setValue(1);
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				Jiugongges[y][x].show();
				System.out.println();
			}
		}
		for(int i = 0 ; i < 9 ; i++){
			Rows[i].show();
		}
		for(int i = 0 ; i < 9 ; i++){
			Columns[i].show();
		}
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
				if(Cells != null) System.out.print(" " + Cells[y][x].toString() + " ");
				else System.out.print("   ");
				if(x % 3 == 2) System.out.print(part);
			}
			System.out.println();
			if(y % 3 == 2) System.out.println(line);
		}
	}
	@Override
	public void check(int inputValue) {
		if(Status.getFinishStatus() == true) return;
		
		for(int i = 0 ; i < 9 ; i++){
			if(Status.getFinishStatus() == true) return;
			Rows[i].check(inputValue);
		}
		
		for(int i = 0 ; i < 9 ; i++){
			if(Status.getFinishStatus() == true) return;
			Columns[i].check(inputValue);
		}
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				if(Status.getFinishStatus() == true) return;
				Jiugongges[y][x].check(inputValue);
			}
		}
	}
	public void calculate(boolean useThread){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
