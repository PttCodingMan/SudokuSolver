# TruthSudokuSolver
This is Multithread sudoku solver library in java
It's pretty fast and easy to use.

You can get a quickstart in the following code.

	String testString = "800000000003600000070090200050007000000045700000100030001000068008500010090000400";
	
	SudokuMap map = new SudokuMap(testString);
		
	boolean useThread = false;
			
	map.show();
			
	map.calculate(useThread);
			
	if(map.isFinish()){
		System.out.println("Calculate finish!");
	}
	else{
		System.out.println("Calculate not finish!");
		System.out.println("Something wrong!");
	}
	map.show();
	
	int result[][] = TestMap.getAnswer(); //Get the 9x9 answer!

Then you can get the following output.

  8  ~  ~   ~  ~  ~   ~  ~  ~  
  ~  ~  3   6  ~  ~   ~  ~  ~  
  ~  7  ~   ~  9  ~   2  ~  ~  

  ~  5  ~   ~  ~  7   ~  ~  ~  
  ~  ~  ~   ~  4  5   7  ~  ~  
  ~  ~  ~   1  ~  ~   ~  3  ~  

  ~  ~  1   ~  ~  ~   ~  6  8  
  ~  ~  8   5  ~  ~   ~  1  ~  
  ~  9  ~   ~  ~  ~   4  ~  ~  

Calculate finish!

  8  1  2   7  5  3   6  4  9  
  9  4  3   6  8  2   1  7  5  
  6  7  5   4  9  1   2  8  3  

  1  5  4   2  3  7   8  9  6  
  3  6  9   8  4  5   7  2  1  
  2  8  7   1  6  9   5  3  4  

  5  2  1   9  7  4   3  6  8  
  4  3  8   5  2  6   9  1  7  
  7  9  6   3  1  8   4  5  2  
