<!DOCTYPE html>
<html>
<head>
    <title>랜던매칭 신청</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/headerNav.jsp" %>
<main>
    <h3>랜덤매칭신청</h3>
    <form action="./app/springmvc/v2/matchService/s/startMatching" method="post">
        랜덤플레이어 매치<p></p>
        <button type="submit" class="button a">신청</button>

    </form>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
</body>
</html>