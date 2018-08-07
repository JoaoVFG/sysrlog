package JoaoVFG.com.github.repositories.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import JoaoVFG.com.github.entity.security.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
