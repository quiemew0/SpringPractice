package spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MoneyCal {
    @Id
    private int date;

    private int total;

    public MoneyCal(int date, int total) {
        this.date = date;
        this.total = total;
    }

    public MoneyCal(){
    }

    public int getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
