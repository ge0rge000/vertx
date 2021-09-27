package helloworldapp;

public class Convertor {
    String money;
    String mainCurrency;
    String convertedCurrency;

    public Convertor(String money, String mainCurrency, String convertedCurrency) {
        this.money = money;
        this.mainCurrency = mainCurrency;
        this.convertedCurrency = convertedCurrency;
    }
    public Convertor() {

    }
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMainCurrency() {
        return mainCurrency;
    }

    public void setMainCurrency(String mainCurrency) {
        this.mainCurrency = mainCurrency;
    }

    public String getConvertedCurrency() {
        return convertedCurrency;
    }

    public void setConvertedCurrency(String convertedCurrency) {
        this.convertedCurrency = convertedCurrency;
    }
}
