package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.User;
import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
   public Optional<User>findUserByToken(String token){
        return User.find("token",token).singleResultOptional();
    }
   public Optional<User>findUserByName(String userName){
       return User.find("userName",userName).singleResultOptional();
   }
}
