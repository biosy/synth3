package note;

public class RythmicNote {

	private int height;
	private int duration;
	
	public RythmicNote(int height, int duration){
		this.setHeight(height);
		this.setDuration(duration);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "RythmicNote [height=" + height + ", duration=" + duration + "]";
	}
}
