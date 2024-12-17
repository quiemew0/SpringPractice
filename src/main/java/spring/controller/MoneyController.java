package spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.domain.Money;
import spring.domain.MoneyCal;
import spring.service.MoneyService;

import java.util.List;
import java.util.Optional;

@RestController
public class MoneyController {


    @Autowired
    private MoneyService moneyService;

/*    public MoneyController(MoneyService moneyService){
        this.moneyService = moneyService;
    }**/

    @PostMapping("/money")
    public ResponseEntity<Money> register(@RequestParam int date, @RequestParam String sign, @RequestParam int amount) {

        Optional<Money> money = moneyService.regMoney(date, sign, amount);
        return money.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    @GetMapping("/money/search")
    public ResponseEntity<List<Money>>searchMoney(@RequestParam int date){
        Optional<List<Money>> money = Optional.ofNullable(moneyService.getMoneyByDate(date));
        return  money.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/money/searchTotal")
    public ResponseEntity<MoneyCal>searchTotalMoney(@RequestParam int date){
        Optional<MoneyCal> moneyCal = moneyService.getTotalMoneyByDate(date);
        return moneyCal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    /*
    @DeleteMapping("/money/delete")
    public ResponseEntity<Money> delete(@RequestParam int date){
        Optional<Money>money = moneyService.deleteMoney(date);
        return money.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    */
}
