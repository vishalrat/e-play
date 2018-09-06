/*package com.stackroute.rsvp.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.eplay.rsvp.controllers.RSVPEventController;
import com.stackroute.eplay.rsvp.domain.Invitation;
import com.stackroute.eplay.rsvp.domain.RSVPEvent;
import com.stackroute.eplay.rsvp.services.RsvpCreateServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(RSVPEventController.class)
public class RSVPEventControllerTest {

	@Autowired
	private MockMvc rsvpCreateMockMvc;

	@MockBean
	private RsvpCreateServiceImpl rsvpCreateServiceImpl;

	@InjectMocks
	private RSVPEventController rsvpEventController = new RSVPEventController(rsvpCreateServiceImpl);
	Date d1 = new Date(2000, 11, 21);
	Date d2 = new Date(2000, 11, 21, 9, 30);
	Date d3 = new Date(2000, 11, 21);
	Date d4 = new Date(2000, 11, 21, 10, 30);
	Invitation Invitation = new Invitation(101, 21, "sandy", "sandeep", "abc@gmail.com", "9898989898", false,
			"sandeep");

	List<Invitation> listOfInvitation = new LinkedList<Invitation>();
	//listOfInvitation.(Invitation);

	RSVPEvent rsv = new RSVPEvent(1, "001", "vishal", d1, d2, d3, d4, "delhi", "delhi", "poster", "bday",
			"welcome to bday party", "none", "none", "none", 10, false, listOfInvitation);

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllRSVPEvents() throws Exception {
		when(rsvpCreateServiceImpl.getAllRsvpCreate()).thenReturn(null);
		rsvpCreateMockMvc.perform(get("/api/v1/rsvpEvents")).andExpect(status().isOk());

	}
}*/