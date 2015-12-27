package main;

import structure.SudokuMap;

public class Main {

	public static void main(String[] args) {
		//String testString = "5 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 1 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9";
		
		//String testString = "0 6 2 7 4 5 8 3 1 5 7 1 9 3 8 6 2 4 4 8 3 6 2 1 9 5 7 2 3 6 1 5 9 7 4 8 7 1 9 2 8 4 3 6 5 8 4 5 3 7 6 2 1 9 3 5 4 8 9 2 1 7 6 6 2 8 4 1 7 5 9 3 1 9 7 5 6 3 4 8 2";
		
		//String testString = "123456780000000000000000000000000000000000000000000000000000000000000000000000000";
		
		
		//String testString = "005430000010006000008090002000500006400000008300001000700040300000900050000082164";
		
		//String testString =   "000000000000000000000000000000000000000000000000000000000000000000000000275438690";
		
		//Hard problem, need to compute
		//String testString = "006000100920075600030800040390400000000000000000007054060009020005730069009000400";
		
		//Hardest problem in the world
		String testString = "800000000003600000070090200050007000000045700000100030001000068008500010090000400";
		
		int TestTimes = 500;
		
		SudokuMap map[] = new SudokuMap[TestTimes];
		
		for(int i = 0 ; i < TestTimes ; i++){
			
			map[i] = new SudokuMap(testString);
			
		}
		
		boolean useThread = true;
		
		long StartTime = System.nanoTime();
		
		for(int i = 0 ; i < TestTimes ; i++){
			
			map[i].calculate(useThread);
			
		}
		
		long EndTime = System.nanoTime();
		map[0].show();
		System.out.println("Calculate + " + TestTimes + " times cost " + (EndTime - StartTime) / 1000000 + " micro sec");
	}

}
