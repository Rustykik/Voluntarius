package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class EventDaoTest {

    @Autowired
    private EventDao underTest;

    @Test
    void ShouldGetEvents() {
        // given

        // when
        List<Event> allEvents = underTest.getEvents();
        // then
        assertThat(allEvents).hasSize(4);
    }

    @Test
    void ShouldInsertEvent() {
        //given
        Event event = new Event("concert",
                "fyre concert LOL",
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(10),
                "Mayami",
                1);
        // when
        int insert = underTest.insertEvent(event);
        // then
        assertThat(insert).isEqualTo(1);
    }

    // TODO remake with custom exception
    @Test
    void ShouldThrowExceptionNotInsertEventOwnerIdNotExists() {
        //given
        Event event = new Event("concert",
                "fyre concert LOL",
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(10),
                "Mayami",
                100);
        // when
//        int insert = underTest.insertEvent(event);
        // then
        assertThatThrownBy(()->underTest.insertEvent(event)).isInstanceOf(DataAccessException.class);
    }

   @ParameterizedTest
   @CsvFileSource(resources = "/events.csv", numLinesToSkip = 1)
    void ShouldGetEventById(int id,
                      String eventName,
                      String description,
                      LocalDateTime eventStart,
                      LocalDateTime eventEnd,
                      String location,
                      int ownerId,
                      String exists) {
        // given
        Integer eventId = id;
        // when
        Optional<Event> event = underTest.getEventById(eventId);
        // then
        if (event.isPresent())
        {
            Event checkEvent = event.get();
            assertThat(checkEvent.getId()).isEqualTo(id);
            assertThat(checkEvent.getEventName()).isEqualTo(eventName);
            assertThat(checkEvent.getDescription()).isEqualTo(description);
            assertThat(checkEvent.getEventStart()).isEqualTo(eventStart);
            assertThat(checkEvent.getEventEnd()).isEqualTo(eventEnd);
            assertThat(checkEvent.getLocation()).isEqualTo(location);
            assertThat(checkEvent.getOwnerId()).isEqualTo(ownerId);
            assertThat(Boolean.valueOf(exists)).isEqualTo(true);
        } else {
            assertThat(Boolean.valueOf(exists)).isEqualTo(false);
        }

    }
    // TODO sometime it should not update event
    @Test
    void ShouldUpdateEvent() {
        //given
        Event oldEvent = underTest.getEventById(1).get();
        Event newEvent = new Event(
                oldEvent.getId(),
                oldEvent.getEventName(),
                "Changed description",
                oldEvent.getEventStart(),
                oldEvent.getEventEnd(),
                oldEvent.getLocation(),
                oldEvent.getOwnerId());
        // when
        underTest.updateEvent(newEvent);
        // then
        Event checkEvent = underTest.getEventById(oldEvent.getId()).get();
        assertThat(checkEvent).isEqualTo(newEvent);
    }

    // TODO should return List or unique event Name
    @Test
    void ShouldGetEventByEventName() {
        // given
        Map<String, List<Event>> eventsByEventName = underTest
                .getEvents()
                .stream()
                .collect(Collectors.groupingBy(Event::getEventName));
        // when

        // then
        for (Map.Entry<String, List<Event>> eventListByEventName : eventsByEventName.entrySet()) {
            List<Event> eventListToCheck = underTest.getEventsByEventName(eventListByEventName.getKey());
            assertThat(eventListToCheck).hasSameElementsAs(eventListByEventName.getValue());
        }
    }
    // TODO check that it gets all events
    @Test
    void getEventByOwnerId() {
        // given
        Map<Integer, List<Event>> eventsByOwnerId = underTest
                        .getEvents()
                        .stream()
                        .collect(Collectors.groupingBy(Event::getOwnerId));
        // when

        // then
        for (Map.Entry<Integer, List<Event>> eventListByOwner : eventsByOwnerId.entrySet()) {
            List<Event> eventListToCheck = underTest.getEventByOwnerId(eventListByOwner.getKey());
            assertThat(eventListToCheck).hasSameElementsAs(eventListByOwner.getValue());
        }
    }

    @Test
    void getSubscribedEvents() {
        // given
        Map<Integer, List<Event>> eventListsByUserSubscribtions = new HashMap<>();

        List<Event> expectedOwner6 = new ArrayList<>();
        expectedOwner6.add(underTest.getEventById(1).get());

        List<Event> expectedOwner5 = new ArrayList<>();
        expectedOwner5.add(underTest.getEventById(1).get());
        expectedOwner5.add(underTest.getEventById(2).get());

        List<Event> expectedOwner4 = new ArrayList<>();
        expectedOwner4.add(underTest.getEventById(1).get());

        eventListsByUserSubscribtions.put(6,expectedOwner6);
        eventListsByUserSubscribtions.put(5,expectedOwner5);
        eventListsByUserSubscribtions.put(4,expectedOwner4);

        // when
//        IntStream.range(1, 7).forEach( givenOwnerId -> {
//           assertThat(underTest.getSubscribedEvents(givenOwnerId))
//                   .hasSameElementsAs(eventListsByUserSubscribtions
//                           .getOrDefault(givenOwnerId, Collections.emptyList()));
//        });
        assertThat(underTest.getSubscribedEvents(1)).hasSameElementsAs(Collections.emptyList());
        assertThat(underTest.getSubscribedEvents(6)).hasSameElementsAs(expectedOwner6);
        assertThat(underTest.getSubscribedEvents(5)).hasSameElementsAs(expectedOwner5);
        assertThat(underTest.getSubscribedEvents(4)).hasSameElementsAs(expectedOwner4);

    }

    @Test
    void getEventsByLocation() {
        // given
        Map<String, List<Event>> eventsByLocation = underTest
                .getEvents()
                .stream()
                .collect(Collectors.groupingBy(Event::getLocation));
        // when

        // then
        for (Map.Entry<String, List<Event>> eventListByLocations : eventsByLocation.entrySet()) {
            List<Event> eventListToCheck = underTest.getEventsByLocation(eventListByLocations.getKey());
            assertThat(eventListToCheck).hasSameElementsAs(eventListByLocations.getValue());
        }
    }

    // TODO in before add specific current and not current events
    @Test
    void getCurrentEvents() {
        // given
        LocalDateTime time = LocalDateTime.now();
        List<Event> currentLists = underTest.getCurrentEvents(time);
        // when

        // then
        currentLists.stream().forEach(event -> {
            assertThat(event.getEventStart()).isBefore(time);
            assertThat(event.getEventEnd()).isAfter(time);
        });
    }

    // TODO test it but may be we dont need it
//    @Test
//    void getEventUserRelatedEvents() {
//    }
}