package structure;

public class MapStatus {
	private boolean FinishStatus;
	private boolean WrongStatus;
	private SudokuMap answer;
	
	public MapStatus(){
		FinishStatus = false;
		WrongStatus = false;
	}
	protected void setFinishStatus(boolean inputStatus){
		FinishStatus = inputStatus;
	}
	protected boolean getFinishStatus(){
		return FinishStatus;
	}
	
	protected void setWrongStatus(boolean inputStatus){
		WrongStatus = inputStatus;
	}
	protected boolean getWrongStatus(){
		return WrongStatus;
	}
	
	protected void setAnswer(SudokuMap inputAnswer){
		answer = inputAnswer;
	}
	
	protected SudokuMap getAnswer(){
		return answer;
	}
	
}
