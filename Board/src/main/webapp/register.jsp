<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>회원가입</h2>
<form action="register" method="post">
    <div>
        <label>아이디: </label>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>비밀번호: </label>
        <input type="password" name="password" required>
    </div>
    <div>
        <label>비밀번호 확인: </label>
        <input type="password" name="confirmPassword" required>
    </div>
    <div>
        <label>이메일: </label>
        <input type="email" name="email" required>
    </div>
    <div>
        <label>전화번호: </label>
        <input type="tel" name="phone" required>
    </div>
    <div>
        <label>성별: </label>
        <input type="radio" name="gender" value="male" required> 남자
        <input type="radio" name="gender" value="female" required> 여자
    </div>
    <div>
        <button type="submit">회원가입</button>
    </div>
</form>

<div>
    <p>이미 계정이 있으신가요? <a href="login.jsp">로그인</a></p>
</div>
</body>
</html>
