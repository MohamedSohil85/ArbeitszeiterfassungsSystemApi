package org.mohamed.endpoints;

import io.quarkus.panache.common.Sort;
import org.mohamed.dto.BacklogItem;
import org.mohamed.dto.User;
import org.mohamed.exceptions.ResourceNotFoundException;
import org.mohamed.model.Priority;
import org.mohamed.model.WorkStatus;
import org.mohamed.repository.BacklogItemRepo;
import org.mohamed.repository.SprintRepo;
import org.mohamed.repository.UserRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/api")
public class BacklogItemEndpoints{

@Inject
BacklogItemRepo backlogItemRepo;
@Inject
SprintRepo sprintRepo;
@Inject
UserRepository userRepository;

@Path("/BacklogItem/{sprintId}/user/{username}")
@Produces(value = MediaType.APPLICATION_JSON)
@POST
@Transactional
public Response addBacklogItem(@Valid BacklogItem backlogItem, @PathParam("sprintId")Long id,@PathParam("username")String username) throws ResourceNotFoundException{
    return sprintRepo.findByIdOptional(id).map(sprint -> {
        Optional<User>optionalUser=userRepository.findUserByName(username);
        User user=optionalUser.get();
        sprint.getSprintBacklog().add(backlogItem);
        backlogItem.setUser(user);
        backlogItem.setWorkStatus(WorkStatus.TO_DO);
        backlogItemRepo.persist(backlogItem);
        return Response.ok().build();
    }).orElse(Response.noContent().build());
}

    @Path("/BacklogItem/{Id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    @POST
    @Transactional
    @RolesAllowed({"scrum_master","developer"})
    public Response changeWorkstatus(@PathParam("Id")Long id,@Valid BacklogItem backlogItem){
     return backlogItemRepo.findByIdOptional(id).map(item ->{
         item.setWorkStatus(backlogItem.getWorkStatus());
         item.setTimeStart(backlogItem.getTimeStart());
         item.setTimeEnd(backlogItem.getTimeEnd());
         backlogItemRepo.persist(item);
         return Response.status(Response.Status.CREATED).build();
     }).orElse(Response.noContent().build());
    }


    @Path("/BacklogItems")
    @Produces(value = MediaType.APPLICATION_JSON)
    @GET
    @RolesAllowed("scrum_master")
    public List<BacklogItem> showTaskboard()throws ResourceNotFoundException{
    List<BacklogItem>items=backlogItemRepo.listAll();
    if (items.isEmpty()){
        throw new ResourceNotFoundException("List of Items is empty");
    }
    return items.stream().filter(backlogItem -> {
        return backlogItem.getPriority().equals(Priority.High);
    }).collect(Collectors.toList());
    }
    //count total worked hours
    // edit time


}
