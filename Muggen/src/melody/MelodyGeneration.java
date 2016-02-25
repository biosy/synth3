package melody;

import RythmDecomposition.RythmGeneration;
import note.HarmonicNote;
import note.RythmicNote;
import piano.Piano;
import scales.Scale;
import chords.Chord;
import markovChain.Markov;

public class MelodyGeneration {

	private Melody melody;
	private Markov markov;
	private Scale scale;
	private Chord chord;
	private RythmGeneration ry;
	
	public MelodyGeneration(Scale scale, Chord chord){
		setMelody(new Melody());
		this.scale = scale;
		this.chord = chord;
		markov = new Markov(scale.getNotes().size()+1);
		initProba0();
	}

	public void setChord(Chord chord){
		this.chord = chord;
	}
	public Melody getMelody() {
		return melody;
	}

	public void setMelody(Melody melody) {
		this.melody = melody;
	}
	
	
	private void initProba0(){
		markov.init();
	}
	
	public void generate(RythmGeneration ry){
		int time;
		int proba=0;
		int actu = 0;
		int actu1=0;
		RythmicNote note = new RythmicNote(0, 0);
		for(int j=0;j<ry.getRy().size();j++){
			proba = 0;
			time=(int)( Math.random()*100);

			for(int i=0;i<scale.getNotes().size();i++)
			{
				if((time>=proba)&&(time<proba+markov.getid(actu, i)))
				{
					note=new RythmicNote(scale.getDegree(i).getHeight(), ry.getRy().get(j));//on cr�er une nouvelle RythmicNote
					melody.add(note);//on la rajoute dans la m�lodie
					//System.out.println(note+"jou�");
					actu1=i;
					
				}
				proba=proba+markov.getid(actu, i);
				actu=actu1;
			}
		}
		
	
	}
	
	@Override
	public String toString() {
		return "MelodyGeneration [melody=" + melody + ", markov=" + markov
				+ ", scale=" + scale + ", chord=" + chord + "]";
	}

	public void reinit(){
		/*0 est l'�tat de d�part
		 * �quiprobabilit� sur tout sauf les notes de l'accord
		 */
		Piano p = new Piano(scale);
		markov.init();
		//System.out.println(markov);

		/* 
		 * on parcours les accords pour les indiquer en temps que temps fort
		 */
		int j=0;
		int i=0;
		for(i=0;i<chord.getNotes().size();i++){
			for(j=0;j<markov.getSize();j++){
				markov.addLink(j, p.getDegre(chord.getNotes().get(i)), 100/chord.getNotes().size());
				//System.out.println("p("+j+","+p.getDegre(chord.getNotes().get(i))+")="+100/chord.getNotes().size());
				//System.out.println(p.getDegre(chord.getNotes().get(1)));
			}
			
		}
	}
	
	public Markov getMarkov(){
		return markov;
	}
	
	
}
