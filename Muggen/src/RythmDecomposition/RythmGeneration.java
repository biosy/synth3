package RythmDecomposition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import rythm.Rythm;
import rythm.TimeSignature;
import note.RythmicNote;
import markovChain.Markov;

public class RythmGeneration {
	
	private HashMap<Integer,ArrayList<ArrayList<Integer>>> list;
	private RythmDecomposition ry;
	public RythmGeneration(){
		ry = new RythmDecomposition();
		list = new HashMap<Integer,ArrayList<ArrayList<Integer>>>();
		geneAll();
	}
	
	/*génère les décompositions primaire d'une note*/
	public ArrayList<ArrayList<Integer>> generateDecomposition(int state){
		
		ArrayList<ArrayList<Integer>> li =new ArrayList<ArrayList<Integer>>();

			for(int j=0;j<ry.getDecompo().get(state).size();j++){
				li.add(ry.getDecompo().get(state).get(j));
			}
			return li;
	}
	
	/*concatène deux ArrayList d'arrayList*/
	public ArrayList<ArrayList<Integer>> concatenateDecomposition(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b){
		
		ArrayList<ArrayList<Integer>> conc = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> a1 = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> b1 = new ArrayList<ArrayList<Integer>>();
		

		for(int i=0 ; i<a.size();i++){
			a1.add(i,(ArrayList<Integer>) a.get(i).clone());
			
			for(int j=0 ;j < b.size();j++){
				b1.add(j,(ArrayList<Integer>) b.get(j).clone());

				array = (ArrayList<Integer>)a1.get(i).clone();

				array.addAll(b1.get(j));
				a1.get(i).clear();
				a1.add(i,(ArrayList<Integer>) a.get(i).clone());
				
				conc.add((ArrayList<Integer>)array.clone());
				array.clear();
				
			}


		}

		return conc;
		
	}
	
	/*génère toutes les décompositions*/
	private void geneAll(){
		list.put(1, generateDecomposition(1));

		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> ones = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> ones1 = new ArrayList<ArrayList<Integer>>();

		ArrayList<ArrayList<Integer>> getList = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> getList1 = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> one = new ArrayList<Integer>();

		for(int i=2 ;i<ry.getDecompo().size()+1;i++){

			for(int j = 0 ;j<ry.getDecompo().get(i).size();j++){
				one.clear();
				ones.clear();
				ones1.clear();
				if(ry.getDecompo().get(i).get(j).size()!=1){
					
					
					
					
					getList = (ArrayList<ArrayList<Integer>>)list.get(ry.getDecompo().get(i).get(j).get(0)).clone();
					getList1 = (ArrayList<ArrayList<Integer>>)list.get(ry.getDecompo().get(i).get(j).get(1)).clone();

					
					ans.addAll((ArrayList<ArrayList<Integer>>)concatenateDecomposition(getList, getList1).clone());
					
					one.clear();
					ones.clear();
					ones1.clear();
					getList.clear();
					getList1.clear();
				}
				
				else{
					one.add(0, ry.getDecompo().get(i).get(j).get(0));
					ones.add(0, (ArrayList<Integer>)one.clone());
					ans.addAll((ArrayList<ArrayList<Integer>>)ones.clone());
				}
				
				
			}

			list.put(i, (ArrayList<ArrayList<Integer>>)ans.clone());
			ans.clear();

		}
	}
	

	@Override
	public String toString() {
		return "RythmGeneration [list=" + list + ", ry=" + ry + "]";
	}
	

	public ArrayList<ArrayList<Integer>> getDecompo(int state){
		return list.get(state);
	}
	
}
