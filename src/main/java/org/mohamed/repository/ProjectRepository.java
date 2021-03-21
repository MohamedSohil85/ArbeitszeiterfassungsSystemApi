package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.Project;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ProjectRepository implements PanacheRepository<Project> {
   public Optional<Project>findProjectByName(String projectname){
        return Project.find("projectName",projectname).singleResultOptional();
    }
}
