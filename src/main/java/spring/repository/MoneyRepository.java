package spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.Money;

import java.util.List;
import java.util.Optional;

//@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {
    Optional<Money> findByDate(int date);
    Optional<Money> findByUserId(Long userId);
    Page<Money> findAll(Pageable pageable);

    Optional<Money> findByUserIdAndDate(Long userId, int date);
    //Optional<Money> calbyDate(int date, int amount, String sign);
    List<Money> findByIsDeletedFalseAndDate(int date);
    List<Money> findByIsDeletedFalseAndUserId(Long id);
}