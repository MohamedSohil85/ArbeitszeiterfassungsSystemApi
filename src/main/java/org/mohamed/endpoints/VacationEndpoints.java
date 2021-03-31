package org.mohamed.endpoints;

import org.mohamed.dto.Holiday;
import org.mohamed.repository.HolidayRepository;
import org.mohamed.repository.SicknessRepository;
import org.mohamed.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class VacationEndpoints {

    @Inject
    HolidayRepository holidayRepository;
    @Inject
    SicknessRepository sicknessRepository;
    @Inject
    UserRepository userRepository;

    @POST
    @Path("/Holiday/{userId}")
    @Transactional
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response enterHoliday (@Valid Holiday holiday,@PathParam("userId")Long id){
        return userRepository.findByIdOptional(id).map(userdto -> {
            userdto.setVacation(holiday);
            holidayRepository.persist(holiday);
            return Response.ok().build();
        }).orElse(Response.noContent().build());
    }



}
