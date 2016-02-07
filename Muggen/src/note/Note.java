package note;

public abstract class Note {
	
	private int height;
	
	public Note(int height){
		this.setHeight(height);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Note [height=" + height + "]";
	}
	

}
