package com.example.cubomv.repository;
//

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import com.example.cubomv.model.User;
//
////@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//
//}

import java.util.List;

import javax.transaction.Transactional;

//package com.cubomv.cubomv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.cubomv.model.User;
//import com.cubomv.cubomv1.model.User;

//Utilizar NativeQuery para 
//inserção, 
//atualização, 
//consulta e 
//exclusão (SQL).
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	@Query(value="INSERT INTO Users (id, cpf, first_name, last_name) VALUES (?1, ?2, 3?, 4?)", nativeQuery=true)
//	User postNewUser(Long id, String cpf, String firstName, String lastName);
//	 @Modifying
	// post
	@Modifying
	@Query(value = "insert into Users (id, cpf, first_name, last_name) VALUES (:id,:cpf, :firstName, :lastName)", nativeQuery = true)
	@Transactional
	User insertNewUser(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName,
			@Param("cpf") String cpf);

	// put
	@Modifying
	@Query(value = "UPDATE Users as u SET u.cpf = :cpf, u.first_name = :firstName, u.last_name = :lastName where  u.id = :id)", nativeQuery = true)
	@Transactional
	User updateUserCustom(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName,
			@Param("cpf") String cpf);

	// delete
	@Modifying
	@Query(value = "DELETE FROM Users as u where  u.id = :id)", nativeQuery = true)
	@Transactional
	User deleteUserCustom(@Param("id") Long id);


	// get all
	@Modifying
	@Query(value = "select id, cpf, first_name, last_name from users", nativeQuery = true)
	List<User> getAllUsersCustom();

}