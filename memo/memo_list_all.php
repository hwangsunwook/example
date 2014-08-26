<?
// 1. 공통 메뉴 인클루드
include "memo.php";

// 2.제목 부분을 만들어 주고
?>
<br/>
<table style="width:1000px;height:50px;border:5px #CCCCCC solid;">
    <tr>
        <td align="center" valign="middle" style="font-zise:15px;font-weight:bold;">메모 목록보기</td>
    </tr>
</table>
<br/>
<table style="width:1000px;font-zise:12px;">
    <tr>
        <td height="20" align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">내용</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">이름</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">이메일</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">작성일</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">수정/삭제</td>
    </tr>
<?

$HOST = "localhost";
$DBNAME = "handsom_db";
$DBUSER = "root";
$DBPW   = "apmsetup";

// 디비와 연결
$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

// 데이터베이스 선택
mysql_select_db("$DBNAME", $connect) or die(mysql_error());

// 5.쿼리문 작성
$query = "select * from sample_memo where 1 order by m_idx desc";

// 6.쿼리문 적용하여 $result 에 대입
$result = mysql_query($query, $connect);

// 7.데이터 갯수 체크를 위한 변수 설정
$i = 0;

// 8.데이터가 있을 동안 반복해서 값을 한 줄씩 읽기
while($data = mysql_fetch_array($result)){
?>
    <tr>
        <td height="20" align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[m_memo]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[m_name]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[m_email]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=substr($data[m_regdate],5,11)?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><input type="button" value="수정" onClick="location.href='memo_modify.php?m_idx=<?=$data[m_idx]?>'"><input type="button" value="삭제" onClick="location.href='memo_delete.php?m_idx=<?=$data[m_idx]?>'"></td>
    </tr>
<?
    // 9.데이터 갯수 체크를 위한 변수를 1 증가시킴
    $i++;
}

// 10.데이터가 하나도 없으면 
if($i == 0){
?>
    <tr>
        <td height="50" align="center" valign="middle" colspan="5" style="border:1px #CCCCCC solid;">자료가 하나도 없습니다.</td>
    </tr>
<?
}

// 11.디비 닫기
mysql_close($connect);
?>
</table>