package scales;

import java.util.ArrayList;

import modes.Mode;
import note.HarmonicNote;
import note.Note;
import note.RythmicNote;

public class Scale {

	private Mode mode;
	private HarmonicNote fundamental;
	private ArrayList<HarmonicNote> notes;

	public Scale(HarmonicNote fundamental, Mode mode) {

		this.mode = mode;
		this.fundamental = fundamental;
		notes = new ArrayList<HarmonicNote>();
		notes.add(0, fundamental);
		// System.out.println(notes.get(0));
		// System.out.println(mode.getIntervals());
		for (int i = 1; i < mode.getIntervals().size(); i++) {
			notes.add(i, new HarmonicNote(notes.get(i - 1).getHeight()
					+ mode.getIntervals().get(i - 1)));
		}
		// System.out.println(notes.toString());
	}

	public ArrayList<HarmonicNote> getNotes() {
		return notes;
	}

	public HarmonicNote getDegree(int degree) { // First degree : 1
			
		HarmonicNote scaleNote = notes.get((degree - 1) % notes.size());
		//System.out.println("Note de base" + scaleNote.toString());
		int octaHeight = scaleNote.getHeight()+ 12*((degree-1)/(notes.size()));
		//System.out.println("Note octavisee :" + octaHeight);
		return new HarmonicNote(octaHeight);
	}

	public HarmonicNote getFundamental() {
		return fundamental;
	}
	public boolean isMajor(){
		return mode.getType()==1;
	}
	public boolean isMinor(){
		return mode.getType()==6;
	}
	public Scale getRelativeScale(){
		int minorThird = 3; //minor third interval. See relative scales theory.
		if(isMajor()){
			return new Scale(new HarmonicNote(fundamental.getHeight()-minorThird), new Mode(6));
		}else if(isMinor()){
			return new Scale(new HarmonicNote(fundamental.getHeight()+minorThird), new Mode(1));
		}else{
			return this;
		}
	}
	public Mode getMode(){
		return mode;
	}
	public String toString() {
		String ret = "";
		for (HarmonicNote note : notes) {
			ret += note.getHeight();
		}
		return ret;
	}

	public ArrayList<RythmicNote> toRythmicNote(int length) {
		ArrayList<RythmicNote> ret = new ArrayList<RythmicNote>();
		for (HarmonicNote note : notes) {
			ret.add(new RythmicNote(note.getHeight(), length));
		}
		return ret;
	}

	public ArrayList<RythmicNote> toRythmicNote() {
		return toRythmicNote(4);
	}
}