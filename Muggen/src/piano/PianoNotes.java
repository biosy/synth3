package piano;

import note.HarmonicNote;

/**
 * classe de données : gère les notes du piano jouables sur la gamme ( chaques notes est représentée par son degré et sa note
 *
 * @author AKTOR Alexis
 *
 */
public class PianoNotes {

	private HarmonicNote note;
	private int degre;
	
	public PianoNotes(HarmonicNote note, int degre)
	{
		setDegre(degre);
		setNote(note);
	}

	public HarmonicNote getNote() {
		return note;
	}

	public void setNote(HarmonicNote note) {
		this.note = note;
	}

	public int getDegre() {
		return degre;
	}
	
	
	public void setDegre(int degre) {
		this.degre = degre;
	}
}
