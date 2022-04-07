<%--
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
        <div class="va-section-welcome-sign">
			<form class="va-section-welcome-sign-form" method="post">
				<label class="va-section-welcome-sign-form-label">First Name</label>
				<input class="va-section-welcome-sign-form-input" type="text" placeholder="first name" name="firstname">
				<label class="va-section-welcome-sign-form-label">Last Name</label>
				<input class="va-section-welcome-sign-form-input" type="text" placeholder="last name" name="lastname">
				<label class="va-section-welcome-sign-form-label">Email</label>
				<input class="va-section-welcome-sign-form-input" type="text" placeholder="email" name="email">
				<label class="va-section-welcome-sign-form-label">Login</label>
				<input class="va-section-welcome-sign-form-input" type="text" placeholder="Enter login" name="login"> 
				<label class="va-section-welcome-sign-form-label">Password</label>
				<input class="va-section-welcome-sign-form-input" type="text" placeholder="Enter password" name="password"> 
				<button class="va-section-welcome-sign-form-button" type="submit">Sign Up</button>
			</form>
        </div>
    </section>
</main>
</body>
</html>