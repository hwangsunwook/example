<?php
$number =  $_POST['number'];
$name =  $_POST['name'];
$score =  $_POST['score'];
$grade = $_POST['grade'];

// 데이터가 입력안된것이 하나라도 있으면
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("db",$conn)
  or die ("2");

session_start();

$query = "delete from multi where number = 5";
$rseult = mysql_query($query, $conn);

for($count = 5 ; $count > $number; $count--){ 
	$query = "update multi set number = '$count' where number = '$count'-1";
	$rseult = mysql_query($query, $conn);
}

$query = "insert into multi (number, name, score, grade) values ('$number','$name','$score','$grade')";
$rseult = mysql_query($query, $conn);

$query = "SELECT * from multi ORDER BY score desc";
$result = mysql_query($query);
	
// 디비 닫기
mysql_close($conn);

if ($rseult == 0)
   echo "0";   // 중복 저장 됨.
else
   echo "1";   //정상적으로 저장 됨.

?>