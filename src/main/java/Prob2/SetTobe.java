package Prob2;

public class SetTobe extends InstumentMuzical
{
    public enum TipTobe {ELECTRONICE, ACUSTICE}

    private TipTobe tip;
    private int nrTobe;
    private int nrCinele;

    public SetTobe(){}
    public SetTobe(String producator, double pret,TipTobe tip, int nrTobe, int nrCinele)
    {
        super(producator, pret);
        this.tip = tip;
        this.nrTobe = nrTobe;
        this.nrCinele = nrCinele;
    }

    public TipTobe getTip()
    {
        return tip;
    }

    public void setTip(TipTobe tip)
    {
        this.tip = tip;
    }

    public int getNrTobe()
    {
        return nrTobe;
    }

    public void setNrTobe(int nrTobe)
    {
        this.nrTobe = nrTobe;
    }

    public int getNrCinele()
    {
        return nrCinele;
    }

    public void setNrCinele(int nrCinele)
    {
        this.nrCinele = nrCinele;
    }


    @Override
    public String toString() {
        return "SetTobe{" +
                "Producator='" + producator + '\'' +
                ", Pret=" + pret +
                ", TipTobe=" + tip +
                ", NrTobe=" + nrTobe +
                ", NrCinele=" + nrCinele +
                '}';
    }
}
