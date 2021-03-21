package org.mohamed.endpoints;

import org.mohamed.dto.Project;
import org.mohamed.exceptions.ResourceNotFoundException;
import org.mohamed.repository.ProjectRepository;
import org.mohamed.repository.UserRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/api")
public class ProjectEndpoints {
   @Inject
   ProjectRepository projectRepository;
   @Inject
   UserRepository userRepository;

   @Path("/ProjectByUserId/{userId}")
   @Produces(value = MediaType.APPLICATION_JSON)
   @Transactional
   @POST
   @RolesAllowed("scrum_master")
   public Project createNewProject(@Valid Project project,@PathParam("userId") Long userId)throws ResourceNotFoundException{
      return userRepository.findByIdOptional(userId).map(user -> {
           project.setCreatedDate(new Date());
           user.setProject(project);
           project.getTeam().add(user);
           projectRepository.persist(project);
           return project;
       }).orElseThrow(()->new ResourceNotFoundException("user with id :"+userId+" not found"));
   }




}
