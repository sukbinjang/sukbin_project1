<%@ taglib prefix="e"
           uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<!DOCTYPE html>
<html>
<head>
    <title>플레이어 매칭상태</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/headerNav.jsp" %>
<main>
    <h1>티어 : ${nowPlayer.tier}</h1>
    <h1>랭킹포인트 : ${nowPlayer.rankingPoint}</h1>
    <form action="./app/springmvc/v2/player/playerStatus" method="get">
        <p>
            <c:if test="${nowPlayer.onMatch == 'empty'}">매칭을 신청하세요
                <a href="./app/springmvc/v2/matchservice/selectMatchForm">매칭신청하기</a>
            </c:if>
            <c:if test="${nowPlayer.onMatch == 'wait'}">매칭을 대기중입니다.</c:if>
            <c:if test="${nowPlayer.onMatch == 'match'}">매칭되었습니다.경기를 기록하세요
                <a href="./app/springmvc/v2/scoreBoard/s/scoreBoardForm">경기 기록하기</a>
            </c:if>


        </p>
    </form>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
</body>
</html>