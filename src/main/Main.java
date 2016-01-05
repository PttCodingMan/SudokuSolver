package main;

import structure.SudokuMap;

public class Main {

	public static void main(String[] args) {
		
		
		String testString = "800000000003600000070090200050007000000045700000100030001000068008500010090000400";
		
		int inputData[] = new int[81];
		
		for(int i= 0 ; i < 81 ; i++){
			
			inputData[i] = Integer.parseInt(testString.split("")[i]);
			
		}
		
		/*Start of execution time test*/
		
		int TestTimes = 500;
		
		SudokuMap map[] = new SudokuMap[TestTimes];
		
		for(int i = 0 ; i < TestTimes ; i++){
			
			map[i] = new SudokuMap(inputData);
			
		}
		
		boolean useThread = true;
		
		long StartTime = System.nanoTime();
		
		for(int i = 0 ; i < TestTimes ; i++){
			
			map[i].calculate(useThread);
			
		}
		
		long EndTime = System.nanoTime();
		
		System.out.println("Calculate " + TestTimes + " times cost " + (EndTime - StartTime) / 1000000 + " micro sec");
		
		/*End of execution time test*/
		
		SudokuMap TestMap = new SudokuMap(inputData);
		
		useThread = false;
		
		TestMap.show();
		
		TestMap.calculate(useThread);
		
		if(TestMap.isFinish()){
			System.out.println("Calculate finish!");
		}
		else{
			System.out.println("Calculate not finish!");
			System.out.println("Something wrong!");
		}
		TestMap.show();
		
		int result[][] = TestMap.getAnswer(); //Get the 9x9 answer!
		
	}

}
