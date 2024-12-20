package spring.domain;

public class MoneyDTO {
    private Long id;

    private int date;
    private String sign;
    private int amount;
    private boolean isDeleted;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public String getSign() {
        return sign;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
