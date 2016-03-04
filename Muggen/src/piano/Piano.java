package piano;
import java.util.ArrayList;

import scales.*;
import note.HarmonicNote;

/**
 * classe de traitement : génère les notes jouables sur la gamme du piano
 *
 * @author AKTOR Alexis
 *
 */
public class Piano {
	private ArrayList<PianoNotes> piano;
	private Scale scale;
	
	public Piano(Scale scale)
	{
		piano = new ArrayList<PianoNotes>();
		this.scale=scale;
		initPiano();
	}
	
	private void initPiano()
	{
		int fundamental=scale.getFundamental().getHeight();//on prend la fondamentale
		int degres=1;
		PianoNotes notes = new PianoNotes(new HarmonicNote(0), 0);
		while(fundamental>11)
		{
			fundamental=fundamental-12;//on descend la gamme jusqu'a obtenir la note la plus basse correspondant à la fondamental
		}			
		while(fundamental<120)//on remonte jusqu'a obtenir toutes les notes jouable sur la gamme 
		{
			scale = new Scale(new HarmonicNote(fundamental), scale.getMode());
			for(int i=0;i<scale.getNotes().size();i++)
			{
				notes = new PianoNotes(scale.getNotes().get(i),degres);
				piano.add(notes);

				degres++;
			}
			degres=1;
			fundamental=fundamental+12;
			
		}
	}
	
	

	
	public String toString() {
		String str = "";
		for(int i=0;i<piano.size();i++)
		{
			str = str +"note"+ piano.get(i).getNote() + " degre : " + piano.get(i).getDegre();
		}
		return str;
	}

	public ArrayList<PianoNotes> getpiano()
	{
		return piano;
	}
	
	public HarmonicNote getPiano(int index)
	{
		return piano.get(index).getNote();
	}
		
	public int getdegre(int index)
	{
		return piano.get(index).getDegre();
	}
		
	public int incrementDegre(int deg, int inc)
	{
		int nbrdegre = scale.getNotes().size();
		deg=deg+inc;
		
		while(deg>nbrdegre)
		{
			deg=deg-nbrdegre;
		}
		
		return deg;
		
	}
	
	public int DecrementDegre(int deg, int inc)
	{
		int nbrdegre = scale.getNotes().size();
		deg=deg-inc;
		
		while(deg<=0)
		{
			deg=deg+nbrdegre;
		}
		
		return deg;
		
	}
	
	public void setScale(Scale scale)
	{
		this.scale=scale;
		piano.clear();
		initPiano();
	
	}
	
	public int getDegre(HarmonicNote fundamental){
		
		int degre=1;
		int i=0;
		
		HarmonicNote har = new HarmonicNote(fundamental.getHeight());
		HarmonicNote note = new HarmonicNote(piano.get(0).getNote().getHeight());
		
		//return fundamental.getHeight()-piano.get(0).getNote().getHeight();
		System.out.println(har.getHeight());

		while(har.getHeight()!=note.getHeight()){
			note.setHeight(piano.get(i).getNote().getHeight());
			if(har.getHeight()!=note.getHeight()){
				i++;
				degre = degre+1;
			}
				

			if(degre==scale.getNotes().size()+1){
				degre=1;
			}

		}
		return degre;
	}
	
	public int getIndice(HarmonicNote note){
		for(int i=0 ; i<120 ; i++){
			if(note.getHeight() == piano.get(i).getNote().getHeight()){
				return i;
			}
		}
		
		return 0;
	}
}