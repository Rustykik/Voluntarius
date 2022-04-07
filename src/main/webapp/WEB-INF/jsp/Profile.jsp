<%@ page import="com.voluntarius.models.User" %>
<%@ page import="com.voluntarius.models.Event" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: rusty
  Date: 04.04.2022
  Time: 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
		<div class="va-section-welcome-profile">
			<div class="va-section-welcome-profile-info">
                <% User user = (User) request.getSession().getAttribute("user"); %>
                <label class="va-section-welcome-sign-form-label">First Name </label>
                <%= user.getFirstname()%>
				<label class="va-section-welcome-sign-form-label">Last Name</label>
                <%= user.getLastname()%>
				<label class="va-section-welcome-sign-form-label">Email</label>
                <%= user.getEmail()%>
				<label class="va-section-welcome-sign-form-label">Login</label>
                <%= user.getLogin()%>
				<label class="va-section-welcome-sign-form-label">Password</label>
                <%= user.getPasswd()%>
			</div>
		</div>
        <div class="va-section-welcome-event-back">

        </div>
		<div class="va-section-welcome-event-profile">
				<form class="va-section-welcome-event-create" method="post">
					<label class="va-section-welcome-event-create-label">Event Name</label>
					<input class="va-section-welcome-event-create-input" type="text" placeholder="Event name" name="eventName">
					<label class="va-section-welcome-event-create-label ">Description</label>
					<textarea class="va-section-welcome-event-create-input-description" type="text" placeholder="Description..." name="description"></textarea>
					<label class="va-section-welcome-event-create-label">Start</label>
					<input class="va-section-welcome-event-create-input" type="datetime-local" placeholder="Event start" name="eventStart">
					<label class="va-section-welcome-event-create-label">End</label>
					<input class="va-section-welcome-event-create-input" type="datetime-local" placeholder="Event end" name="eventEnd">
					<label class="va-section-welcome-event-create-label">Location</label>
					<input class="va-section-welcome-event-create-input" type="text" placeholder="Location" name="location">
					<button class="va-section-welcome-event-create-button">Create</button>
				</form>
			<div class="va-section-welcome-event-list">
                <% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); %>
                <% for (Event event : user.eventSet) { %>
                    <div class="event">
                        <div class="event-name, event-text">Event name:<br> <%= event.getEventName() %> </div>
                        <div class="event-description, event-text">Description:<br> <%= event.getDescription() %> </div>
                        <div class="event-start, event-text"> from <%= event.getEventStart().format(formatter) %> </div>
                        <div class="event-end, event-text"> to <%= event.getEventEnd().format(formatter) %> </div>
                        <div class="event-location, event-text"> Location: <%= event.getLocation() %> </div>
                    </div>
                <%} %>
			</div>
		</div>
    </section>
</main>
</body>
</html>
