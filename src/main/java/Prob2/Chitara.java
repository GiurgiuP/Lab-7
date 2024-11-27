package Prob2;

public class Chitara extends InstumentMuzical
{
    public enum TipChitara {ELECTRICA, ACUSTICA, CLASICA}

    private TipChitara tipChitara;
    private int nrCorzi;

    public Chitara() {}

    public Chitara(String producator, double pret, TipChitara tipChitara, int nrCorzi)
    {
        super(producator, pret);
        this.tipChitara = tipChitara;
        this.nrCorzi = nrCorzi;
    }

    public TipChitara getTipChitara()
    {
        return tipChitara;
    }

    public void setTipChitara(TipChitara tipChitara)
    {
        this.tipChitara = tipChitara;
    }

    public int getNrCorzi()
    {
        return nrCorzi;
    }

    public void setNrCorzi(int nrCorzi)
    {
        this.nrCorzi = nrCorzi;
    }


    @Override
    public String toString() {
        return "Chitara{" +
                "Producator='" + producator + '\'' +
                ", Pret=" + pret +
                ", TipChitara=" + tipChitara +
                ", NrCorzi=" + nrCorzi +
                '}';
    }
}
