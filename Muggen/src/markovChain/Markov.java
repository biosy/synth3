package markovChain;

import java.util.Arrays;

public class Markov {
	
	private float[][] matTrans;
	private int size;
	public Markov(int size){
		this.setSize(size);
		setMatTrans(new float[size][size]);
		init();
	}
	public float[][] getMatTrans() {
		return matTrans;
	}
	
	public float getid(int in, int out){
		return matTrans[in][out];
	}
	public void setMatTrans(float[][] matTrans) {
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
		
		String chaine = "mat : ";
		for(int i=0;i<size;i++){
			for(int j = 0 ; j< size;j++){
				chaine = chaine + ',' + matTrans[i][j];
			}
			chaine = chaine + System.getProperty("line.separator"); 
				
		}
		return chaine;
	}
	public void init(){
		for(int i=0 ; i< size;i++){
			for(int j=0;j<size;j++){
				matTrans[i][j]=0;
			}
		}
	}
	
	
	public void addLink(int cel_debut, int cel_fin, float proba){
		matTrans[cel_debut][cel_fin] = proba;
	}

}
