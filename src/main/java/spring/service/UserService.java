package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Member;
import spring.repository.UserIdRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserIdRepository userIdRepository;

    public UserService(UserIdRepository userIdRepository) {
        this.userIdRepository = userIdRepository;
    }

    public Optional<Member> regUser(String name, int age){
        Member member = new Member(name,age);
        Member savedmember = userIdRepository.save(member);
        return Optional.of(savedmember);
    }
}
