/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.listadoble;

import javax.swing.JOptionPane;

public class ListaDoble {

    NodoDoble inicio;

    
    public void crearInicio(int dato){
        NodoDoble nuevo=new NodoDoble();
        nuevo.setAtras(null);
        nuevo.setAdelante(null);
        nuevo.setDato(dato);
        if(inicio==null){
        inicio=nuevo;
        }else{
        nuevo.setAdelante(inicio);
        inicio.setAtras(nuevo);
        inicio=nuevo;
        }
  }
  public void crearFinal(int dato){
      NodoDoble nuevo = new NodoDoble();
      nuevo.setDato(dato);
      nuevo.setAdelante(null);
      if(inicio == null){
          // Si la lista está vacía, el nuevo nodo es el inicio
          nuevo.setAtras(null);
          inicio = nuevo;
      } else {
          // Recorrer hasta el último nodo
          NodoDoble temporal = inicio;
          while(temporal.getAdelante() != null){
              temporal = temporal.getAdelante();
          }
          // Enlazar el nuevo nodo al final
          temporal.setAdelante(nuevo);
          nuevo.setAtras(temporal);
      }
  }
 public void imprimir(){
     NodoDoble temporal=inicio;
     if(inicio==null){
     JOptionPane.showMessageDialog(null, "Lista vacia");
     }else{
     do{
      JOptionPane.showMessageDialog(null,
              "Dato: "+temporal.getDato());
      temporal=temporal.getAdelante();
     }while(temporal!=null);
       }}          
 public void buscar(int bus){
     if(inicio == null){
         JOptionPane.showMessageDialog(null, "Lista vacia");
         return;
     }
     
     NodoDoble temporal = inicio;
     boolean encontrado = false;
     String resultado = "Resultados para el número " + bus + ":\n\n";
     
     while(temporal != null){
         if(bus == temporal.getDato()){
             encontrado = true;
             
             // Comprobar ambos lados para no dar error de NullPointerException
             String adelante = (temporal.getAdelante() != null) ? String.valueOf(temporal.getAdelante().getDato()) : "Nada (fin)";
             String atras = (temporal.getAtras() != null) ? String.valueOf(temporal.getAtras().getDato()) : "Nada (inicio)";
             
             resultado += "- [" + bus + "] tiene atrás: " + atras + " | adelante: " + adelante + "\n";
         }
         temporal = temporal.getAdelante();
     }
     
     if (encontrado) {
         JOptionPane.showMessageDialog(null, resultado);
     } else {
         JOptionPane.showMessageDialog(null, "El valor " + bus + " no se encontró en la lista.");
     }
 }  
       

    // Buscar un número y mostrar TODOS los nodos que están ADELANTE
    public void buscarAdelante(int bus){
        if(inicio == null){
            JOptionPane.showMessageDialog(null, "Lista vacia");
            return;
        }
        NodoDoble temporal = inicio;
        boolean encontrado = false;
        String resultado = "Resultados para el número " + bus + ":\n\n";
        
        while(temporal != null){
            if(bus == temporal.getDato()){
                encontrado = true;
                if(temporal.getAdelante() != null){
                    resultado += "->" + bus + " está antes de " + temporal.getAdelante().getDato() + "\n";
                } else {
                    resultado += "->" + bus + " es el último (no hay nada adelante)\n";
                }
            }
            temporal = temporal.getAdelante();
        }
        
        if(encontrado){
            JOptionPane.showMessageDialog(null, resultado);
        } else {
            JOptionPane.showMessageDialog(null, "El valor " + bus + " no se encontró en la lista.");
        }
    }

    // Buscar un número y mostrar TODOS los nodos que están ATRÁS
    public void buscarAtras(int bus){
        if(inicio == null){
            JOptionPane.showMessageDialog(null, "Lista vacia");
            return;
        }
        NodoDoble temporal = inicio;
        boolean encontrado = false;
        String resultado = "Resultados para el número " + bus + ":\n\n";
        
        while(temporal != null){
            if(bus == temporal.getDato()){
                encontrado = true;
                if(temporal.getAtras() != null){
                    resultado += "->" + bus + " está después de " + temporal.getAtras().getDato() + "\n";
                } else {
                    resultado += "->" + bus + " es el primero (no hay nada atrás)\n";
                }
            }
            temporal = temporal.getAdelante();
        }
        
        if(encontrado){
            JOptionPane.showMessageDialog(null, resultado);
        } else {
            JOptionPane.showMessageDialog(null, "El valor " + bus + " no se encontró en la lista.");
        }
    }

    // Eliminar el primer nodo que coincida con el valor
    public void eliminar(int dato){
        if(inicio == null){
            JOptionPane.showMessageDialog(null, "Lista vacia");
            return;
        }
        NodoDoble temporal = inicio;
        
        while(temporal != null){
            if(dato == temporal.getDato()){
                // Caso 1: Es el único nodo
                if(temporal.getAtras() == null && temporal.getAdelante() == null){
                    inicio = null;
                }
                // Caso 2: Es el primer nodo
                else if(temporal.getAtras() == null){
                    inicio = temporal.getAdelante();
                    if(inicio != null){
                        inicio.setAtras(null);
                    }
                }
                // Caso 3: Es el último nodo
                else if(temporal.getAdelante() == null){
                    temporal.getAtras().setAdelante(null);
                }
                // Caso 4: Está en el medio
                else {
                    temporal.getAtras().setAdelante(temporal.getAdelante());
                    temporal.getAdelante().setAtras(temporal.getAtras());
                }
                JOptionPane.showMessageDialog(null, "Se eliminó un nodo con el valor " + dato + ".");
                return; // Cortar el ciclo aquí para solo eliminar el primero
            }
            temporal = temporal.getAdelante();
        }
        
        JOptionPane.showMessageDialog(null, "El valor " + dato + " no se encontró en la lista.");
    }

    // Devuelve la lista como texto para mostrar en la interfaz
    public String getListaComoTexto(){
        if(inicio == null){
            return "Lista vacía";
        }
        String resultado = "";
        NodoDoble temporal = inicio;
        int pos = 1;
        while(temporal != null){
            resultado += " " + pos + "\n: [" + temporal.getDato() + "]";
            if(temporal.getAdelante() != null){
                resultado += " > ";
            }
            temporal = temporal.getAdelante();
            pos++;
        }
        return resultado;
    }

}
