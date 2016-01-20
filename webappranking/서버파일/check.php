<?php
$score = $_POST['score'];

// 데이터가 입력안된것이 하나라도 있으면
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("db",$conn)
  or die ("2");

session_start();

// 쿼리문 작성
$query = "select * from multi where score <= '$score' ";
$res = mysql_query($query,$conn) or die("query failed: ". mysql_error());

$rows = mysql_num_rows($res);

// 디비 닫기
mysql_close($conn);

if ($rows > 0)
   echo "1";   // 성공.
else
   echo "0";   // 실패.

?>