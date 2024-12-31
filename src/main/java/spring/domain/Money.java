package spring.domain;

import jakarta.persistence.*;


@Entity

public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///!!!!!!
    private Long id;

    private int date;
    private String sign;
    private int amount;
    private boolean isDeleted;
    private Long userId;

    /*
        @ManyToOne
        @JoinColumn(name = "user")
        private User user;
    */
    public Money(int date,String sign,int amount,Long userId){
        this(date,sign,amount, false,userId);
    }
    public Money(int date, String sign, int amount, boolean isDeleted,Long userId) {
        this.date = date;
        this.sign = sign;
        this.amount = amount;
        this.isDeleted = isDeleted;
        this.userId = userId;
    }
    public Money(){
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
