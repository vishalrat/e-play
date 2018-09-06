package com.stackroute.eplay.recommendationservice.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.eplay.recommendationservice.domain.Movie;

public interface MovieRepository extends Neo4jRepository<Movie, Integer> {
	
	@Query("MATCH (m:Movie) WHERE m.name ={name} RETURN m")
	Movie findByName(@Param("name") String name);
	
	@Query("Match (m:Movie) WHERE m.id ={id} RETURN m")
	Movie findById(@Param("id") int id);
	
	@Query("Match (c:City) ,(m:Movie) where c.name={name} and m.id={movieId} with m,c merge (m)-[r:RELEASED_IN]->(c)")
	void releasedIn(@Param("name") String name,@Param("movieId")int movieId);
	
	@Query("MATCH (m:Movie)-[r:IS_OF_GENRE]->(g:Genre) where g.genreName={genreName} RETURN m")
	List<Movie> getMoviesByGenre(@Param("genreName") String genreName);
	
	@Query("MATCH (m:Movie)-[r:RELEASED_IN]->(c:City) where c.name={name} RETURN m")
	List<Movie> getMoviesByCity(@Param("name") String name);
	
	@Query("MATCH (c:City)<- [:RELEASED_IN]-(m:Movie)-[:IS_OF_GENRE]->(g:Genre) where g.genreName={genreName} and  c.name={name} RETURN m")
    List<Movie> getMovieByCityGenre(@Param("name") String name, @Param("genreName") String genreName);
}
