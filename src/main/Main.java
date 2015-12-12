package main;

import structure.SudokuMap;

public class Main {

	public static void main(String[] args) {
		String testString = "5 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 1 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9";
		SudokuMap map = new SudokuMap(testString);
		if(!map.isEnable()) return;
		map.show();
	}

}
