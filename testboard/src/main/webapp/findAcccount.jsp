<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>아이디/비밀번호 찾기</title>
</head>
<body>
<h2>아이디/비밀번호 찾기</h2>

<!-- 아이디 찾기 폼 -->
<h3>아이디 찾기</h3>
<form action="findAccountServlet" method="post">
    <div>
        <label>전화번호: </label>
        <input type="tel" name="phone" required>
    </div>
    <div>
        <label>이메일: </label>
        <input type="email" name="email" required>
    </div>
    <div>
        <button type="submit">아이디 찾기</button>
    </div>
</form>

<!-- 비밀번호 찾기 폼 -->
<h3>비밀번호 찾기</h3>
<form action="findAccountServlet" method="post">
    <div>
        <label>아이디: </label>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>이메일: </label>
        <input type="email" name="email" required>
    </div>
    <div>
        <button type="submit">비밀번호 재설정</button>
    </div>
</form>

</body>
</html>
