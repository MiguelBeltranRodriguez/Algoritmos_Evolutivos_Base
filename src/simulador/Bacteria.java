package simulador;

public class Bacteria {
	private int size = 5;
	private float energy = 60;
	private int x;
	private int y;
	private Patch patch;
	private Simulador simulador;
	
	public Bacteria(float energy, int x, int y, Patch patch, Simulador simulador) {
		super();
		this.energy = energy;
		this.x = x;
		this.y = y;
		this.patch = patch;
		this.simulador = simulador;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public float getEnergy() {
		return energy;
	}

	public void setEnergy(float energy) {
		this.energy = energy;
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
	
	public Patch getPatch() {
		return patch;
	}

	public void setPatch(Patch patch) {
		this.patch = patch;
	}

	
	public Simulador getSimulador() {
		return simulador;
	}

	public void setSimulador(Simulador simulador) {
		this.simulador = simulador;
	}

	public void move() {
		setEnergy(this.energy-1);
		 int maxX = 1; 
	     int minX = -1;
	     
	     int maxY = 1; 
	     int minY = -1;
	     
	    
	     
	     if(x-1<=0) {
	    	 minX = 0;
	     }
	     else if(x+1>=VarGlobal.sizeX) {
	    	 maxX = 0;
	     }
	     if(y-1<=0) {
	    	 minY = 0;
	     }
	     else if(y+1>=VarGlobal.sizeY) {
	    	 maxY = 0;
	     }
	     int rangeX = maxX - minX + 1;
	     int rangeY = maxY - minY + 1;
	     int randomFloatX = (int)(Math.random() * rangeX) + minX;
	     int randonFloatY = (int)(Math.random() * rangeY) + minY;
	     
	     this.x += randomFloatX;
	     this.y += randonFloatY;
	     simulador.moveMe(this);
	}

	public void live() {
		move();
		bacteriaEatFood();
		reproduceBacteria();
		
	}

	private void reproduceBacteria() {
		
	     if(this.energy > VarGlobal.min_reproduce_energy) {
			this.setEnergy(this.energy/2);
			Bacteria son = new Bacteria(this.energy/2, this.getX(), this.getY(), patch, simulador);
			simulador.getBacterias().add(son);
	     }
	}

	private void bacteriaEatFood() {
		if (patch.getFood_energy() > VarGlobal.food_bacteria_eat) {
			patch.setFood_energy(patch.getFood_energy()-VarGlobal.food_bacteria_eat);
			this.setEnergy(this.getEnergy()+(VarGlobal.food_bacteria_eat/5));
		}
		if (patch.getFood_energy() <= VarGlobal.food_bacteria_eat){
			patch.setCountdown((int) VarGlobal.sprout_delay_time);
		}
	}

	
	
	
	
	
	
}
