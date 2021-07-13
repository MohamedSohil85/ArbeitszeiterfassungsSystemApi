package org.mohamed.endpoints;

import org.mohamed.dto.Timeblock;
import org.mohamed.exceptions.ResourceNotFoundException;
import org.mohamed.repository.TimeblockRepository;
import org.mohamed.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class TimeBlockEndpoints {

    @Inject
    UserRepository userRepository;
    @Inject
    TimeblockRepository timeblockRepository;

    @Path("/saveTime/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Transactional
    public Timeblock saveTimeBlockByUserId(@PathParam("userId")Long userId, Timeblock timeblock)throws ResourceNotFoundException {
        return userRepository.findByIdOptional(userId).map(userdto -> {
         userdto.getTimeblocks().add(timeblock);
         timeblock.setUser(userdto);
         timeblockRepository.persist(timeblock);
         return timeblock;
        }).orElseThrow(()->new ResourceNotFoundException("User object not found"));
    }



}
