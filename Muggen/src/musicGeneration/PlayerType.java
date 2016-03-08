package musicGeneration;

import piano.Piano;
import note.HarmonicNote;
import scales.Scale;

public class PlayerType {
	private PlayerAll pA;
	private TypeDataBase datab;
	private int type;
	public PlayerType(int type){
		datab = new TypeDataBase();
		this.type = type;
	}

	public void randomize(){
		int randInstruCh=(int)( Math.random()*datab.getDatabase().get(type).getInstruChords().size());
		int randInstruMe=(int)( Math.random()*datab.getDatabase().get(type).getInstruMelody().size());
		int randNote=(int)( Math.random()*11);
		pA = new PlayerAll(new Scale(new HarmonicNote(60+randNote), datab.getDatabase().get(type).getMode()), datab.getDatabase().get(type).getInstruChords().get(randInstruCh), datab.getDatabase().get(type).getInstruMelody().get(randInstruMe), datab.getDatabase().get(type).getSeuil(), datab.getDatabase().get(type).getTempo());
		
	}
	public void play(){
		pA.play();
	}
	
	public void setType(int type){
		this.type = type;
	}
	public PlayerAll getPlayerAll(){
		return pA;
	}
	public TypeDataBase getTypeDataBase(){
		return datab;
	}
	
}
