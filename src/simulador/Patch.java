package simulador;

public class Patch {
	private float food_energy = VarGlobal.max_food_energy/2;
	private int countdown = 0;
	private int x;
	private int y;
	private int size = 4;
	
	public Patch(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public float getFood_energy() {
		return food_energy;
	}
	public void setFood_energy(float food_energy) {
		this.food_energy = food_energy;
	}
	public int getCountdown() {
		return countdown;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void replenishFood() {
	   
		this.setCountdown(this.countdown-1);
		if(this.countdown <= 0) {
			setFood_energy(this.food_energy + VarGlobal.food_growth_rate * (VarGlobal.regrowth_variability/10));
			if(this.food_energy > VarGlobal.max_food_energy) {
				setFood_energy(VarGlobal.max_food_energy);
			}
		}
		if(this.food_energy < 0) {
			setFood_energy(0);
			setCountdown((int) VarGlobal.sprout_delay_time);
		}
	}
	
	
}
