package iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgoritmeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorMonoalfabetic();
    }
}
