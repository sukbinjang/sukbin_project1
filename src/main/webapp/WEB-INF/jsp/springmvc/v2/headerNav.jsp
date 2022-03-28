<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>

    <a href="./"><img src="./images/atr.png" class="logo"/></a>
</header>
<ul class="nav">
    <li>
        <a href="./app/springmvc/v2/player/playerList">플레이어목록</a>
    </li>
    <li>
        <a href="./app/springmvc/v2/player/playerListRanked?tier=0">플레이어랭킹</a>
    </li>
    <li><a href="./app/springmvc/v2/article/articleList">게시판</a></li>
    <c:choose>
        <c:when test="${!empty sessionScope.PLAYER}"><!-- 로그인을 했으면 -->
            <li><a href="./app/springmvc/v2/scoreBoard/scoreList?playerId=${sessionScope.PLAYER.playerId}">내 전적</a></li>
            <li><a href="./app/springmvc/v2/player/playerStatus">매칭상태</a></li>
            <li><a href="./app/springmvc/v2/player/s/myInfo">${sessionScope.PLAYER.name}님</a></li>
            <li><a href="./app/springmvc/v2/player/s/logout">로그아웃</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="./app/springmvc/v2/player/loginForm">로그인</a></li>
            <li><a href="./app/springmvc/v2/player/joinForm">회원가입</a></li>
        </c:otherwise>
    </c:choose>
</ul>