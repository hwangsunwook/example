<?
// 1.�����Ͱ� �Է¾ȵȰ��� �ϳ��� ������
if(!$_POST['m_idx'] || !$_POST['m_pass']){
?>
<script>
    alert("��� �׸��� �� �ۼ��ϼž� �մϴ�.");
    history.back();
</script>
<?
    exit;
}else{
    // 2.���� ����
    // 3.�����ͺ��̽� ����
   
    $HOST = "localhost";
	$DBNAME = "handsom_db";
	$DBUSER = "root";
	$DBPW   = "apmsetup";

	// ���� ����
	$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

	// �����ͺ��̽� ����
	mysql_select_db("$DBNAME", $connect) or die(mysql_error());

    // 4.���� ����
    $m_idx = $_POST['m_idx'];
    $m_name = $_POST['m_name'];
    $m_email = $_POST['m_email'];
    $m_pass = $_POST['m_pass'];
    $m_memo = $_POST['m_memo'];

    // 5. ������ �б�
    $query = "select * from sample_memo where m_idx = '".$m_idx."'";
    $result = mysql_query($query, $connect);
    $data = mysql_fetch_array($result);

    // ������ �˻�
    // 6. �ش� ���� ���� ���
    if(!$data[m_idx]){
        ?>
        <script>
            alert("�޸� �������� �ʽ��ϴ�.");
            history.back();
        </script>
        <?
        exit;
    }

    // 7. ��й�ȣ�� �ٸ� ���
    if($data[m_pass] != $m_pass){
        ?>
        <script>
            alert("��й�ȣ�� ��Ȯ���� �ʽ��ϴ�.");
            history.back();
        </script>
        <?
        exit;
    }

    // 8. ���� ������ �ۼ�
    $query = "delete from sample_memo where m_idx = '".$m_idx."'";

    // 9. ������ ����
    mysql_query($query, $connect);

    // 10. ��� �ݱ�
    mysql_close($connect);

    echo '<a href="./memo.php">�޴�ȭ������</a>';
}
?>