package structure;

public class MapStatus {
	private boolean FinishStatus;
	private boolean WrongStatus;
	private boolean useThread;
	private SudokuMap answer;
	
	private int ErrCode = ErrorCode.NoError;
	
	public MapStatus(){
		FinishStatus = false;
		WrongStatus = false;
		useThread = false;
	}
	
	protected void setUseThread(boolean inputUseThread){
		useThread = inputUseThread;
	}
	protected boolean getUseThread(){
		return useThread;
	}
	protected void setFinishStatus(){
		FinishStatus = true;
	}
	protected boolean getFinishStatus(){
		return FinishStatus;
	}
	
	protected void setWrongStatus(int inputErrorCode){
		if(ErrCode != ErrorCode.NoError) return;
		WrongStatus = true;
		ErrCode = inputErrorCode;
	}
	
	protected int getErrorCode(){
		return ErrCode;
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
