<!DOCTYPE html>
<html>
<head>
    <title>경기 기록하기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/headerNav.jsp" %>
<main>
    <h3>경기 기록하기</h3>
    <form action="./app/springmvc/v2/scoreBoard/s/addMatch" method="get">
        <input type="radio" name="result" value="w">승
        <input type="radio" name="result" value="l">패
        <br>
        <select name="score1">
            <option type = "text" value="6">6</option>
            <option type = "text" value="5">5</option>
            <option type = "text" value="4">4</option>
            <option type = "text" value="3">3</option>
            <option type = "text" value="2">2</option>
            <option type = "text" value="1">1</option>
            <option type = "text" value="0">0</option>
        </select>
        <select name="score2">
            <option type = "text" value="6">6</option>
            <option type = "text" value="5">5</option>
            <option type = "text" value="4">4</option>
            <option type = "text" value="3">3</option>
            <option type = "text" value="2">2</option>
            <option type = "text" value="1">1</option>
            <option type = "text" value="0">0</option>
        </select>
        </br>
        <p>
            <button type="submit" class="button a">저장</button>
            <button type="button" onclick="history.back();" class="button b">취소
            </button>
        </p>
    </form>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
</body>
</html>