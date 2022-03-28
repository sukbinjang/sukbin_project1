<!DOCTYPE html>
<html>
<head>
    <title>사용자</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="./css/article.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/springmvc/v2/headerNav.jsp" %>
<main>
    <h3>비밀번호 수정</h3>
<%-- 아래 form 태그에 id값을 줬음. 전달방식은 POST로 action에 정의된 updatePassword로 넘어가더라도 사용자는 주소창에서 내 비번 등에대한 정보를 볼 수 없음. (보안목적)--%>
    <form name="form1" action="./app/springmvc/v2/player/s/updatePassword"
          method="post">
<%--    placeholder : 해당 input 태그 안에 들어가는 힌트글자 (클릭하면 사라지는 글자들)
        required : 얘가 비워있으면 submit 안됨
        autofocus : 이 페이지로 넘어오면 자동으로 저 비밀번호창에 커서가 클릭돼서 깜빡임. --%>
    <p><input type="password" name="password" placeholder="현재 비밀번호" required
              autofocus/>
    </p>
    <p><input type="password" name="newPassword" placeholder="새 비밀번호"
              required/></p>
    <p>
    <p><input type="password" name="newPasswordConfirm" placeholder="새 비밀번호 확인"
              required/></p>
    <p>
        <button type="submit">저장</button>
<%--        아래 취소 버튼을 눌렀을 때 동작하는 onclick에 대한 정의. history라는 친구가 자바스크립트에서 쓰는 친구인데 말그대로 이전 정보라던가 그런 잡다한게 있음.
            그 history에서 back을 호출한거니까 말그대로 그냥 뒷페이지로 넘기는거. --%>
        <button type="button" onclick="history.back();" class="button b">취소
        </button>
    </p>
</form>
    <p class="warn">${msg}</p>
</main>
<%@ include file="/WEB-INF/jsp/springmvc/v2/footer.jsp" %>
<script>
    // var, let, const : 자바스크립트에서 쓰는 변수의 종류로 따로 자료형이 int char 이렇게는 없는데 대충 지한테 들어간 내용보고 지 자료형을 유추해서 동작한다.
    //
    var form1 = document.form1; //아까 10번 줄에서 <form>태그에다가 form1이라고 id값을 줬으니까 그 id값 그대로 그냥 박아버린거.
    // console.log(form1); 디버깅할때 좋은 방법으로 저게 뭔지 궁금하면 console.log()함수안에 저새끼를 집어넣고
    // 해당 페이지에 방문해서 저새끼를 동작시키고 크롬에서 F12를 눌러서 나오는 콘솔창에서의 내용을 확인하면 된다.

    //10번줄에서의 <form>을 submit했을때 바로 action으로 넘어가지말고 일단 안에 들어간 새 비밀번호들을 대조해보고
    //둘이 틀리면 새 비밀번호, 새 비밀번호 확인 두 칸 모두 비우고 다시 입력하도록 한다.
    //둘이 같으면 이 함수는 재껴지고 바로 action에 명시된 주소로 넘어간다.
    form1.onsubmit = function () {
        if (form1.newPassword.value != form1.newPasswordConfirm.value) {
            alert("새 비밀번호가 서로 다릅니다.");
            form1.newPassword.value = "";
            form1.newPasswordConfirm.value = "";
            form1.newPassword.focus();
            return false;
        }
    }
</script>
</body>
</html>