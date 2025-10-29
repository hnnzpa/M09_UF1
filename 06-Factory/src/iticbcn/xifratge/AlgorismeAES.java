package iticbcn.xifratge;

public class AlgorismeAES extends AlgoritmeFactory {
    
    @Override
    public Xifrador creaXifrador() {
        return new XifradorAES();
    }
}