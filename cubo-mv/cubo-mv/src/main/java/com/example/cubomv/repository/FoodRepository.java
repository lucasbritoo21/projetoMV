
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

import com.example.cubomv.model.Food;
import com.example.cubomv.model.User;
//import com.cubomv.cubomv1.model.User;

//Utilizar NativeQuery para 
//inserção, 
//atualização, 
//consulta e 
//exclusão (SQL).
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

//	@Query(value="INSERT INTO Users (id, cpf, first_name, last_name) VALUES (?1, ?2, 3?, 4?)", nativeQuery=true)
//	User postNewUser(Long id, String cpf, String firstName, String lastName);
//	 @Modifying
	// post
	@Modifying
	@Query(value = "INSERT INTO Foods (id, name) VALUES (:id,:name)", nativeQuery = true)
	@Transactional
	Food insertNewFood(@Param("id") Long id, @Param("name") String name);

	// put
	@Modifying
	@Query(value = "UPDATE Food as f SET f.name = :name WHERE  f.id = :id)", nativeQuery = true)
	@Transactional
	Food updateFoodCustom(@Param("id") Long id, @Param("name") String name);

	// delete
	@Modifying
	@Query(value = "DELETE FROM Foods as f WHERE  f.id = :id)", nativeQuery = true)
	@Transactional
	Food deleteFoodCustom(@Param("id") Long id);


	// get all
	@Modifying
	@Query(value = "select * from foods", nativeQuery = true)
	List<Food> getAllFoodsCustom();

}