<!DOCTYPE html>
<html>
<head>
    <title>플레이어 티어</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/header.jsp" %>
<main>
    <h3>플레이어 티어</h3>
    <form action="./app/springmvc/v2/player/tier" method="post">
        <select name="job">
            <option value="">선수여부</option>
            <option value="학생">실업선수출신</option>
            <option value="회사원">대학선수출신</option>
            <option value="기타">중등선수출신</option>
            <option value="기타">초등선수출신</option>
            <option value="기타">비선수출신</option>
        </select>
        <select name="job">
            <option value="">지도자부우승여부</option>
            <option value="학생">5회이상</option>
            <option value="회사원">3회이상</option>
            <option value="기타">1회</option>
            <option value="기타">없음</option>
        </select>
        <select name="job">
            <option value="">전국동호인대회우승여부</option>
            <option value="학생">5회이상</option>
            <option value="회사원">3회이상</option>
            <option value="기타">1회</option>
            <option value="기타">없음</option>
        </select>
        <button type="submit" class="button a">저장</button>
        </p>
    </form>
    <p class="warn">${msg}</p>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
</body>
</html>