/*
package com.stackroute.eplay.controllertest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stackroute.eplay.streams.MovieStreams;
import com.stackroute.eplay.streams.ShowStreams;
import com.stackroute.eplay.upstreamservice.UpStreamServiceApplication;
import com.stackroute.eplay.upstreamservice.domain.Movie;
import com.stackroute.eplay.upstreamservice.domain.Show;
import com.stackroute.eplay.upstreamservice.service.UpStreamService; 

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { UpStreamServiceApplication.class })
@WebAppConfiguration
public class ControllerTest {

	@Autowired
	UpStreamService upStreamService;
	private TestRestTemplate restTemplate = new TestRestTemplate();
	HttpEntity<?> entity;
	Show show;
	Show show1;
	Movie movie;
	Movie movie1;
	
	@Before
	public void setupShow() {
		show = new Show();
		show.setShowId(1);
		entity = new HttpEntity<Show>(show);
		restTemplate.exchange("http://localhost:8080/api/v1/upstream/show", HttpMethod.POST,
				entity, String.class);
	}
	@Before
	public void setupMovie() {
		movie = new Movie();
		movie.setId(100);
		entity= new HttpEntity<Movie>(movie);
		restTemplate.exchange("http://localhost:8080/api/v1/upstream/movie", HttpMethod.POST,
				entity, String.class);
	}

	@StreamListener(ShowStreams.INPUT)
	public void showListener(@Payload Show show2) {
		show1=show2;
	}
	
	@StreamListener(MovieStreams.INPUT)
	public void movieListener(@Payload Movie movie2) {
		movie1= movie2;
	}
	
	@Test
	public void testShow() {
		assertEquals(show.getShowId(), show1.getShowId());
	}
	
	@Test
	public void testMovie() {
		assertEquals(movie.getId(), movie1.getId());
	}

} */
