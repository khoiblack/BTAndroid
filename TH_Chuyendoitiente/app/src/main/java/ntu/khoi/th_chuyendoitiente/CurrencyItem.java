package ntu.khoi.th_chuyendoitiente;

public class CurrencyItem {
    private String currencyName;
    private int flagImage;

    public CurrencyItem(String currencyName, int flagImage) {
        this.currencyName = currencyName;
        this.flagImage = flagImage;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getFlagImage() {
        return flagImage;
    }
}
