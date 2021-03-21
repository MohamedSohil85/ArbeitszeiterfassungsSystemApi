package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.Userdto;
import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<Userdto> {
   public Optional<Userdto>findUserByToken(String token){
        return Userdto.find("token",token).singleResultOptional();
    }
   public Optional<Userdto>findUserByName(String userName){
       return Userdto.find("userName",userName).singleResultOptional();
   }
}
