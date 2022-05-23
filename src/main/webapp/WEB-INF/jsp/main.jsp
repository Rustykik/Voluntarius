<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.voluntarius.database.dao.EventDao" %>
<%@ page import="com.voluntarius.database.dao.SingletonDataAccess" %>
<%@ page import="com.voluntarius.database.dao.EventDaoImpl" %>
<%@ page import="com.voluntarius.models.Event" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: rusty
  Date: 04.04.2022
  Time: 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Voluntarius</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;700&family=Ruslan+Display&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="../../style/style.css">
</head>
<body>

<header class="header">
    <div class="header-backgournd"></div>
    <div class="header-backgournd1"></div>

    <nav class="header-nav">
        <span class="main-logo">.Voluntarious</span>
        <ul class="header-nav-list">
            <li class="header-nav-list-item">
                <span class="header-nav-list-item-text">Location</span>
            </li>
            <li  class="header-nav-list-item">
                <span class="header-nav-list-item-text">Events</span>
            </li>
            <li class="header-nav-list-item">
                <span class="header-nav-list-item-text">Courses</span>
            </li>
        </ul>
    </nav>
    <div class="header-sign">
        <div class="header-buttons">
            <button class="header-button" onclick="location.href='/SignIn'">
                <span class="header-button-text sign-in">Sign In</span>
            </button>
            <button class="header-button" onclick="location.href='/SignUp'">
                <span class="header-button-text sign-up">Sign Up</span>
            </button>
        </div>
    </div>
</header>
<main>
    <section class="va-section-welcome">
        <div class="va-section-welcome-text">
            <p class="va-section-welcome-text-txt">
                Knowledge
                Networking
                Commuity
            </p>
        </div>
        <div class="va-section-welcome-event-back">

        </div>
        <div class="va-section-welcome-event-list">
            <% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); %>
            <% EventDao eventDao = new EventDaoImpl(SingletonDataAccess.getInstance().getSource()); %>
            <% List<Event> eventSet = eventDao.getCurrentEvents(LocalDateTime.now()); %>
            <% for (Event event : eventSet) { %>
            <div class="event">
                <div class="event-name, event-text">Event name:<br> <%= event.getEventName() %> </div>
                <div class="event-description, event-text">Description:<br> <%= event.getDescription() %> </div>
                <div class="event-start, event-text"> from <%= event.getEventStart().format(formatter) %> </div>
                <div class="event-end, event-text"> to <%= event.getEventEnd().format(formatter) %> </div>
                <div class="event-location, event-text"> Location: <%= event.getLocation() %> </div>
            </div>
            <%} %>
        </div>
        <!-- <div class="va-section-welcome-content">
            <ul class="va-section-welcome-list">
                <li class="va-section-welcome-list-item">
                    <span class="va-section-welcome-list-item-text">register on<br> event</span>
                </li>
                <li  class="va-section-welcome-list-item">
                    <span class="va-section-welcome-list-item-text">create<br> event</span>
                </li>
                <li class="va-section-welcome-list-item">
                    <span class="va-section-welcome-list-item-text">check out<br>liveevents</span>
                </li>
            </ul>
        </div> -->
    </section>
    <section class="va-section-courses">
        <div class="va-section-grid">
            <div class="courses">
                <div class="course">
                    <!-- <div class="description">
                        <p>
                            hfhf
                            hfhf
                            hfhfhf
                            hfhfhfhfhf
                            hfjhfhfjhkfjfjkflf
                            hffjjkhjfkhjkdfhjkd
                            hffjjkhjfkhjkdfhjkd
                            ffhfh
                        </p>
                    </div> -->
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bash/bash-original.svg" />
                    </div>
                    <div class="course-title">
                        <span>bash</span>
                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/cplusplus/cplusplus-line.svg" />
                    </div>
                    <div class="course-title">
                        <span>C++</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-plain-wordmark.svg" />

                    </div>
                    <div class="course-title">
                        <span>docker</span>
                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original-wordmark.svg" />

                    </div>
                    <div class="course-title">
                        <span>Git</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/go/go-original-wordmark.svg" />

                    </div>
                    <div class="course-title">
                        <span>Go</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" />

                    </div>
                    <div class="course-title">
                        <span>Java</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg" />

                    </div>
                    <div class="course-title">
                        <span>JavaScript</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linux/linux-original.svg" />

                    </div>
                    <div class="course-title">
                        <span>Linux</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/python/python-original-wordmark.svg" />

                    </div>
                    <div class="course-title">
                        <span>Python</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" />

                    </div>
                    <div class="course-title">
                        <span>Spring</span>

                    </div>
                </div>
                <div class="course">
                    <div class="course-logo">
                        <img class="course-logo-img" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/numpy/numpy-original.svg" />

                    </div>
                    <div class="course-title">
                        <span>NumPy</span>

                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<tail>
    <p>
        contact info
    </p>
</tail>
</body>
</html>