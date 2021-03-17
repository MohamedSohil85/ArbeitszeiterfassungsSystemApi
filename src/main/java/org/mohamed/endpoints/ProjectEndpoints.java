package org.mohamed.endpoints;

import org.mohamed.dto.Project;
import org.mohamed.dto.User;
import org.mohamed.exceptions.ResourceNotFoundException;
import org.mohamed.repository.ProjectRepository;
import org.mohamed.repository.UserRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/api")
public class ProjectEndpoints {
   @Inject
   private ProjectRepository projectRepository;
   @Inject
   private UserRepository userRepository;

   @Path("/ProjectByUserId/{userId}")
   @Produces(value = MediaType.APPLICATION_JSON)
   @Transactional
   @POST
   @RolesAllowed("scrum_master")
   public Project createNewProject(@Valid Project project,Long userId)throws ResourceNotFoundException{
      return userRepository.findByIdOptional(userId).map(user -> {
           project.setCreatedDate(new Date());
           user.setProject(project);
           project.getTeam().add(user);
           projectRepository.persist(project);
           return project;
       }).orElseThrow(()->new ResourceNotFoundException("user with id :"+userId+" not found"));
   }

    @Produces(value = MediaType.APPLICATION_JSON)
    @Transactional
    @POST
    @RolesAllowed("scrum_master")
    @Path("/addTeam")
    public Response addTeam(@QueryParam("projectId")Long projectId, @QueryParam("userId")List<User>userIds){
      return projectRepository.findByIdOptional(projectId).map(project -> {
           project.setTeam(userIds);
           projectRepository.persist(project);
           return Response.ok(project).build();
       }).orElse(Response.noContent().build());
    }



}
