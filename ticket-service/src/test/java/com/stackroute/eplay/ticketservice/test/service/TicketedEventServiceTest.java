//package com.stackroute.eplay.ticketservice.test.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.stackroute.eplay.ticketservice.domain.TicketedEvent;
//import com.stackroute.eplay.ticketservice.exception.MovieEventAlreadyExistException;
//import com.stackroute.eplay.ticketservice.exception.TicketedEventAlreadyExistException;
//import com.stackroute.eplay.ticketservice.repositories.TicketedEventRepository;
//import com.stackroute.eplay.ticketservice.service.TicketedEventServiceImpl;
//
//@RunWith(SpringRunner.class)
//public class TicketedEventServiceTest {
//
//	@Mock
//	private TicketedEventRepository ticketedEventRepository;
//	private TicketedEvent ticketedEvent;
//	int id;
//	
//	@InjectMocks
//	private TicketedEventServiceImpl ticketedEventServiceImpl;
//	
//	@Before
//	public void setupMock() {
//		MockitoAnnotations.initMocks(this);
//		id =22;
//		ticketedEvent = new TicketedEvent(id,"shivangi",new Date(),new Date(),"patna","bihar","abc","play",100,1,"sing","22","shivangi","nmn","mnb","username");
//	}
//	
//	@Test
//	public void testMockCreation() {
//		assertNotNull("jpaRepository creation fails: use @injectMocks on movieServicempl", ticketedEventRepository);
//	}
//
//	@Test
//	public void getTicketedEventByIdTest() {
//		when(ticketedEventRepository.getTicketedEventById(id)).thenReturn(ticketedEvent);
//		assertEquals(ticketedEvent, ticketedEventServiceImpl.getTicketedEventById(id));
//	}
//	
//	@Test
//	public void saveTicketedEventTests() throws TicketedEventAlreadyExistException{
//		when(ticketedEventRepository.save(ticketedEvent)).thenReturn(ticketedEvent);
//		assertEquals(ticketedEvent, ticketedEventServiceImpl.saveTicketedEvent(ticketedEvent));
//	}
//	
//	@Test(expected = TicketedEventAlreadyExistException.class)
//	public void testSaveTicketedEventFailure() throws TicketedEventAlreadyExistException {
//		when(ticketedEventRepository.getTicketedEventById(id)).thenReturn(ticketedEvent);
//		ticketedEventServiceImpl.saveTicketedEvent(ticketedEvent);
//	}
//}
