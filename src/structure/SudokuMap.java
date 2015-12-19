package structure;

import java.util.Iterator;

public class SudokuMap implements checkParent,Runnable{
	private Cell Cells[][];
	private Line Rows[];
	private Line Columns[];
	private Jiugongge Jiugongges[][];
	private boolean isEnable;
	private MapStatus Status;
	
	private String originData[];
	
	private long time;
	
	private int LastSetY;
	private int LastSetX;
	
	public SudokuMap(SudokuMap OriginMap){
		isEnable = false;
		Status = new MapStatus();
		
		String inputData = "";
		int values[][] = OriginMap.getValues();
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++){
				inputData += values[y][x] + "";
			}
		}
		
		String data[] = processInputData(inputData);
		
		if(data == null || (!isEnable) ) return ;
		
		initStructure();
		
		originData = data;
		
		setDataToCell();
	}
	public SudokuMap(String inputData){
		isEnable = false;
		Status = new MapStatus();
		
		String data[] = processInputData(inputData);
		
		if(data == null || (!isEnable) ) return ;
		
		initStructure();
		
		originData = data;
	}
	private void setDataToCell(){
		
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++ ){
				
				if(Status.getWrongStatus() == true) {
					isEnable = false;
					return;
				}

				setCellValue(y, x, Cells[y][x], Integer.parseInt(originData[ 9 * y + x]));
			}
		}
		
		originData = null;
	}
	private String[] processInputData(String inputData){
		while(inputData.contains("  ")) inputData = inputData.replaceAll("  ", " ");
		while(inputData.contains("\n")) inputData = inputData.replaceAll("\n", "");
		while(inputData.contains("\r")) inputData = inputData.replaceAll("\r", "");
		
		String data[] = inputData.split(" ");
		if(data.length == 81){
			
			checkNumber(data);
			
		}
		else if(data.length == 1){
			
			data = inputData.split("");

			checkNumber(data);
			
		}
		else{
			System.out.println("the input value can't translate to SudokuMap");
			return null;
		}
		
		isEnable = !Status.getWrongStatus();
		
		return data;
	}
	private void checkNumber(String[] inputData){
		for(int i = 0 ; i < 81 ; i++ ){
			try{
				if( -1 > Integer.parseInt(inputData[i]) || Integer.parseInt(inputData[i]) > 9){
					System.out.println("the values is not between 0~9");
					Status.setWrongStatus(true);
					return;
				}
			}
			catch(NumberFormatException e){
				System.out.println("The values can not be converted into 0~9");
				Status.setWrongStatus(true);
				return;
			}
		}
	}
	private void initStructure(){
		
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
	}
	public int[][] getValues(){
		int result[][] = new int[9][9];
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++){
				result[y][x] = Cells[y][x].getValue();
			}
		}
		return result;
	}
	protected void setCellValue(int y, int x, int inputValue){
		setCellValue(y, x, Cells[y][x], inputValue);
	}
	private void setCellValue(int y, int x, Cell inputCell, int inputValue){
		LastSetY = y;
		LastSetX = x;
		inputCell.setValue(inputValue);
	}
	public static SudokuMap copyMap(SudokuMap becopyMap){
		
		SudokuMap result = new SudokuMap(becopyMap);
		return result;
		
	}
	public boolean isEnable(){
		return isEnable && (!Status.getWrongStatus());
	}
	public void show(){
		//String line = " - - - - - - - - - - - - - - -";
		boolean showCell = false;
		
		if(originData == null){
			showCell = true;
		}
				
		String line = "";
		String part = " ";
		
		System.out.println(line);
		for(int y = 0 ; y < 9 ; y++){
			System.out.print(part);
			for(int x = 0 ; x < 9 ; x++){
				
				String TempValue = "~";
				
				if(showCell){
					TempValue = Cells[y][x].toString();
				}
				else{
					TempValue = originData[ 9 * y + x].equals("0") ? "~" : originData[ 9 * y + x];
				}
				
				System.out.print(" " + TempValue + " ");
				if(x % 3 == 2) System.out.print(part);
			}
			System.out.println();
			if(y % 3 == 2) System.out.println(line);
		}
	}
	@Override
	public void check(int inputValue) {
		if(Status.getWrongStatus() == true) return;
		
		for(int i = 0 ; i < 9 ; i++){
			if(Status.getWrongStatus() == true) return;
			Rows[i].check(inputValue);
		}
		
		for(int i = 0 ; i < 9 ; i++){
			if(Status.getWrongStatus() == true) return;
			Columns[i].check(inputValue);
		}
		
		for(int y = 0 ; y < 3 ; y++){
			for(int x = 0 ; x < 3 ; x++){
				if(Status.getWrongStatus() == true) return;
				if((LastSetY / 3) == y || (LastSetX / 3) == x){
					Jiugongges[y][x].check(inputValue);
				}
			}
		}
	}
	private void RecursiveCalculate(MapStatus OriginState, int threadLevel){
		
		int tempX = 0, tempY = 0;
		int MinSize = Integer.MAX_VALUE;
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++){
				
				if(Cells[y][x].getValue() != 0) continue;
				
				int sizeTemp = Cells[y][x].getCandidateNumberSet().size();
				
				if(MinSize > sizeTemp){
					
					MinSize = sizeTemp;
					tempY = y;
					tempX = x;
					
				}
				
			}
		}
		
//		If MinSize == Integer.MAX_VALUE,
//		it means this SudokuMap has not find any empty cell
//		in other words, it finds an answer!
		
		if(MinSize == Integer.MAX_VALUE){
			
			OriginState.setFinishStatus(true);
			OriginState.setAnswer(this);
			return;
			
		}

		SudokuMap tempMap[] = new SudokuMap[MinSize];
		
		for(int i = 0 ; i < MinSize ; i++){
			tempMap[i] = SudokuMap.copyMap(this);
		}		
		
		Iterator<Integer> iterator = Cells[tempY][tempX].getCandidateNumberSet().iterator();
		
		int index = 0;
		while(iterator.hasNext()){
			int inputValue = iterator.next();
			
			tempMap[index].setCellValue(tempY, tempX, inputValue);
			
			if(OriginState.getFinishStatus()){
				
				return;
				
			}
			else if(tempMap[index].isEnable()){
								
				tempMap[index].RecursiveCalculate(OriginState, threadLevel - 1);
				
			}
			
			index++;
		}
	}
	public void calculate(boolean useThread){
		
		setDataToCell();
		
		if(Status.getWrongStatus()) return;
		
		int ThreadLevel = useThread ? 3 : 0;
		
		time = System.nanoTime();
		
		RecursiveCalculate(Status, ThreadLevel);
		
		time = System.nanoTime() - time;
		
		if(Status.getFinishStatus()){
			
			originData = new String[81];
			int CellTemp[][] = Status.getAnswer().getValues();
			for(int y = 0 ; y < 9 ; y++){
				for(int x = 0 ; x < 9 ; x++){
					
					originData[ 9 * y + x] = CellTemp[y][x] + "";
					
				}
			}
		}
		
	}
	public long getExecutionTime(){
		return time/1000000;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
