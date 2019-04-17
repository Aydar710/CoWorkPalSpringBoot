package com.example.demo.repositories.project;

import com.example.demo.models.Project;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAll();

    Project save(Project project);

    Optional<Project> findById(Long id);

    //TODO: Make sure this method works
    @Query(value = "select users.* from users, user_project where project_id = :id and users.id = user_id",
            nativeQuery = true)
    List<User> getAllProjectMembers(@Param("id") Long id);

//    @Query(value = "insert into user_project(project_id, user_id) values (:project.id, :user.id)",
//            nativeQuery = true)
//    void addMemberToProject(@Param("user") User user, @Param("project") Project project);

    //TODO: Make sure this method works
    @Query(value = "insert into user_project(project_id, user_id) values (:projectId, :userId)",
            nativeQuery = true)
    void addMemberToProject(@Param("userId") Long userId, @Param("projectId") Project projectId);

    /*TODO: Реализовать все эти методы:
    getAllAdminsProjects
    getAllAdmins
    addAdminToProject
    getAllMembersProjects
    findProjectByNameAndMainAdminId*/

    //Реализация метода getAllAdminsProjects в старом проекте
    List<Project> findAllByMainAdmin_Id(Long id);

    @Query(value = "select project.* from project, admin_project where admin_project.admin_id = :adminId and project_id = project.id", nativeQuery = true)
    List<Project> findAllProjectsWhereUserIsAdmin(@Param("adminId") Long adminId);

    Project findByNameAndMainAdmin_Id(String projectId, Long mainAdminId);

    List<Project> findAllByAdminListContains(User user);

    //Реализация метода getAllAdmins в старом проекте
    @Query(value = "select users.* from admin_project, users where project_id = :projectId and admin_id = users.id",
            nativeQuery = true)
    List<User> getAllProjectAdmins(@Param("projectId") Long projectId);


    @Query(value = "select users.* from project, users where project.id = :projectId and project.main_admin = users.id",
            nativeQuery = true)
    List<User> getProjectsMainAdmin(@Param("projectId") Long projectId);

}
