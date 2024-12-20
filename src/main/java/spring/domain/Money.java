package spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///!!!!!!
    private Long id;

    private int date;
    private String sign;
    private int amount;
    private boolean isDeleted;

    public Money(int date,String sign,int amount){
        this(date,sign,amount, false);
    }
    public Money(int date, String sign, int amount, boolean isDeleted) {
        this.date = date;
        this.sign = sign;
        this.amount = amount;
        this.isDeleted = isDeleted;
    }
    public Money(){
    }

    public boolean isDeleted(){
        return isDeleted;
    }

    public int getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
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
}
