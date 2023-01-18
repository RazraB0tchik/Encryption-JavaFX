package encryption.main.encryption.entity;

public class SelectedParams {
    Integer KeysSize;
    Integer openExponent;

    String textAria;

    String generatorFile;

    public SelectedParams(Integer keysSize, Integer openExponent, String textAria) {
        KeysSize = keysSize;
        this.openExponent = openExponent;
        this.textAria = textAria;
    }

    public SelectedParams() {
    }

    public Integer getKeysSize() {
        return KeysSize;
    }

    public void setKeysSize(Integer keysSize) {
        KeysSize = keysSize;
    }

    public Integer getOpenExponent() {
        return openExponent;
    }

    public void setOpenExponent(Integer openExponent) {
        this.openExponent = openExponent;
    }

    public String getTextAria() {
        return textAria;
    }

    public void setTextAria(String textAria) {
        this.textAria = textAria;
    }

}
