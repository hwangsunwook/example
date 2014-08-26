<?
include "memo.php";

// 1.m_idx 값이 넘어왔는지 검사
if(!$_GET[m_idx]){
    ?>
    <script>
        alert("메모 고유번호가 없습니다.");
        history.back();
    </script>
    <?
    exit;
}else{
        // 2. m_idx 값이 넘어왔으면 $m_idx 에 대입
    $m_idx = $_GET[m_idx];
}

// 3.디비와 연결
// 4.데이터베이스 선택

$HOST = "localhost";
$DBNAME = "handsom_db";
$DBUSER = "root";
$DBPW   = "apmsetup";

// 디비와 연결
$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

// 데이터베이스 선택
mysql_select_db("$DBNAME", $connect) or die(mysql_error());

// 5.쿼리문 작성
$query = "select * from sample_memo where m_idx = '".$m_idx."'";

// 6.쿼리문 적용하여 $result 에 대입
$result = mysql_query($query, $connect);

// 7. $data 에 한행만 배열로 저장
$data = mysql_fetch_array($result);

// 8. m_idx 로 읽은 데이터가 존재 하는지 검사
if(!$data[m_idx]){
    ?>
    <script>
        alert("메모가 존재하지 않습니다.");
        history.back();
    </script>
    <?
    exit;
}

// 9. 삭제 form 작성
?>
<br/>
<form name="iForm" method="post" action="memo_delete_update.php" style="margin:0px;">
<input type="hidden" name="m_idx" value="<?=$data[m_idx]?>">
<table style="width:500px;height:50px;border:5px #CCCCCC solid;">
    <tr>
        <td align="center" valign="middle" style="font-zise:15px;font-weight:bold;">메모 삭제하기</td>
    </tr>
</table>
<table style="width:500px;height:50px;border:0px;">
    <tr>
        <td align="center" valign="middle" style="width:100px;height:50px;">비밀번호</td>
        <td align="center" valign="middle" style="width:400px;height:50px;"><input type="password" name="m_pass" style="width:380px;"></td>
    </tr>
    <tr>
        <td align="center" valign="middle" colspan="2"><input type="submit" value="삭제"></td>
    </tr>
</table>
</form>
<?
// 10.디비 닫기
mysql_close($connect);
?>