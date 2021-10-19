/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogdokdsti.cm.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KDS
 */
public class Campo {

    private final int LINHA;
    private final int COLUNA;

    private boolean minado = false;
    private boolean marcado = false;
    private boolean aberto = false;

    private List<Campo> vizinho = new ArrayList<>();

    Campo(int linha, int coluna) {
        this.COLUNA = coluna;
        this.LINHA = linha;
    }
public boolean adicionarVizinho(Campo vizinho){
    boolean linhaDiferente = LINHA != vizinho.LINHA;
            boolean colunaDiferente =  COLUNA != vizinho.COLUNA;
                    boolean diagonal = linhaDiferente && colunaDiferente;
                      
                   int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
                   int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA);
                   int deltaGeral = deltaLinha + deltaColuna;
                   
                   if(deltaGeral == 1 && !diagonal){
                       this.vizinho.add(vizinho);
                       return true;
                   }else if (deltaGeral == 2 && diagonal){
                       this.vizinho.add(vizinho);
                       return true;
                   }else{
                       return false;
                   }
                   
  
}
 void alternarMarcacao(){
     if (!aberto) {
         marcado = !marcado;
     }
}
 boolean abrir(){
     if(!aberto && !marcado){
         aberto = true;
         if(minado){
             throw new Explosao();
         }if(vizinhancaSegura()){
             vizinho.forEach(v -> v.abrir());
         }
         return true;
     }else{
         return false;
     }
     
 }
 boolean vizinhancaSegura(){
     return vizinho.stream().noneMatch(v -> v.minado);
     }
}