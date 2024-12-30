package spring.controller;


import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.domain.*;
import spring.service.MoneyService;
import spring.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MoneyController {


    private MoneyService moneyService;
    private UserService userService;
    private List<Optional<Money>> mlists = new ArrayList<>();

    @Autowired
    public MoneyController(MoneyService moneyService, UserService userService){
        this.moneyService = moneyService;
        this.userService =userService;
    }

    @PostMapping("/money/user")
    public ResponseEntity<Member>register(@RequestBody UserDTO userinfo){
        Optional<Member> user = userService.regUser(userinfo.getName(),userinfo.getAge());
        Member response = user.orElseThrow(() ->new IllegalArgumentException("error!"));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @PostMapping("/money/register/{id}")
    @Cacheable(value = "userid", key = "#id")
    public ResponseEntity<Money> register(@RequestBody MoneyDTO moneyinfo,@PathVariable("id") Long id){
        Optional<Money>money = moneyService.regMoney(moneyinfo.getDate(),moneyinfo.getSign(),moneyinfo.getAmount(),id);
        Money response = money.orElseThrow(()->new IllegalArgumentException("error!"));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/money/delete/{id}")
    @CacheEvict(value="userid",key ="#id")
    public ResponseEntity<Money> delete(@PathVariable Long id, @RequestParam int date){
        Optional<Money> money = moneyService.deleteMoney(id,date);
        Money response = money.orElseThrow(()-> new IllegalArgumentException("Error!"));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/money/show/{id}")
    public ResponseEntity<List<Money>> searchbyUserid(@PathVariable Long id){
        Optional<List<Money>> money = Optional.ofNullable(moneyService.getMoneyByUserid(id));
        List<Money> response = money.orElseThrow(()->new IllegalArgumentException("Error!"));
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }
/*
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body("illegal argument exception발생!!!!! "+ex.getMessage());
    }*/
/*
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
    } */


    }
