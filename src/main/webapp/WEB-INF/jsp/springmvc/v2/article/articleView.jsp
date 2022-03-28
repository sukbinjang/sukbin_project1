<!DOCTYPE html>
<html>
<head>
    <title>게시글</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/headerNav.jsp" %>
<main>
    <h3>게시글 보기</h3>
    <p><a href="${sessionScope.listPage}" class="button a">목록</a>
        <a href="./app/springmvc/v2/article/s/articleEdit?articleId=${article.articleId}"
           class="button a">수정</a>
        <%-- 하단에 있는 "삭제"라는 글씨가 쓰여진 a 태그에 id값을 줬다.(btnDel)
            그 이유는 이 태그가 적힌 <body>태그의 맨 밑단에 Javascript 스크립트를 하나 정의해
            btnDel이라는 아이디를 가진 태그를 누르면 삭제할지에 대한 다이얼로그를 띄우게 하기 위함. 25번줄 참조--%>
        <a id="btnDel"
           href="./app/springmvc/v2/article/s/deleteArticle?articleId=${article.articleId}"
           class="button a">삭제</a>
        <%--    <a id="btnDel"--%>
        <%--       href="./app/springmvc/v2/article/s/deleteArticle?articleId=${article.articleId}" onclick="delContent()">삭제</a>--%>
    </p>
    <hr/>
    <p>${article.articleId}. <span class="title">${article.titleHtml}</span></p>
    <p><a
            href="./app/springmvc/v2/player/playerInfo?userId=${article.playerId}">${article.name}</a>
        / ${article.udate}</p>
    <hr/>
    <p>${article.contentHtml}</p>
    <hr/>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
<script>
    // 기본적으로 document라는 큰 컨테이너에서 btnDel이라는 아이디를 가진 컴포넌트를 하나 가져와 거기에 onclick속성을 바꿔주는 구조. (삭제할지에 대한 다이얼로그가 뜨도록)
    // 예를 누르면 true가 반환되어 다시 위쪽 14번줄에서 실제로 삭제를 하는 URL이 담긴 href로 넘어가서 삭제를 진행한다.

    //아래 세개 다 동일 기능으로 동작한다. (람다냐, function이냐 const냐의 차이.)
    // document.getElementById("btnDel").onclick = function () {
    //     return confirm("삭제합니까? from 원본"); //JS에서 confirm() : Y/N 다이얼로그 출력
    // }
    // const delContent = () => {
    //     return confirm("삭제합니까? from const");
    // }
    document.getElementById("btnDel").onclick = () => {
        //아래와 동일한 내용 (좀더 전개해서 보면 이렇다 이런 느낌)
        // if(confirm("삭제합니까? from lambda expression"))
        //     return true;
        // else return false;
        return confirm("삭제합니까? from 람다식");
    }
</script>
</body>
</html>