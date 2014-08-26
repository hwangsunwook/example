<?
$HOST = "localhost";
$DBNAME = "handsom_db";
$DBUSER = "root";
$DBPW   = "apmsetup";

// 디비와 연결
$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

// 데이터베이스 선택
mysql_select_db("$DBNAME", $connect) or die(mysql_error());

echo "디비 연결이 성공하였습니다.";

// 디비 닫기
mysql_close($connect);
?>