<!DOCTYPE html>
<html>
<head>
    <title>플레이어</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/headerNav.jsp" %>
<main>
    <h3>플레이어 정보</h3>
    <ul class="info">
        <li><label>플레이어번호</label> ${sessionScope.PLAYER.playerId}</li>
        <li><label>이메일</label> ${sessionScope.PLAYER.email}</li>
        <li><label>이름</label> ${sessionScope.PLAYER.name}</li>
        <li><label>선출여부</label> ${sessionScope.PLAYER.playerLevel1}</li>
        <li><label>지도자부우승</label> ${sessionScope.PLAYER.playerLevel2}</li>
        <li><label>전국대회우승</label> ${sessionScope.PLAYER.playerLevel3}</li>
    </ul>
    <p><a href="./app/springmvc/v2/player/s/playerEdit" class="button a">개인정보 수정</a>
        <a href="./app/springmvc/v2/player/s/passwordForm" class="button a">비밀번호 수정</a>
        <a href="./app/springmvc/v2/article/playerArticles?playerId=${sessionScope.PLAYER.playerId}" class="button a">내가 쓴 글</a></p>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
</body>
</html>