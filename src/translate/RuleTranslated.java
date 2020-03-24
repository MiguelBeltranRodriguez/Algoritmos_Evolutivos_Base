package translate;

import java.util.ArrayList;
import java.util.List;

import simulador.Bacteria;
import simulador.Patch;
import simulador.Simulator;
import simulador.VarGlobal;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RuleTranslated {
	private int varWorld;
	private int firstOperator;
	private int varPatch;
	private int secondOperator;
	private int varBacteria;
	private int thirdOperator;
	private int varPatch2;
	
	private String cadena = "";
	
	public RuleTranslated(ArrayList<Integer> genes) {
		
		List<Integer> secuence = genes.subList(0, 2);
		varWorld = translate(secuence); 
		
		
		secuence =  genes.subList(2, 5);
		firstOperator = translate(secuence); 
		
	
		secuence=  genes.subList(5, 6);
		varPatch = translate(secuence); 
		
	
		secuence=  genes.subList(6, 9);
		secondOperator = translate(secuence); 
		
		
		secuence=  genes.subList(9, 10);
		varBacteria = translate(secuence); 
		
	
		secuence=  genes.subList(10, 13);
		thirdOperator = translate(secuence); 
		
	
		secuence=  genes.subList(13, 14);
		varBacteria = translate(secuence); 
		
		
		if(varWorld!=-1) {
			cadena = "var1";	
		}
		if(firstOperator!=-1) {
			cadena += getStringOperator(firstOperator);
		}
		if(varPatch!=-1) {
			cadena += "var2";
		}
		if(secondOperator!=-1) {
			cadena += getStringOperator(secondOperator);
		}
		if(varBacteria!=-1) {
			cadena += "var3";
		}
		if(thirdOperator!=-1 && varPatch2!=-1 ) {
			cadena += getStringOperator(thirdOperator);
		}
		if(varPatch2!=-1) {
			cadena += "var4";
		}
	
	}
	private String getStringOperator(int operator) {
		switch (operator) {
		case 0:
			return "==";
		case 1:
			return ">=";
		case 2:
			return "<=";
		case 3:
			return "&&";
		case 4:
			return ">";
		case 5:
			return "<";
		case 6:
			return "!=";
		case 7:
			return "||";
		case 8:
			return "===";
		default:
			return "";
		}
		
	}
	public boolean evaluated(Bacteria b, Patch p, Simulator s) throws ScriptException, ClassCastException {
		ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        
        engine.put("var1", getWorldObject());
        engine.put("var2", getPatchObject(p));
        engine.put("var3", getBacteriaObject(b));
        engine.put("var4", getPatchObject(p));
        boolean resultado = (boolean) engine.eval(cadena);
        s.reglasValidas++;
        return resultado;
	}
	private String getBacteriaObject(Bacteria b) {
		switch (varPatch2) {
		case 0:
			return Float.toString(b.getEnergy());
		case 1:
			return Integer.toString(b.getX()*b.getY());
		default:
			return "";
		}
	}
	private String getPatchObject(Patch p) {
		switch (varBacteria) {
		case 0:
			return Float.toString(p.getFood_energy());
		case 1:
			return p.getX()+" "+p.getY();
		default:
			return "";
		}
	}
	private String getWorldObject() {
		switch (varWorld) {
		case 0:
			return Float.toString(VarGlobal.food_bacteria_eat);
		case 1:
			return Integer.toString(VarGlobal.min_reproduce_energy);
		case 2:
			return Integer.toString(0);
		case 3:
			return Boolean.toString(true);
		default:
			return "";
		}
	}
	private int translate(List<Integer> varWorldGenes) {
		String number = "";
		for (Integer integer : varWorldGenes) {
			number = number+integer;
		}
		if(!noNull(number)) {
			return Integer.parseInt(number, 2);
		}else {
			return -1;
		}
		
	}
	private boolean noNull(String number) {
		return number.contains("2");
	}
	public int getVarWorld() {
		return varWorld;
	}
	public void setVarWorld(int varWorld) {
		this.varWorld = varWorld;
	}
	public int getFirstOperator() {
		return firstOperator;
	}
	public void setFirstOperator(int firstOperator) {
		this.firstOperator = firstOperator;
	}
	public int getVarPatch() {
		return varPatch;
	}
	public void setVarPatch(int varPatch) {
		this.varPatch = varPatch;
	}
	public int getSecondOperator() {
		return secondOperator;
	}
	public void setSecondOperator(int secondOperator) {
		this.secondOperator = secondOperator;
	}
	public int getVarBacteria() {
		return varBacteria;
	}
	public void setVarBacteria(int varBacteria) {
		this.varBacteria = varBacteria;
	}
	public int getThirdOperator() {
		return thirdOperator;
	}
	public void setThirdOperator(int thirdOperator) {
		this.thirdOperator = thirdOperator;
	}
	public int getVarPatch2() {
		return varPatch2;
	}
	public void setVarPatch2(int varPatch2) {
		this.varPatch2 = varPatch2;
	}
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	
	
}
