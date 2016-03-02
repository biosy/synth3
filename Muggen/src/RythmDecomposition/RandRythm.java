package RythmDecomposition;

import java.util.ArrayList;

public class RandRythm {

	private RythmGeneration rythmDec;
	private ArrayList<Integer> rythmes;
	public RandRythm(){
		rythmDec = new RythmGeneration();
		rythmes = new ArrayList<Integer>();
	}
	
	public void RandomGeneration(int nbrTimes, int seuil){
		
		
		int time=2000;
		while(rythmDec.getDecompo(nbrTimes).get(time).size()>seuil){
			time=(int)( Math.random()*rythmDec.getDecompo(nbrTimes).size());
			System.out.println("bijour");
			rythmes = rythmDec.getDecompo(nbrTimes).get(time);
		}
		
	}
	
	public ArrayList<Integer> getRythmes(){
		return rythmes;
	}

	@Override
	public String toString() {
		return "RandRythm [rythmes=" + rythmes + "]";
	}

}
