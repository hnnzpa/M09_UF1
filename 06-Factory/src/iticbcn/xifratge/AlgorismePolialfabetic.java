package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgoritmeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorPolialfabetic();
    }
}