package note;

import note.RythmicNote;

public class HarmonicNote {

		private int height;
		
		@Override
		public String toString() {
			return "HarmonicNote [height=" + height + "]";
		}

		public HarmonicNote(int height){
			this.setHeight(height);
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
		
		public RythmicNote toRythmicNote(int length){
			return new RythmicNote(getHeight(), length);
		}
		public RythmicNote toRythmicNote(){
			return toRythmicNote(4);
		}
}
