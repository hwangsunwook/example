<?php
$mode = $_POST['mode'];
$tel =  $_POST['tel'];
$id =  $_POST['id'];
$pwd = $_POST['pwd'];

// �����Ͱ� �Է¾ȵȰ��� �ϳ��� ������
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("userinfo",$conn)
  or die ("2");

mysqli_query($conn,"SELECT * FROM nuser");
$sql = "select * from nuser";

session_start();

// �ϴ� ������ �����Ѵ�. �ߺ� üũ�� ���߿� ó��.
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
	// ������ �ۼ�
	$query = "insert into nuser (tel, id, pwd) values ('$tel','$id','$pwd')";

	// ������ ����
	$rseult = mysql_query($query, $conn);
		
	// ��� �ݱ�
	mysql_close($conn);

	if ($rseult == 0)
	   echo "0";   // �ߺ� ���� ��.
	else
	   echo "1";   //���������� ���� ��.
}

?>