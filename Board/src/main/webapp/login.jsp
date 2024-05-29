<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<form action="login" method="post">
    <div>
        <label>아이디: </label>
        <input type="text" name="username">
    </div>
    <div>
        <label>비밀번호: </label>
        <input type="password" name="password">
    </div>
    <div>
        <button type="submit">로그인</button>
    </div>
</form>

<div>
    <p>아이디가 없으신가요? <a href="register.jsp">회원가입</a></p>
    <p>아이디나 비밀번호를 잊으셨나요? <a href="findAccount.jsp">아이디/비밀번호 찾기</a></p>
</div>
</body>
</html>
