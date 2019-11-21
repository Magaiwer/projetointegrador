package projetointegrador.repository.helper;

import projetointegrador.model.Component;
import projetointegrador.model.Face;
import projetointegrador.model.Project;
import projetointegrador.model.Room;
import projetointegrador.repository.filter.ProjectFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentRepositoryImpl implements ComponentQueries {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Component> findByProject(ProjectFilter filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Component> componentCriteriaQuery = criteriaBuilder.createQuery(Component.class);

        /*Join tables*/
        Root<Component> fromComponent = componentCriteriaQuery.from(Component.class);
        Join<Component, Face> faceJoin = fromComponent.join("face");
        Join<Face, Room> roomJoin = faceJoin.join("room");
        Join<Room, Project> projectJoin = roomJoin.join("project");

        /*Filters*/

        if (filter != null) {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getProjectId() != null) {
                predicates.add(criteriaBuilder.equal(projectJoin.get("id"), filter.getProjectId()));
            }

            if (filter.getFaceId() != null) {
                predicates.add(criteriaBuilder.equal(faceJoin.get("id"), filter.getFaceId()));
            }

            if (filter.getRoomId() != null) {
                predicates.add(criteriaBuilder.equal(roomJoin.get("id"), filter.getRoomId()));
            }

            componentCriteriaQuery.where(
                    predicates.toArray(new Predicate[predicates.size()])
            );
        }

        TypedQuery<Component> componentTypedQuery = entityManager.createQuery(componentCriteriaQuery);

        return componentTypedQuery.getResultList();
    }
}
