<?
// 1. ���� �޴� ��Ŭ���
include "memo.php";

// 2.���� �κ��� ����� �ְ�
?>
<br/>
<table style="width:1000px;height:50px;border:5px #CCCCCC solid;">
    <tr>
        <td align="center" valign="middle" style="font-zise:15px;font-weight:bold;">�޸� ��Ϻ���[����¡]</td>
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


// ���⼭ ���� ����¡ ����

// 5. ���� ������ ��������

if($_GET[page] && $_GET[page] > 0){
    // ���� ������ ���� �����ϰ� 0 ���� ũ�� �״�� ���
    $page = $_GET[page];
}else{
    // �� ���� ���� ���� �������� 1�� ����
    $page = 1;
}

// 6.������ �⺻ ����
// �� �������� ���� �� ��
$page_row = 10;
// ���ٿ� ������ ������ ��
$page_scale = 10;


// 7. ��ü �� �� ���ϱ�
$query_total = "select count(*) as cnt from sample_memo where 1";
$result_total = mysql_query($query_total, $connect);
$data_total = mysql_fetch_array($result_total);
$total_count = $data_total[cnt];

// 8. ��ü ������ ���
$total_page  = ceil($total_count / $page_row);

// 9. ���� ���� ����
$from_record = ($page - 1) * $page_row;

// 10. ����¡�� ����� ���� �ʱ�ȭ
$paging_str = "";

// 11. ó�� ������ ��ũ �����
if ($page > 1) {
    $paging_str .= "<a href='".$_SERVER[PHP_SELF]."?page=1'>ó��</a>";
}

// 12. ����¡�� ǥ�õ� ���� ������ ���ϱ�
$start_page = ( (ceil( $page / $page_scale ) - 1) * $page_scale ) + 1;

// 13. ����¡�� ǥ�õ� ������ ������ ���ϱ�
$end_page = $start_page + $page_scale - 1;
if ($end_page >= $total_page) $end_page = $total_page;

// 14. ���� ����¡ �������� ���� ��ũ �����
if ($start_page > 1){
    $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".($start_page - 1)."'>����</a>";
}

// 15. �������� ��� �κ� ��ũ �����
if ($total_page > 1) {
    for ($i=$start_page;$i<=$end_page;$i++) {
        // ���� �������� �ƴϸ� ��ũ �ɱ�
        if ($page != $i){
            $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".$i."'><span>$i</span></a>";
        // ������������ ���� ǥ���ϱ�
        }else{
            $paging_str .= " &nbsp;<b>$i</b> ";
        }
    }
}

// 16. ���� ����¡ �������� ���� ��ũ �����
if ($total_page > $end_page){
    $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".($end_page + 1)."'>����</a>";
}

// 17. ������ ������ ��ũ �����
if ($page < $total_page) {
    $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".$total_page."'>�ǳ�</a>";
}
// ������� ����¡

// 18.������ �ۼ�
$query = "select * from sample_memo where 1 order by m_idx desc limit ".$from_record.", ".$page_row;

// 19.������ �����Ͽ� $result �� ����
$result = mysql_query($query, $connect);

// 20.������ ���� üũ�� ���� ���� ����
$i = 0;

// 21.�����Ͱ� ���� ���� �ݺ��ؼ� ���� �� �پ� �б�
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
    // 22.������ ���� üũ�� ���� ������ 1 ������Ŵ
    $i++;
}

// 23.�����Ͱ� �ϳ��� ������ 
if($i == 0){
?>
    <tr>
        <td height="50" align="center" valign="middle" colspan="5" style="border:1px #CCCCCC solid;">�ڷᰡ �ϳ��� �����ϴ�.</td>
    </tr>
<?
}

// 24. ����¡ ���
if($paging_str){
?>
    <tr>
        <td height="30" align="center" valign="middle" colspan="5" style="border:1px #CCCCCC solid;"><?=$paging_str?></td>
    </tr>
<?
}

// 25.��� �ݱ�
mysql_close($connect);
?>
</table>