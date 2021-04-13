package org.mohamed.endpoints;

import org.mohamed.dto.Project;
import org.mohamed.dto.Sprint;
import org.mohamed.dto.Timeblock;
import org.mohamed.exceptions.ResourceNotFoundException;
import org.mohamed.repository.ProjectRepository;
import org.mohamed.repository.SprintRepo;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Optional;

@Path("/api")
public class SprintEndpoints {

    @Inject
    ProjectRepository projectRepository;
    @Inject
    SprintRepo sprintRepo;

    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("scrum_master")
    @Path("/Sprint/{projectId}")
    public Sprint createSprint(@Valid Sprint sprint,@PathParam("projectId") Long id) throws ResourceNotFoundException {
        Optional<Project>optionalProject=projectRepository.findByIdOptional(id);
        Project project=optionalProject.orElseThrow(()-> new ResourceNotFoundException("not object with this id is found"));
        project.getSprintList().add(sprint);
        sprint.setProject(project);
        sprintRepo.persistAndFlush(sprint);
        return sprint;
    }




}
