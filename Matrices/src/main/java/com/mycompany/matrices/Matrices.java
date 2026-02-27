package com.mycompany.matrices;

import javax.swing.JOptionPane;

public class Matrices {

    int[][] m;

    public void crearMatriz(int f, int c) {
        
        m = new int[f][c];
        if(m==null){
        
        }
        for (int fi = 0; fi < f; fi++) {
            for (int ci = 0; ci < c; ci++) {
                m[fi][ci] = Integer.parseInt(JOptionPane.showInputDialog("Candidato: " + (fi + 1) + " Mesa : " + (ci + 1)));
            }
        }
        JOptionPane.showMessageDialog(null, "No hay mas candidatos");
    }

    public void consultarMatriz() {
        for (int fi = 0; fi < m.length; fi++) {
            for (int ci = 0; ci < m[fi].length; ci++) {
                
                JOptionPane.showMessageDialog(null, "Resultado candidato "+(fi+1)+"\nMesa :  "+(ci+1)+ "\nVotos"+m[fi][ci]);
            }
        }
    }
}
