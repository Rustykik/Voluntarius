package com.voluntarius.database.dao;

import com.voluntarius.utils.DBSetup;
import com.voluntarius.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import static com.voluntarius.utils.TestData.JKIM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao underTest;

    @BeforeAll
    static void  init(@Autowired DBSetup dbSetup) {
        dbSetup.drop();
    }
    @BeforeEach
    void setup(@Autowired DBSetup dbSetup) throws IOException {
        dbSetup.create();
        dbSetup.fill();
    }

    @AfterEach
    void drop(@Autowired DBSetup dbSetup) {
        dbSetup.drop();
    }

    @Test
    void ShouldInsertUser() {
        // given
        User John = new User("John", "Bood", "Jbood", "tyur", "John@gmail.com");
        // when
        int inserts = underTest.insertUser(John);
        // then
        assertThat(inserts).isEqualTo(1);
    }

    // TODO Change later to custom exception User login already exists change login
    @Test
    void ShouldNotInsertExistingByLoginUserAndThrowException() {
        // given
        User user= JKIM;
        // when
//        int inserts = underTest.insertUser(John);
        // then
        assertThatThrownBy(()->underTest.insertUser(user)).isInstanceOf(DataAccessException.class);
    }

    @Test
    void ShouldGetAllUsers() {
        // given

        // when
        Collection<User> users = underTest.getUsers();
        // then
        assertThat(users).hasSize(6);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    void ShouldGetUserById(int id,
                           String expectedFirstname,
                           String expectedLastname,
                           String expectedLogin,
                           String exists) {
        // given

        // when
        Optional<User> user = underTest.getUserById(id);
        // then
        if (user.isPresent()) {
            assertThat(user.get().getId()).isEqualTo(id);
            assertThat(user.get().getFirstname()).isEqualTo(expectedFirstname);
            assertThat(user.get().getLastname()).isEqualTo(expectedLastname);
            assertThat(user.get().getLogin()).isEqualTo(expectedLogin);
            assertThat(Boolean.valueOf(exists)).isEqualTo(true);
        } else {
            assertThat(Boolean.valueOf(exists)).isEqualTo(false);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    void ShouldGetUserByLogin(int id,
                              String expectedFirstname,
                              String expectedLastname,
                              String expectedLogin,
                              String exists) {
        // given

        // when
        Optional<User> user = underTest.getUserByLogin(expectedLogin);
        // then
        if (user.isPresent()) {
            assertThat(user.get().getId()).isEqualTo(id);
            assertThat(user.get().getFirstname()).isEqualTo(expectedFirstname);
            assertThat(user.get().getLastname()).isEqualTo(expectedLastname);
            assertThat(user.get().getLogin()).isEqualTo(expectedLogin);
            assertThat(Boolean.valueOf(exists)).isEqualTo(true);
        } else {
            assertThat(Boolean.valueOf(exists)).isEqualTo(false);
        }

    }

    // TODO configure our john in beforeall
    @Test
    void ShouldUpdateUser() {
        // given
        String login = JKIM.getLogin();
        User oldUser = underTest.getUserByLogin(login).get();
        User newUser = new User(
                oldUser.getId(),
                "Johny",
                oldUser.getLastname(),
                "WHOJO",
                "password",
                oldUser.getEmail());
        // when
        int updatedUsers = underTest.updateUser(newUser);
        // then
        assertThat(updatedUsers).isEqualTo(1);
        User updatedUser = underTest.getUserById(oldUser.getId()).get();
        assertThat(updatedUser).isEqualTo(newUser);
    }

    //TODO custom exception login is already in use
    @Test
    void ShouldNotUpdateAndThrowExceptionUserLoginIsAlreadyUsed() {
        // given
        User oldUser = underTest.getUserByLogin("Eminem").get();
        User newUser = new User(
                oldUser.getId(),
                "Johny",
                oldUser.getLastname(),
                "KK",
                "password",
                oldUser.getEmail());
        //then
        assertThatThrownBy(()->underTest.updateUser(newUser)).isInstanceOf(DataAccessException.class);
    }

    // may be should be tested in service test

//    @Test
//    void ShouldUpdateSubscriptions() {
//        //given
//        Event event = new Event(1,
//                "Tokio fight",
//                "Large fight in tokyo with different creatures",
//                LocalDateTime.parse("1999-11-01T10:11:11"),
//                LocalDateTime.parse("1999-11-01T15:11:11"),
//                "Tokyo, Japan",
//                0);
//        User user = underTest.getUserById(1).get();
//        ArrayList<Event> subscribedEvents = new ArrayList<Event>(user.getSubscribedEvents());
//        //when
//        subscribedEvents.add(event);
//        underTest.updateSubscriptions(user);
//        //then
//        User userSubscribedOnEvent = underTest.getUserById(1).get();
//        assertThat(userSubscribedOnEvent.getSubscribedEvents()).hasSize(1);
//    }
//
//    @Test
//    void ShouldGetUsersSubscribedOnEvent() {
//
//    }
}