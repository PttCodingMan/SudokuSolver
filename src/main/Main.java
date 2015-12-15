package main;

import structure.SudokuMap;

public class Main {

	public static void main(String[] args) {
		//String testString = "5 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 1 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9";
		
		//String testString = "9 6 2 7 4 5 8 3 1 5 7 1 9 3 8 6 2 4 4 8 3 6 2 1 9 5 7 2 3 6 1 5 9 7 4 8 7 1 9 2 8 4 3 6 5 8 4 5 3 7 6 2 1 9 3 5 4 8 9 2 1 7 6 6 2 8 4 1 7 5 9 3 1 9 7 5 6 3 4 8 2";
		
		String testString = "1 2 3 4 5 6 7 8 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
		
		//String testString = "006400005030006900009050010020001804900000002708900030040090200007600080600002300";
		
		//String testString = "006400005\n030006900\n009050010\n020001804\n900000002\n708900030\n040090200\n007600080\n600002300";
		
		SudokuMap map = new SudokuMap(testString);
		if(!map.isEnable()){
			System.out.println("Init status: Fail");
		}
		else{
			System.out.println("Init status: Success");
		}
		map.show();
		//SudokuMap testMap = (SudokuMap) map.clone();
	}

}
