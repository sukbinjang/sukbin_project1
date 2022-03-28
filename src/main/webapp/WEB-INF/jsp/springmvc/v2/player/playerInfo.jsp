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
    <li><label>플레이어번호</label> <span>${player.playerId}</span></li>
    <li><label>이메일</label> <span>${player.email}</span></li>
    <li><label>이름</label> <span>${player.name}</span></li>
    <li><label>랭킹포인트</label> ${player.tier}</li>
    <li><label>랭킹포인트</label> ${player.rankingPoint}</li>
    <li><label>선출여부</label> ${player.playerLevel1}</li>
    <li><label>지도자부우승</label> ${player.playerLevel2}</li>
    <li><label>전국대회우승</label> ${player.playerLevel3}</li>
    <li><label>매칭상태</label> ${player.onMatch}</li>
  </ul>
  <p><a href="./app/springmvc/v2/article/playerArticles?playerId=${player.playerId}">
    ${player.name}의 글</a></p>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
</body>
</html>