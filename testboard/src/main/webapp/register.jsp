<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <script type="text/javascript">
        function showSuccessAlert() {
            alert("회원가입이 성공했습니다!");
        }

        function showFailureAlert() {
            alert("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
    </script>
</head>
<body>
<h2>회원가입</h2>
<form action="register" method="post" onsubmit="return onSubmit()">
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

<script type="text/javascript">
    function onSubmit() {
        // 여기서 실제로 서버로 전송하기 전에 비밀번호 확인 등의 검증을 수행할 수 있습니다.
        // 이 예제에서는 단순히 성공/실패 알림만을 처리합니다.
        var success = confirm("회원가입을 진행하시겠습니까?");
        if (success) {
            // 서버에서 회원가입 성공/실패 여부를 처리하도록 합니다.
            // 서버에서 응답에 따라 showSuccessAlert 또는 showFailureAlert 함수를 호출합니다.
        }
        return success;
    }
</script>
</body>
</html>
