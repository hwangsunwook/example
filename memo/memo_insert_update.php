<?
// 데이터가 입력안된것이 하나라도 있으면
if(!$_POST['m_name'] || !$_POST['m_email'] || !$_POST['m_pass'] || !$_POST['m_memo']){
?>
<script>
    history.back();
</script>
<?
}else{
    
	$HOST = "localhost";
	$DBNAME = "handsom_db";
	$DBUSER = "root";
	$DBPW   = "apmsetup";

	// 디비와 연결
	$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

	// 데이터베이스 선택
	mysql_select_db("$DBNAME", $connect) or die(mysql_error());

    // 변수 정리
    $m_name = $_POST['m_name'];
    $m_email = $_POST['m_email'];
    $m_pass = $_POST['m_pass'];
    $m_memo = $_POST['m_memo'];
    $m_ip = $_SERVER['REMOTE_ADDR'];

    // 쿼리문 작성
    $query = "insert into sample_memo (m_name, m_email, m_pass, m_memo, m_ip, m_regdate) values ('$m_name', '$m_email', '$m_pass', '$m_memo', '$m_ip', now())";

    // 쿼리문 적용
    mysql_query($query, $connect);

    // 디비 닫기
    mysql_close($connect);

    echo '<a href="./memo.php">메뉴화면으로</a>';
}
?>