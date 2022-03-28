<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="count" value="${empty param.count? 20: param.count}"/>
<c:set var="page" value="${empty param.page? 1: param.page}"/>
<c:set var="maxPage" value="${Math.ceil(totalCount / count).intValue()}"/>
<!DOCTYPE html>
<html>
<head>
  <title>플레이어</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <base href="${pageContext.request.contextPath}/">
  <link rel="stylesheet" href="./css/article.css?ver=1">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/headerNav.jsp" %>
<main>
  <h3>플레이어 목록</h3>
  <p>총 ${totalCount}건, ${maxPage}페이지</p>
  <form name="form1" method="get">
    <p class="pagebar">
      <button id="btnPrev" type="submit" class="button b"
              <c:if test="${page <= 1}">disabled</c:if>>&lt;
      </button>
      <input type="text" name="page" value="${page}" min="1"
             max="${maxPage}" readonly/>
      <button id="btnNext" type="submit" class="button b"
              <c:if test="${page >= maxPage}">disabled</c:if>>&gt;
      </button>
      <input type="number" name="count" value="${count}" min="10" step="10"/>행씩
    </p>
  </form>
  <div class="list player">
    <div class="tableColumn">id</div>
    <div class="tableColumn">email</div>
    <div class="tableColumn">username</div>
    <c:forEach var="player" items="${playerList}">
      <div>${player.playerId}</div>
      <div><a
              href="./app/springmvc/v2/player/playerInfo?playerId=${player.playerId}">${player.email}</a>
      </div>
      <div>${player.name}</div>
    </c:forEach>
  </div>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
<script>
  var form1 = document.forms["form1"];
  document.getElementById("btnPrev").onclick = function () {
    form1["page"].value--;
  };
  document.getElementById("btnNext").onclick = function () {
    form1["page"].value++;
  };
  form1["count"].onchange = function () {
    form1.submit();
  }
</script>
</body>
</html>