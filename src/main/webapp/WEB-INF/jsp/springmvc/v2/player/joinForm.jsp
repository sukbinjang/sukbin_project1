<!DOCTYPE html>
<html>
<head>
    <title>플레이어</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/header.jsp" %>
<main>
    <h3>플레이어 등록</h3>
    <form action="./app/springmvc/v2/player/join" method="post">
        <p><input type="email" name="email" placeholder="이메일" required autofocus/>
        </p>
        <p><input type="password" name="password" placeholder="비밀번호" required/></p>
        <p><input type="text" name="name" placeholder="이름" required/></p>
        <p><input type="text" name="address" placeholder="주소" required/></p>
        <p><input type="text" name="phoneNum" placeholder="휴대폰 번호" required/></p>
        <select name="playerLevel1">
            <option value="">선수여부</option>
            <option type = "text" value="실업선수출신">실업선수출신</option>
            <option type = "text" value="대학선수출신">대학선수출신</option>
            <option type = "text" value="고등선수출신">고등선수출신</option>
            <option type = "text" value="중등선수출신">중등선수출신</option>
            <option type = "text" value="초등선수출신">초등선수출신</option>
            <option type = "text" value="슈퍼동호인">슈퍼동호인</option>
            <option type = "text" value="비선수출신">비선수출신</option>
        </select>
        <select name="playerLevel2">
            <option type = "text" value="">지도자부우승여부</option>
            <option type = "text" value="5회이상">5회이상</option>
            <option type = "text" value="3회이상">3회이상</option>
            <option type = "text" value="1회">1회</option>
            <option type = "text" value="없음">없음</option>
        </select>
        <select name="playerLevel3">
            <option type = "text" value="">전국동호인대회우승여부</option>
            <option type = "text" value="5회이상">5회이상</option>
            <option type = "text" value="3회이상">3회이상</option>
            <option type = "text" value="1회">1회</option>
            <option type = "text" value="1회입상">1회입상</option>
            <option type = "text" value="없음">없음</option>
        </select>
        <p>
            <button type="submit" class="button a">저장</button>
        </p>
    </form>
    <p class="warn">${msg}</p>
</main>
<%--<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>--%>
</body>
</html>