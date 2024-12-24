package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.domain.Member;

import java.util.Optional;

@Repository
public interface UserIdRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByid(int id);
    Optional<Member> findByName(String name);
}
