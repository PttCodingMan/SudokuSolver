package structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SudokuMap implements checkParent,Runnable{
	private Cell Cells[][];
	private Line Rows[];
	private Line Columns[];
	private Jiugongge Jiugongges[][];
	private MapStatus Status;
	
	private String originData[];
	
	private int LastSetY;
	private int LastSetX;
	
	private SudokuMap(SudokuMap OriginMap, MapStatus inputStatus){
		Status = inputStatus;		
		
		String inputData = "";
		int values[][] = OriginMap.getValues();
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++){
				inputData += values[y][x] + "";
			}
		}
		
		String data[] = processInputData(inputData);
		
		if(data == null || Status.getWrongStatus() ) return ;
		
		initStructure();
		
		originData = data;
		
		setDataToCell(true);
		
//		HashSet<Integer> TempCandidateNumbers[][] = getCandidateNumbers();
//		setCandidateNumbers(TempCandidateNumbers);
		
	}
	public SudokuMap(String inputData){
		Status = new MapStatus();
		
		String data[] = processInputData(inputData);
		
		if(data == null || Status.getWrongStatus() ) return ;
		
		initStructure();
		
		originData = data;

	}
	private void setDataToCell(boolean needUpdate){
		
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++ ){
				
				setCellValue(y, x, Integer.parseInt(originData[ 9 * y + x]), needUpdate);
				
				if(Status.getWrongStatus()) {
					originData = null;
					return;
					
				}
			}
		}
		originData = null;
	}
	private HashSet<Integer>[][] getCandidateNumbers(){
		
		HashSet<Integer> result[][] = new HashSet[9][9];
		
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++ ){
				
				result[y][x] = Cells[y][x].getCandidateNumberSet();
				
			}
		}
		
		return result;
	}
	
	private void setCandidateNumbers(HashSet<Integer>[][] inputCandidateNumbers){
		
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++ ){
				
				Cells[y][x].setCandidateNumberSet(inputCandidateNumbers[y][x]);
				
			}
		}

	}

	private String[] processInputData(String inputData){
		while(inputData.contains("  ")) inputData = inputData.replaceAll("  ", " ");
		while(inputData.contains("\n")) inputData = inputData.replaceAll("\n", "");
		while(inputData.contains("\r")) inputData = inputData.replaceAll("\r", "");
		
		String data[] = inputData.split(" ");
		
		if(data.length == 1){
			
			data = inputData.split("");
			
		}
		if(data.length != 81){
			System.out.println("the input value can't translate to SudokuMap");
			Status.setWrongStatus(ErrorCode.ValueLENNOT81);
			return null;
		}
		checkNumber(data);
		
		if(Status.getWrongStatus()) return null;
		
		return data;
	}
	private void checkNumber(String[] inputData){
		if(inputData.length != 81){
			Status.setWrongStatus(ErrorCode.ValueLENNOT81);
			return;
		}
		for(int i = 0 ; i < 81 ; i++ ){
			try{
				if( -1 > Integer.parseInt(inputData[i]) || Integer.parseInt(inputData[i]) > 9){
					System.out.println("the values is not between 0~9");
					Status.setWrongStatus(ErrorCode.ValueCouldNotbetween19);
					return;
				}
			}
			catch(NumberFormatException e){
				System.out.println("The values can not be converted into 0~9");
				Status.setWrongStatus(ErrorCode.ValueCouldNotbetween19);
				return;
			}
		}
	}
	private void initStructure(){
		
		Cells = new Cell[9][9];
		for(int y = 0 ; y < 9 ; y++ ){
			for(int x = 0 ; x < 9 ; x++ ){
				Cells[y][x] = new Cell(Status, y, x);
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
	private int[][] getValues(){
		int result[][] = new int[9][9];
		for(int y = 0 ; y < 9 ; y++){
			for(int x = 0 ; x < 9 ; x++){
				result[y][x] = Cells[y][x].getValue();
			}
		}
		return result;
	}
	
	private void setCellValue(int y, int x, int inputValue, boolean needUpdate){
		
		LastSetY = y;
		LastSetX = x;
		Cells[y][x].setValue(inputValue, needUpdate);
		
	}
	
	private SudokuMap copyMap(MapStatus inputStatus){
		
		SudokuMap result = new SudokuMap(this, inputStatus);
		return result;
		
	}
	public boolean isEnable(){
		return !Status.getWrongStatus();
	}
	public boolean isFinish(){
		return Status.getFinishStatus();
	}
	public void show(){
		if(Status.getErrorCode() == ErrorCode.ValueCouldNotbetween19 || Status.getErrorCode() == ErrorCode.ValueLENNOT81) return;
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
	public boolean check() {
		
		boolean isUpdated = false;
		do{
			
			isUpdated = false;
			
			for(int y = 0 ; y < 9 ; y++){
				for(int x = 0 ; x < 9 ; x++){
					
					if(Cells[y][x].getValue() != 0) continue;
					
					HashSet<Integer> sizeTemp = Cells[y][x].getCandidateNumberSet();
					
					if(sizeTemp.size() == 1){
						
						int InputValueTemp = sizeTemp.iterator().next();
						
						Cells[y][x].setValue(InputValueTemp, true);
						
						isUpdated = true;
					}
					
				}
			}
			
			if(isUpdated) continue;
				
			for(int i = 0 ; i < 9 ; i++ ){
				
				boolean isRowChange = Rows[i].check();
				
				isUpdated = isUpdated || isRowChange;
			}
			
			if(isUpdated) continue;
			
			for(int i = 0 ; i < 9 ; i++ ){
				
				boolean isColumnsChange = Columns[i].check();
				
				isUpdated = isUpdated || isColumnsChange;
			}
			
		}while(isUpdated);
		
		return false;
	}
	private void RecursiveCalculate(){
		
		int tempX = 0, tempY = 0;
		int MinSize = Integer.MAX_VALUE;
		
		check();
		
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
			
			Status.setFinishStatus();
			Status.setAnswer(this);
			
			return;
			
		}

		SudokuMap tempMap[] = new SudokuMap[MinSize];
		MapStatus tempStatus[] = new MapStatus[MinSize];
		Thread tempThread[] = new Thread[MinSize];
		
		for(int i = 0 ; i < MinSize ; i++){
			
			tempStatus[i] = new MapStatus();
			tempMap[i] = copyMap(tempStatus[i]);
			tempThread[i] = new Thread(tempMap[i]);
		
		}		
		
		Iterator<Integer> iterator = Cells[tempY][tempX].getCandidateNumberSet().iterator();
		
		
		for(int index = 0; iterator.hasNext(); index++){
			int inputValue = iterator.next();
			
			tempMap[index].setCellValue(tempY, tempX, inputValue, true);
			
			if(!tempMap[index].isEnable()) continue;
				
			if(!Status.getUseThread()){
				tempMap[index].RecursiveCalculate();
				
				if(!tempStatus[index].getFinishStatus()) continue;
					
				Status.setFinishStatus();
				Status.setAnswer(tempStatus[index].getAnswer());
				
				return;
			}
			else{
				
				tempThread[index].start();
				
			}
			
		}
		if(!Status.getUseThread()) return ;
		for(int i = 0 ; i < MinSize ; i++ ){
			
			try {
				tempThread[i].join();
			} catch (InterruptedException e) {e.printStackTrace();}
			
			if(tempStatus[i].getFinishStatus()){
				
				Status.setFinishStatus();
				Status.setAnswer(tempStatus[i].getAnswer());
				
				return;
				
			}
			
		}
		
	}
	public int getErrorCode(){
		return Status.getErrorCode();
	}
	public void calculate(boolean inputUseThread){
		
		if(Status.getWrongStatus()) return;
		
		setDataToCell(true);
		
		if(Status.getWrongStatus()) return;
		
		Status.setUseThread(inputUseThread);
		
		RecursiveCalculate();
		
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
	@Override
	public void run() {
		RecursiveCalculate();
	}
}
