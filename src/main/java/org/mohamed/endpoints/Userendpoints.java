package org.mohamed.endpoints;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.panache.common.Sort;
import lombok.extern.slf4j.Slf4j;
import org.mohamed.dto.Role;
import org.mohamed.dto.User;
import org.mohamed.exceptions.ResourceNotFoundException;
import org.mohamed.model.MemberStatus;
import org.mohamed.repository.RoleRepository;
import org.mohamed.repository.UserRepository;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Path("/api")
public class Userendpoints {

    @Inject
    UserRepository userRepository;
    @Inject
    RoleRepository roleRepository;
    @Inject
    Mailer mailer;

    @GET
    @Path("/users")
    @Produces(value = MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<User>loadUsers() throws ResourceNotFoundException{
        List<User>users=userRepository.listAll(Sort.by("lastName"));
        if (users.isEmpty()){
            throw new ResourceNotFoundException("List of users is empty");
        }
        return users;
    }


    @POST
    @Transactional
    @Path("/user")
    @Produces(value= MediaType.APPLICATION_JSON)
    public Response addNewUser(@Valid User user){
        log.info("create new User");
        List<User>users=userRepository.listAll();
        for(User temp:users){
            if(temp.getLastName().equalsIgnoreCase(user.getLastName()))
                if (temp.getEmail().equalsIgnoreCase(user.getEmail()))
                  if(temp.getUserName().equalsIgnoreCase(user.getUserName())){
                    return Response.status(Response.Status.FOUND).build();
                }
        }
        String token= UUID.randomUUID().toString();
        user.setToken(token);
        user.setMemberStatus(MemberStatus.INACTIVE);
        mailer.send(Mail.withText(user.getEmail(), "Confirmation from System, Please click the following Link to continue registration :\n","http://localhost:8080/api/confirmUserAccount?token="+user.getToken()));
        userRepository.persist(user);
        return Response.ok(user).build();
    }
    @POST
    @Transactional
    @Path("/confirmUserAccount")
    @Produces(value= MediaType.APPLICATION_JSON)
    public User addPassword(@Valid User user, @QueryParam("token")String token)throws ResourceNotFoundException{
       log.info("add password to user");
        return userRepository.findUserByToken(token).map(existuser ->{
          String password=user.getPassword();
          String encodePassword=BcryptUtil.bcryptHash(password);
          existuser.setPassword(encodePassword);
          existuser.setMemberStatus(MemberStatus.ACTIVE);
          userRepository.persist(existuser);
          return existuser;
        }).orElseThrow(()->new ResourceNotFoundException("Object not found"));
    }
    @Path("/roleByUserId/{userId}")
    @POST
    @Transactional
    @Produces(value= MediaType.APPLICATION_JSON)
    public Response addRole(@Valid Role role, @PathParam("userId")Long id){
        Optional<User>optionalUser=userRepository.findByIdOptional(id);
        if (optionalUser.isEmpty()){
            return Response.noContent().build();
        }
        User user=optionalUser.get();
        user.getRoles().add(role);
        user.setMemberStatus(MemberStatus.ACTIVE);
        role.setUser(user);
        roleRepository.persist(role);
        return Response.status(Response.Status.CREATED).build();
    }



   @POST
   @Transactional
   @Path("blockUserByUserName/{username}")
   @Produces(value= MediaType.APPLICATION_JSON)
   @RolesAllowed("admin")
   public Response blockUserByUsername(@PathParam("username")String username){
        return userRepository.findUserByName(username).map(existUser->{
            existUser.setMemberStatus(MemberStatus.BLOCKED);
            existUser.setToken(null);
            existUser.setPassword(null);
            existUser.setUserName("Blocked User");
            mailer.send(Mail.withText(existUser.getEmail(),"Notification !","Notification : Dear User,\n your Account has been blocked \n please contact your Adminstrator"));
            userRepository.persist(existUser);
            return Response.ok("blocked User").build();
        }).orElse(Response.noContent().build());


   }


}
