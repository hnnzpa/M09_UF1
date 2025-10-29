package iticbcn.xifratge;

public class AlgorismeRotX extends AlgoritmeFactory {
    
    @Override
    public Xifrador creaXifrador() {
        Xifrador rotX = new XifradorRotX();
        return rotX; 
    }
}