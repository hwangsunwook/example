<?
include "memo.php";

// 1.m_idx ���� �Ѿ�Դ��� �˻�
if(!$_GET[m_idx]){
    ?>
    <script>
        alert("�޸� ������ȣ�� �����ϴ�.");
        history.back();
    </script>
    <?
    exit;
}else{
        // 2. m_idx ���� �Ѿ������ $m_idx �� ����
    $m_idx = $_GET[m_idx];
}

// 3.���� ����
// 4.�����ͺ��̽� ����

$HOST = "localhost";
$DBNAME = "handsom_db";
$DBUSER = "root";
$DBPW   = "apmsetup";

// ���� ����
$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

// �����ͺ��̽� ����
mysql_select_db("$DBNAME", $connect) or die(mysql_error());

// 5.������ �ۼ�
$query = "select * from sample_memo where m_idx = '".$m_idx."'";

// 6.������ �����Ͽ� $result �� ����
$result = mysql_query($query, $connect);

// 7. $data �� ���ุ �迭�� ����
$data = mysql_fetch_array($result);

// 8. m_idx �� ���� �����Ͱ� ���� �ϴ��� �˻�
if(!$data[m_idx]){
    ?>
    <script>
        alert("�޸� �������� �ʽ��ϴ�.");
        history.back();
    </script>
    <?
    exit;
}

// 9. ���� form �ۼ�
?>
<br/>
<form name="iForm" method="post" action="memo_delete_update.php" style="margin:0px;">
<input type="hidden" name="m_idx" value="<?=$data[m_idx]?>">
<table style="width:500px;height:50px;border:5px #CCCCCC solid;">
    <tr>
        <td align="center" valign="middle" style="font-zise:15px;font-weight:bold;">�޸� �����ϱ�</td>
    </tr>
</table>
<table style="width:500px;height:50px;border:0px;">
    <tr>
        <td align="center" valign="middle" style="width:100px;height:50px;">��й�ȣ</td>
        <td align="center" valign="middle" style="width:400px;height:50px;"><input type="password" name="m_pass" style="width:380px;"></td>
    </tr>
    <tr>
        <td align="center" valign="middle" colspan="2"><input type="submit" value="����"></td>
    </tr>
</table>
</form>
<?
// 10.��� �ݱ�
mysql_close($connect);
?>