<?php
$mode = $_POST['mode'];
$type = $_POST['type'];
$tel =  $_POST['tel'];
$pwd = $_POST['pwd'];
$id = $_POST['id'];

// �����Ͱ� �Է¾ȵȰ��� �ϳ��� ������
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("userinfo",$conn)
  or die ("2");

session_start();

// ������ �ۼ�
if ($type == "tel")  // ��ȭ��ȣ �˻�
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
else                 // ���̵� �˻�
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

// ��� �ݱ�
mysql_close($conn);

if ($rseult == 1)
   echo "1";   // �α��� ����.
else
   echo "0";   // �α��� ����.

?>