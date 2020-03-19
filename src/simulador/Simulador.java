package simulador;

import java.util.ArrayList;

public class Simulador {
	private Patch[][] patches;
	private ArrayList<Bacteria> bacterias;
	private ArrayList<Bacteria> deadBacterias;
	private int ticks;
	private int maxTicks;

	public static void main(String[] args) {
		Simulador s = new Simulador();
		s.run();
		s.print();
	}
	private void print() {
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
	public Simulador() {
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
		maxTicks = 5000;
	}
	public void run() {
		while(ticks<=maxTicks) {
			for (int i = 0; i < bacterias.size(); i++) {
				Bacteria bacteria = bacterias.get(i);
				bacteria.live();
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
	public void moveMe(Bacteria bacteria) {
		bacteria.setPatch(patches[bacteria.getX()][bacteria.getY()]);
	}
	public ArrayList<Bacteria> getBacterias() {
		return bacterias;
	}
	public void setBacterias(ArrayList<Bacteria> bacterias) {
		this.bacterias = bacterias;
	}
	
	
}
