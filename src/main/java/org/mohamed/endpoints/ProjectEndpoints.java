package org.mohamed.endpoints;

import org.hibernate.ResourceClosedException;
import org.mohamed.dto.Project;
import org.mohamed.dto.Userdto;
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
import java.util.List;
import java.util.Optional;

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

    @Path("/addTeam")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Transactional
    @POST
    @RolesAllowed("scrum_master")
    public Project addTeam(@QueryParam("projectName")String projectName,@QueryParam("username") String username)throws ResourceNotFoundException{
       return projectRepository.findProjectByName(projectName).map(project -> {
           Optional<Userdto>optionalUserdto=userRepository.findUserByName(username);
           Userdto userdto=optionalUserdto.orElseThrow(()->new ResourceClosedException("Object not found"));
           project.getTeam().add(userdto);
           projectRepository.persist(project);
           return project;
       }).orElseThrow(()->new ResourceNotFoundException("Project not found"));
    }


}
