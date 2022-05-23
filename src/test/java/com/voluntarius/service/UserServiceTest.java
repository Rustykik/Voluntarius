package com.voluntarius.service;

import com.voluntarius.utils.DBSetup;
import com.voluntarius.utils.TestData;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.voluntarius.utils.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService underTest;

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

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    void ShouldReturnBoolAccordingToCsvOnSignInViaLogin(int Id,
                                                        String fistname,
                                                        String lastname,
                                                        String login,
                                                        String exists,
                                                        String password,
                                                        String email) {
        //given

        //when

        //then
        assertThat(underTest.signIn(login, password)).isEqualTo(Boolean.valueOf(exists));

    }
//      TODO add email login later
//    @ParameterizedTest
//    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
//    void ShouldReturnBoolAccordingToCsvOnSignInViaEmail(int Id,
//                                                        String fistname,
//                                                        String lastname,
//                                                        String login,
//                                                        String exists,
//                                                        String password,
//                                                        String email) {
//        //given
//
//        //when
//
//        //then
//        assertThat(underTest.signIn(email, password)).isEqualTo(Boolean.valueOf(exists));
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    void ShouldReturnTrueOnlyNotExistingAccounts(int Id,
                                                 String fistname,
                                                 String lastname,
                                                 String login,
                                                 String exists,
                                                 String password,
                                                 String email) {
        //given

        //when

        //then
        assertThat(underTest.signUp(fistname, lastname, login, email, password)).isEqualTo(!Boolean.valueOf(exists));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    void ShouldAddNonExistingAccountAfterSignUp(int Id,
                                                String firstname,
                                                String lastname,
                                                String login,
                                                String exists,
                                                String password,
                                                String email) {
        //given
        User nonExistingUser = new User(firstname, lastname, login, password, email);
        //when
        underTest.signUp(firstname, lastname, login, email, password);

        //then
        assertThat(underTest.signIn(login, password)).isEqualTo(Boolean.TRUE);
        User user = assertDoesNotThrow(() -> underTest.getUser(login));
        assertThat(user).usingRecursiveComparison()
                .ignoringFields("id", "createdEvents", "subscribedEvents")
                .isEqualTo(nonExistingUser);
    }

    // tested in dao
//    @Test
//    void updateUser() {
//    }

    @Test
    void ShouldDoCorrectSubscribeToEvent() {
        //given
        User user = EMINEM;
        Event eventToSubscribe = CONCERT_TIVA;
        List<Event> shouldBe = new ArrayList<Event>(user.getSubscribedEvents());
        shouldBe.add(eventToSubscribe);
        //when
        assertDoesNotThrow(() -> underTest.subscribeToEvent(user.getId(), eventToSubscribe.getId()));
        //then
        User toTest = assertDoesNotThrow(() -> underTest.getUser(user.getId()));
        assertThat(toTest.getSubscribedEvents()).hasSameElementsAs(shouldBe);
    }


    @Test
    void unsubscribeFromEvent() {
        User user = EMINEM;
        Event eventToUnsubscribe = TOKIO;
        List<Event> shouldBe = new ArrayList<Event>(user.getSubscribedEvents());
        shouldBe.remove(eventToUnsubscribe);
        //when
        underTest.unsubscribeFromEvent(user.getId(), eventToUnsubscribe.getId());
        //then
        User toTest = assertDoesNotThrow(() -> underTest.getUser(user.getId()));
        assertThat(toTest.getSubscribedEvents()).hasSameElementsAs(shouldBe);
    }
//    TODO add this functionality
//    @Test
//    void setLikeToEvent() {
//    }

    // tested in dao
//    @Test
//    void createEvent() {
//    }

    @Test
    void ShouldGetUserById() {
        //given
        User user = EMINEM;

        //when
        User testUser = assertDoesNotThrow(() -> underTest.getUser(user.getId()));

        //then
        assertThat(testUser).isEqualTo(user);
    }

    @Test
    void getUsers() {
        //given
        List<User> testUsers = TestData.getTestUsers();

        //when
        List<User> toTestUsers = underTest.getUsers();

        assertThat(toTestUsers).usingRecursiveComparison().isEqualTo(testUsers);
    }

    /* testet at dao test

    @Test
    void getEvent() {

    }
    */

    /* testet at dao test

    @Test
    void getEvents() {
    }
    */

}