<?
// 1.데이터가 입력안된것이 하나라도 없으면
if(!$_POST['m_idx'] || !$_POST['m_pass']){
?>
<script>
    alert("모든 항목을 다 작성하셔야 합니다.");
    history.back();
</script>
<?
    exit;
}else{
    // 2.디비와 연결
    // 3.데이터베이스 선택
   
    $HOST = "localhost";
	$DBNAME = "handsom_db";
	$DBUSER = "root";
	$DBPW   = "apmsetup";

	// 디비와 연결
	$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

	// 데이터베이스 선택
	mysql_select_db("$DBNAME", $connect) or die(mysql_error());

    // 4.변수 정리
    $m_idx = $_POST['m_idx'];
    $m_name = $_POST['m_name'];
    $m_email = $_POST['m_email'];
    $m_pass = $_POST['m_pass'];
    $m_memo = $_POST['m_memo'];

    // 5. 데이터 읽기
    $query = "select * from sample_memo where m_idx = '".$m_idx."'";
    $result = mysql_query($query, $connect);
    $data = mysql_fetch_array($result);

    // 데이터 검사
    // 6. 해당 글이 없는 경우
    if(!$data[m_idx]){
        ?>
        <script>
            alert("메모가 존재하지 않습니다.");
            history.back();
        </script>
        <?
        exit;
    }

    // 7. 비밀번호가 다른 경우
    if($data[m_pass] != $m_pass){
        ?>
        <script>
            alert("비밀번호가 정확하지 않습니다.");
            history.back();
        </script>
        <?
        exit;
    }

    // 8. 삭제 쿼리문 작성
    $query = "delete from sample_memo where m_idx = '".$m_idx."'";

    // 9. 쿼리문 적용
    mysql_query($query, $connect);

    // 10. 디비 닫기
    mysql_close($connect);

    echo '<a href="./memo.php">메뉴화면으로</a>';
}
?>