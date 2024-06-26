package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Role;
import ru.studentproject.startbusiness.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.id = :roleId")
    List<User> findByRole( @Param("roleId") Long id);


}
