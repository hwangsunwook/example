<?php
$mode = $_POST['mode'];
$tel =  $_POST['tel'];
$id =  $_POST['id'];
$pwd = $_POST['pwd'];

// 데이터가 입력안된것이 하나라도 있으면
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("userinfo",$conn)
  or die ("2");

mysqli_query($conn,"SELECT * FROM nuser");
$sql = "select * from nuser";

session_start();

// 일단 무조건 삽입한다. 중복 체크는 나중에 처리.
$duble_check = 0;
$query = "SELECT tel FROM euser WHERE tel = '$tel'";
$res = mysql_query($query,$conn) or die("Login query failed: ". mysql_error());
if(mysql_num_rows($res)) {
	$duble_check = 1;
}

if ($duble_check == 1)
{
	echo "2";
}
else
{
	// 쿼리문 작성
	$query = "insert into nuser (tel, id, pwd) values ('$tel','$id','$pwd')";

	// 쿼리문 적용
	$rseult = mysql_query($query, $conn);
		
	// 디비 닫기
	mysql_close($conn);

	if ($rseult == 0)
	   echo "0";   // 중복 저장 됨.
	else
	   echo "1";   //정상적으로 저장 됨.
}

?>