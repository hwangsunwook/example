<?
// �����Ͱ� �Է¾ȵȰ��� �ϳ��� ������
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

	// ���� ����
	$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

	// �����ͺ��̽� ����
	mysql_select_db("$DBNAME", $connect) or die(mysql_error());

    // ���� ����
    $m_name = $_POST['m_name'];
    $m_email = $_POST['m_email'];
    $m_pass = $_POST['m_pass'];
    $m_memo = $_POST['m_memo'];
    $m_ip = $_SERVER['REMOTE_ADDR'];

    // ������ �ۼ�
    $query = "insert into sample_memo (m_name, m_email, m_pass, m_memo, m_ip, m_regdate) values ('$m_name', '$m_email', '$m_pass', '$m_memo', '$m_ip', now())";

    // ������ ����
    mysql_query($query, $connect);

    // ��� �ݱ�
    mysql_close($connect);

    echo '<a href="./memo.php">�޴�ȭ������</a>';
}
?>