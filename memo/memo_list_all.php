<?
// 1. ���� �޴� ��Ŭ���
include "memo.php";

// 2.���� �κ��� ����� �ְ�
?>
<br/>
<table style="width:1000px;height:50px;border:5px #CCCCCC solid;">
    <tr>
        <td align="center" valign="middle" style="font-zise:15px;font-weight:bold;">�޸� ��Ϻ���</td>
    </tr>
</table>
<br/>
<table style="width:1000px;font-zise:12px;">
    <tr>
        <td height="20" align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">����</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">�̸�</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">�̸���</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">�ۼ���</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">����/����</td>
    </tr>
<?

$HOST = "localhost";
$DBNAME = "handsom_db";
$DBUSER = "root";
$DBPW   = "apmsetup";

// ���� ����
$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

// �����ͺ��̽� ����
mysql_select_db("$DBNAME", $connect) or die(mysql_error());

// 5.������ �ۼ�
$query = "select * from sample_memo where 1 order by m_idx desc";

// 6.������ �����Ͽ� $result �� ����
$result = mysql_query($query, $connect);

// 7.������ ���� üũ�� ���� ���� ����
$i = 0;

// 8.�����Ͱ� ���� ���� �ݺ��ؼ� ���� �� �پ� �б�
while($data = mysql_fetch_array($result)){
?>
    <tr>
        <td height="20" align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[m_memo]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[m_name]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[m_email]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=substr($data[m_regdate],5,11)?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><input type="button" value="����" onClick="location.href='memo_modify.php?m_idx=<?=$data[m_idx]?>'"><input type="button" value="����" onClick="location.href='memo_delete.php?m_idx=<?=$data[m_idx]?>'"></td>
    </tr>
<?
    // 9.������ ���� üũ�� ���� ������ 1 ������Ŵ
    $i++;
}

// 10.�����Ͱ� �ϳ��� ������ 
if($i == 0){
?>
    <tr>
        <td height="50" align="center" valign="middle" colspan="5" style="border:1px #CCCCCC solid;">�ڷᰡ �ϳ��� �����ϴ�.</td>
    </tr>
<?
}

// 11.��� �ݱ�
mysql_close($connect);
?>
</table>