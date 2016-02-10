package markovChain;

import java.util.Arrays;

public class Markov {
	
	private int[][] matTrans;
	private int size;
	public Markov(int size){
		this.setSize(size);
		setMatTrans(new int[size][size]);
		init();
	}
	public int[][] getMatTrans() {
		return matTrans;
	}
	public void setMatTrans(int[][] matTrans) {
		this.matTrans = matTrans;
	}
	public int getSize() {
		return size;
	}
	private void setSize(int size) {
		this.size = size;
	}
	
	public void addlc(){//ajouter un sommet
		setSize(size+1);
		init_colonne(size+1);
		init_ligne(size+1);
	}
	
	public void init_colonne(int colonne){
		for(int i=0;i<size;i++){
			matTrans[colonne][i] = 0;
		}
	}
	
	public void init_ligne(int ligne){
		for(int i=0;i<size;i++){
			matTrans[i][ligne] = 0;
		}
	}
	
	@Override
	public String toString() {
		return "Markov [matTrans=" + Arrays.toString(matTrans) + ", size="
				+ size + "]";
	}
	private void init(){
		for(int i=0 ; i< size;i++){
			for(int j=0;j<size;j++){
				matTrans[i][j]=0;
			}
		}
	}
	
	
	public void addLink(int cel_debut, int cel_fin, int proba){
		matTrans[cel_debut][cel_fin] = proba;
	}

}
