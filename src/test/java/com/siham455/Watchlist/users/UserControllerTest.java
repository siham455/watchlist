package com.siham455.Watchlist.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.siham455.Watchlist.WatchlistApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WatchlistApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	private int port;
	private URI baseURI;

	@Autowired
	private TestRestTemplate restTemplate;

	private List<User> defaultUsers = new ArrayList<>() {
		{
			add(new User("John", "Alice"));
			add(new User("Bob", "Eve"));
			add(new User("Charlie", "Grace"));
		}
	};

	@MockBean
	private UserService userService;

	@BeforeEach
	void setUp() throws RuntimeException {
		this.baseURI = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost")
				.port(port)
				.path("api/users")
				.build()
				.toUri();

		when(userService.getAllUsers()).thenReturn(defaultUsers);
	}

	@Test
	@Description("POST /api/users creates new User")
	void createUser() {
		// Arrange
		User user = createNewUser();

		when(userService.createUser(any(User.class))).thenAnswer(invocation -> setId(invocation.getArgument(0)));

		// Act
		ResponseEntity<User> response = restTemplate.postForEntity(baseURI.toString(), user, User.class);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());
		verify(userService).createUser(any(User.class));
	}

	@Test
	@Description("GET /api/users returns all Users")
	void getAllUsers() throws URISyntaxException {
		// Act
		ResponseEntity<List<User>> response = restTemplate.exchange(baseURI, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> responseUsers = response.getBody();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(responseUsers);
		assertEquals(defaultUsers.size(), responseUsers.size());
		verify(userService).getAllUsers();
	}

	@Test
	@Description("GET /api/users/{id} returns matching User")
	void getUserById() {
		// Arrange
		User user = selectRandomUser();
		URI endpoint = getEndpoint(user);

		when(userService.getUserById(any(UUID.class))).thenReturn(user);

		// Act
		ResponseEntity<User> response = restTemplate.getForEntity(endpoint, User.class);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(user.getId(), response.getBody().getId());
		verify(userService).getUserById(user.getId());
	}

	@Test
	@Description("GET /api/users/{id} returns 404 for invalid User")
	void getInvalidUser() {
		// Arrange
		User user = createNewUser();
		URI endpoint = getEndpoint(user);

		when(userService.getUserById(any(UUID.class))).thenThrow(NoSuchElementException.class);

		// Act
		ResponseEntity<User> response = restTemplate.getForEntity(endpoint, User.class);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userService).getUserById(user.getId());
	}

	@Test
	@Description("PUT /api/users/{id} updates matching User")
	void updateUser() {
		// Arrange
		User user = selectRandomUser();
		URI endpoint = getEndpoint(user);

		when(userService.getUserById(any(UUID.class))).thenReturn(user);
		when(userService.updateUser(any(UUID.class), any(User.class))).thenReturn(user);

		// Act
		user.setFirstName("UpdatedFirstName");
		user.setLastName("UpdatedLastName");
		restTemplate.put(endpoint, user);

		ResponseEntity<User> response = restTemplate.getForEntity(endpoint, User.class);
		User updatedUser = response.getBody();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user.getId(), updatedUser.getId());
		assertEquals("UpdatedFirstName", updatedUser.getFirstName());
		assertEquals("UpdatedLastName", updatedUser.getLastName());
		verify(userService).getUserById(user.getId());
		verify(userService).updateUser(any(UUID.class), any(User.class));
	}

	@Test
	@Description("PUT /api/users/{id} returns 404 for invalid User")
	void updateInvalidUser() {
		// Arrange
		User user = createNewUser();
		URI endpoint = getEndpoint(user);

		when(userService.updateUser(any(UUID.class), any(User.class)))
				.thenThrow(new NoSuchElementException("User not found"));

		// Act
		RequestEntity<User> request = RequestEntity.put(endpoint).accept(MediaType.APPLICATION_JSON).body(user);
		ResponseEntity<User> response = restTemplate.exchange(request, User.class);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userService).updateUser(any(UUID.class), any(User.class));
	}

	@Test
	@Description("DELETE /api/users/{id} deletes matching User")
	void deleteUser() {
		// Arrange
		User user = selectRandomUser();
		URI endpoint = getEndpoint(user);

		when(userService.getUserById(any(UUID.class))).thenReturn(user);

		ResponseEntity<User> foundResponse = restTemplate.getForEntity(endpoint, User.class);

		doAnswer(invocation -> {
			return null;
		}).when(userService).deleteUser(any(UUID.class));
		when(userService.getUserById(any(UUID.class))).thenThrow(NoSuchElementException.class);

		// Act
		RequestEntity<?> request = RequestEntity.delete(endpoint).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<?> deletionResponse = restTemplate.exchange(request, Object.class);
		ResponseEntity<User> deletedResponse = restTemplate.getForEntity(endpoint, User.class);

		// Assert
		assertEquals(HttpStatus.OK, foundResponse.getStatusCode());
		assertTrue(deletionResponse.getStatusCode() == HttpStatus.OK
				|| deletionResponse.getStatusCode() == HttpStatus.NO_CONTENT);
		assertEquals(HttpStatus.NOT_FOUND, deletedResponse.getStatusCode());
		verify(userService).deleteUser(user.getId());
	}

	@Test
	@Description("DELETE /api/users/{id} returns 404 for invalid User")
	void deleteInvalidUser() {
		// Arrange
		User user = createNewUser();
		URI endpoint = getEndpoint(user);

		doThrow(new NoSuchElementException("User not found")).when(userService).deleteUser(any(UUID.class));

		// Act
		RequestEntity<?> request = RequestEntity.delete(endpoint).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<User> response = restTemplate.exchange(request, User.class);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(userService).deleteUser(user.getId());
	}

	private User selectRandomUser() {
		int randomIndex = new Random().nextInt(defaultUsers.size());

		return setId(defaultUsers.get(randomIndex));
	}

	private User createNewUser() {
		return setId(new User("John", "Alice"));
	}

	private URI getEndpoint(User user) {
		return appendPath(baseURI, user.getId().toString());
	}

	private URI appendPath(URI uri, String path) {
		return UriComponentsBuilder.fromUri(uri).pathSegment(path).build().encode().toUri();
	}

	private static User setId(User user) {
		ReflectionTestUtils.setField(user, "id", UUID.randomUUID());
		return user;
	}
}