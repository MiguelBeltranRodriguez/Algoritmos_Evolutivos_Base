package simulador;

import java.util.ArrayList;

import javax.script.ScriptException;

import evolucion.Individual;
import translate.RuleTranslated;

public class Simulator {
	private Patch[][] patches;
	private ArrayList<Bacteria> bacterias;
	private ArrayList<Bacteria> deadBacterias;
	private int ticks;
	private int maxTicks;
	public int reglasValidas = 0;
	public ArrayList<RuleTranslated> rulesTranslated;

	public static void main(String[] args) {
		Simulator s = new Simulator();
		s.run();
		s.print();
	}
	public void print() {
		System.out.println("Bacterias: " + bacterias.size());
		for (Bacteria bacteria : bacterias) {
			System.out.println("     Bacteria: " + bacteria.getEnergy());
		}
		Float totalEnergy = 0f;
		for (int x = 0; x < VarGlobal.sizeX; x++) {
			for (int y = 0; y < VarGlobal.sizeY; y++) {
				totalEnergy += patches[x][y].getFood_energy();
			}
		}
		System.out.println("Energy: " + totalEnergy);
	}
	public Simulator() {
		rulesTranslated = new ArrayList<RuleTranslated>();
		patches = new Patch[VarGlobal.sizeX][VarGlobal.sizeY];
		for (int x = 0; x < VarGlobal.sizeX; x++) {
			for (int y = 0; y < VarGlobal.sizeY; y++) {
				patches[x][y] = new Patch(x, y);
			}
		}
		bacterias = new ArrayList<Bacteria>();
		deadBacterias = new ArrayList<Bacteria>();
		bacterias.add(new Bacteria(60, 3, 3, patches[3][3], this));
		ticks = 0;
		maxTicks = 35;
	}
	public void run() {
		while(ticks<=maxTicks) {
			if(bacterias.size()>10) {
				return;
			}
			for (int i = 0; i < bacterias.size(); i++) {
				Bacteria bacteria = bacterias.get(i);
				bacteria.live();
				if(bacterias.size()>10) {
					return;
				}
				if(bacteria.getEnergy() <= 0) {
					deadBacterias.add(bacteria);
				}
			}
			for (Bacteria bacteria : deadBacterias) {
				bacterias.remove(bacteria);
			}
			for (int x = 0; x < VarGlobal.sizeX; x++) {
				for (int y = 0; y < VarGlobal.sizeY; y++) {
					patches[x][y].replenishFood();
				}
			}
			ticks++;
		}
	}
	public void run(Individual individual) throws ScriptException {
		
		
		
		RuleTranslated rt = new RuleTranslated(individual.getRules().get(0).getGenes());
		RuleTranslated rt2 = new RuleTranslated(individual.getRules().get(1).getGenes());
		RuleTranslated rt3 = new RuleTranslated(individual.getRules().get(2).getGenes());
		RuleTranslated rt4 = new RuleTranslated(individual.getRules().get(3).getGenes());
		rulesTranslated.add(rt);
		rulesTranslated.add(rt2);
		rulesTranslated.add(rt3);
		rulesTranslated.add(rt4);
		
		while(ticks<=maxTicks) {
			if(bacterias.size()>10) {
				return;
			}
			for (int i = 0; i < bacterias.size(); i++) {
				Bacteria bacteria = bacterias.get(i);
				bacteria.live(rulesTranslated);

				if(bacterias.size()>10) {
					return;
				}
				if(rt.evaluated(bacteria, bacteria.getPatch(), this)) {
					deadBacterias.add(bacteria);
				}
			}
			for (Bacteria bacteria : deadBacterias) {
				bacterias.remove(bacteria);
			}
			for (int x = 0; x < VarGlobal.sizeX; x++) {
				for (int y = 0; y < VarGlobal.sizeY; y++) {
					patches[x][y].replenishFood();
				}
			}
			ticks++;
		}
	}
	public void moveMe(Bacteria bacteria) {
		bacteria.setPatch(patches[bacteria.getX()][bacteria.getY()]);
	}
	public ArrayList<Bacteria> getBacterias() {
		return bacterias;
	}
	public void setBacterias(ArrayList<Bacteria> bacterias) {
		this.bacterias = bacterias;
	}
	public float getEnergiaTotal() {
		Float totalEnergy = 0f;
		for (int x = 0; x < VarGlobal.sizeX; x++) {
			for (int y = 0; y < VarGlobal.sizeY; y++) {
				totalEnergy += patches[x][y].getFood_energy();
			}
		}
		return totalEnergy;
	}
	
	
}
