package RythmDecomposition;

import java.util.ArrayList;
import java.util.LinkedList;

import rythm.Rythm;
import rythm.TimeSignature;
import note.RythmicNote;
import markovChain.Markov;

public class RythmGeneration {
	
	private Markov markov;
	private LinkedList<Integer> rythm;
	
	public RythmGeneration(){
		markov = new Markov(10);
		markov.init();
		generateDecomposition();
		rythm = new LinkedList<Integer>();
	}
	
	public void generateDecomposition(){
		markov.addLink(3, 3, 100);
		markov.addLink(4, 4, 100);
	}
	
	public void gene(){
		int tu=(int)( Math.random()*2);

		if(tu ==0){
			rythm.add(3);
			rythm.add(3);

		}
		
		if(tu == 1){
			rythm.add(4);
		}
	}
	public void generate(int ntime){
		
		int proba;
		int time;
		int tu=(int)( Math.random()*2);
		
		
		int actu=4;
		int actu1 = 4;
		if(tu ==1){
			actu = 3;
			actu1 = 3;

		}
		else {
			actu =4;
			actu1 = 4;

		}
		TimeSignature ts = new TimeSignature(4, 4, 120);
		System.out.println(actu);
		float total=0;
		for(int j=0;j<ntime;j++){
			proba = 0;
			while(total < 1){
				time=(int)( Math.random()*100);
				System.out.println(time);
				for(int i=0;i<10;i++)
				{						//System.out.println(total);

					if((time>=proba)&&(time<proba+markov.getid(actu, i)))
					{

						if(total+ts.noteTime(i)<=1){
							rythm.add(i);
							actu1=i;
							total = total + ts.noteTime(i);

						}
						
					}
					
					
					proba=proba+markov.getid(actu, i);
					actu=actu1;
				}	
			}
			
		}
	}
	
	public void afficheEcc(){
		for(int i=0;i<rythm.size();i++){
			System.out.println("ec : "+rythm.get(i));
		}
	}

	public LinkedList<Integer> getRy(){
		return rythm;
	}
}
