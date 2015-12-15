package structure;

public class MapStatus {
	private boolean FinishStatus;
	public MapStatus(){
		FinishStatus = false;
	}
	public void setFinishStatus(boolean inputStatus){
		FinishStatus = inputStatus;
	}
	public boolean getFinishStatus(){
		return FinishStatus;
	}
}
