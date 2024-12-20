package spring.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.domain.Money;
import spring.domain.MoneyCal;
import spring.domain.MoneyDTO;
import spring.service.MoneyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MoneyController {


    @Autowired
    private MoneyService moneyService;
    private List<Optional<Money>> mlists = new ArrayList<>();
/*    public MoneyController(MoneyService moneyService){
        this.moneyService = moneyService;
    }**/
    @PostMapping("/money")
    public ResponseEntity<Money> register(@RequestBody MoneyDTO info) {
        Optional<Money> money = moneyService.regMoney(info.getDate(),info.getSign(),info.getAmount());
        Money response = money.orElseThrow(()-> new IllegalArgumentException("error!"));
        mlists.add(money);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        //return money.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/money/search") //날짜별 건수들 다 출력
    public ResponseEntity<List<Money>>searchMoney(@RequestParam int date){
        Optional<List<Money>> money = Optional.ofNullable(moneyService.getMoneyByDate(date));
        List<Money> response = money.orElseThrow(()->new IllegalArgumentException("error!"));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/money/searchAll") //전체 건수 다 출력
    public ResponseEntity<List<Optional<Money>>>searchMoneyAll(){
        List<Optional<Money>> response = new ArrayList<>(mlists);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/money/searchTotal") //날짜별 계산 출력
    public ResponseEntity<MoneyCal>searchTotalMoney(@RequestParam int date){
        Optional<MoneyCal> moneyCal = moneyService.getTotalMoneyByDate(date);
        MoneyCal response = moneyCal.orElseThrow(()->new IllegalArgumentException("error"));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/money/delete")
    public ResponseEntity<Money> delete(@RequestParam Long id){
        Optional<Money>money = moneyService.deleteMoney(id);
        Money response = money.orElseThrow(()->new IllegalArgumentException("error!"));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }
    }
