<?
$HOST = "localhost";
$DBNAME = "handsom_db";
$DBUSER = "root";
$DBPW   = "apmsetup";

// ���� ����
$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

// �����ͺ��̽� ����
mysql_select_db("$DBNAME", $connect) or die(mysql_error());

echo "��� ������ �����Ͽ����ϴ�.";

// ��� �ݱ�
mysql_close($connect);
?>