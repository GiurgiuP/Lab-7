package Prob2;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipInstrument")
@JsonSubTypes
        ({
        @JsonSubTypes.Type(value = Chitara.class, name = "Chitara"),
        @JsonSubTypes.Type(value = SetTobe.class, name = "SetTobe")
})
abstract class InstumentMuzical
{
    protected String producator;
    protected double pret;
    protected String tipInstrument;

    public InstumentMuzical()
    {
        this.tipInstrument = this.getClass().getSimpleName();
    }

    public InstumentMuzical(String producator, double pret)
    {
        this.producator = producator;
        this.pret = pret;
        this.tipInstrument = this.getClass().getSimpleName();
    }

    public String getProducator()
    {
        return producator;
    }

    public void setProducator(String producator)
    {
        this.producator = producator;
    }

    public double getPret()
    {
        return pret;
    }

    public void setPret(double pret)
    {
        this.pret = pret;
    }

    public String getTipInstrument()
    {
        return tipInstrument;
    }


    @Override
    public String toString()
    {
        return "Producator: " + producator + ", Pret: " + pret + ", Tip Instrument: " + tipInstrument;
    }
}
