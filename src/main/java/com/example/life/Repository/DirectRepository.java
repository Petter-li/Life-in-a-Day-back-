package com.example.life.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.life.domain.Location;
@Repository
public interface DirectRepository extends JpaRepository<Location, Integer >{
	//通过用户名来查找
	@Query(nativeQuery=true,value="SELECT * FROM district WHERE district.name LIKE CONCAT('%',:name,'%') LIMIT 10")
	List<Location> findByNameLike(@Param("name") String name);
}
