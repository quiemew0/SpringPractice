package spring.service;


import org.springframework.stereotype.Service;
import spring.domain.Money;
import spring.domain.MoneyCal;
import spring.repository.MoneyCalRepository;
import spring.repository.MoneyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyService {

    private final MoneyRepository moneyRepository;
    private final MoneyCalRepository moneyCalRepository;
    public MoneyService(MoneyRepository moneyRepository, MoneyCalRepository moneyCalRepository){
        this.moneyCalRepository = moneyCalRepository;
        this.moneyRepository = moneyRepository;
    }

    // optional : null 처리를 강제한다. Or else throw를 가장 많이 사용 / 없으면 터짐.
    // enum (domain)
    // 삼항연산자는 사용안하는거 추천
    // enum

    public Optional<Money> regMoney(int date, String sign, int amount, Long userid){
        //Long userID = User.Id()
        Money money = new Money(date,sign,amount,false, userid);
        Money savedMoney = moneyRepository.save(money);

        Optional<MoneyCal> preMoney = moneyCalRepository.findByDate(date);
        MoneyCal moneyCal;
        if(preMoney.isPresent()){
            moneyCal = preMoney.get();
            int updatedCal = moneyCal.getTotal();
            if(sign.equals("sub")){
                updatedCal -= amount;
            } else if (sign.equals("add")){
                updatedCal += amount;
            } // sub, add아닌 경우에는 오류 반환하게 추가하기
            moneyCal.setTotal(updatedCal);
        }
        else{
            int initCal = (sign.equals("sub")) ? -amount : amount;
            moneyCal = new MoneyCal(date,initCal);
        }
        // MoneyCal savedCalMoney = moneyCalRepository.save(moneyCal);
        return Optional.of(savedMoney);
    }
/*
    public List<Money> getAllMoneys(){
        return moneyRepository.findByIsDeletedFalse();
    }*/
    /* public Optional<Money> getMoneyByDate(int date){
        return moneyRepository.findByDate(date);
    }*/
    public List<Money> getMoneyByUserid(Long id){
        return moneyRepository.findByIsDeletedFalseAndUserid(id);
    }
    public List<Money> getMoneyByDate(int date){
        return moneyRepository.findByIsDeletedFalseAndDate(date);
    }
    public Optional<MoneyCal> getTotalMoneyByDate(int date){
        return moneyCalRepository.findByDate(date);
    }


    public Optional<Money> deleteMoney(Long id, int date) {
        Optional<Money> optionalIdandDate = moneyRepository.findByUseridAndDate(id,date);
        if (optionalIdandDate.isPresent()) {
            Money money = optionalIdandDate.get();
            money.setDeleted(true);
            moneyRepository.save(money);
            return Optional.of(money);
        }
        return Optional.empty();
    }

}
