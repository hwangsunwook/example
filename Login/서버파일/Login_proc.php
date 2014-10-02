<?php
$mode = $_POST['mode'];
$type = $_POST['type'];
$tel =  $_POST['tel'];
$pwd = $_POST['pwd'];
$id = $_POST['id'];

// 데이터가 입력안된것이 하나라도 있으면
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("userinfo",$conn)
  or die ("2");

session_start();

// 쿼리문 작성
if ($type == "tel")  // 전화번호 검색
{
	$query = "SELECT tel, pwd FROM nuser WHERE tel = '$tel' AND pwd = '$pwd'";
	$res = mysql_query($query,$conn) or die("Login query failed: ". mysql_error());
	if(mysql_num_rows($res)) {
		$rseult = 1;
	}
	else {
		$rseult = 0;
	}
}
else                 // 아이디 검색
{
	$query = "SELECT tel, id, pwd FROM nuser WHERE tel = '$tel' AND id = '$id' AND pwd = '$pwd'";
	$res = mysql_query($query,$conn) or die("Login query failed: ". mysql_error());
	if(mysql_num_rows($res)) {
		$rseult = 1;
	}
	else {
		$rseult = 0;
	}
}

// 디비 닫기
mysql_close($conn);

if ($rseult == 1)
   echo "1";   // 로그인 성공.
else
   echo "0";   // 로그인 실패.

?>