/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si_proyecto1_ag;

import java.util.ArrayList;
/**
 *
 * @author Barajas-d
 */
public class SI_Proyecto1_AG {
    /**
     * @param args the command line arguments
     */
    static final int LENGTH_POPULATION = 5;
    static final int SIZE_GENOTYPE = 64;
    static final int NUMBER_RULES = 4;
    
    public static void main(String[] args){
        
        Sistema AG = new Sistema(LENGTH_POPULATION, SIZE_GENOTYPE, NUMBER_RULES);
        AG.printSystem();
        
        System.out.println("si_proyecto1_ag.SI_Proyecto1_AG.main()");
        
        AG.selection();
        AG.process();
        AG.printSystem();   
        
        System.out.println("si_proyecto1_ag.SI_Proyecto1_AG.main()");
        
        AG.selection();
        AG.process();
        AG.printSystem();   
        
        System.out.println("si_proyecto1_ag.SI_Proyecto1_AG.main()");
        
        AG.selection();
        AG.process();
        AG.printSystem();   
    }
}
