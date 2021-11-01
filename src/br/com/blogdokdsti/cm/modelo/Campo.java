    
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
 void minar(){
     minado = true;
 }

    public int getLINHA() {
        return LINHA;
    }

    public int getCOLUNA() {
        return COLUNA;
    }
    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }
long minasNaVizinhanca(){
    return vizinho.stream().filter(v -> v.minado).count();
}
void reiniciar(){
    marcado = false;
    minado = false;
    aberto = false;
}
public String toString(){
    if(marcado){
        return "X";
    }else if(aberto && minado){
        return "*";
    }else if(aberto && minasNaVizinhanca() > 0){
        return Long.toString(minasNaVizinhanca());
    }else if(aberto){
        return "x";
    }else{
        return "?";
    }
}
}