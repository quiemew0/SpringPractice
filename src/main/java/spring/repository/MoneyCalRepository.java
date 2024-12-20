package spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.domain.MoneyCal;

import java.util.Optional;

@Repository
public interface MoneyCalRepository extends JpaRepository<MoneyCal, Integer> {
    Optional<MoneyCal> findByDate(int date);

}
