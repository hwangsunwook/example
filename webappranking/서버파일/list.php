<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
<body>
<table style="width:100%;height:50px;border:5px #CCCCCC solid;">
    <tr>
        <td align="center" valign="middle" style="font-zise:15px;font-weight:bold;">Ranking</td>
    </tr>
</table>

<table style="width:100%;font-zise:12px;">
    <tr>
        <td height="20" align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">Number</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">Name</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">Score</td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;font-weight:bold;">
		Grade</td>        
    </tr>
<?php
// 3.디비와 연결
// 4.데이터베이스 선택

$HOST = "localhost";
$DBNAME = "db";
$DBUSER = "root";
$DBPW   = "password";

// 디비와 연결
$connect = mysql_connect("$HOST", "$DBUSER", "$DBPW") or die(mysql_error());

// 데이터베이스 선택
mysql_select_db("$DBNAME", $connect) or die(mysql_error());


// 여기서 부터 페이징 관련

// 5. 현재 페이지 변수정리

if($_GET[page] && $_GET[page] > 0){
    // 현재 페이지 값이 존재하고 0 보다 크면 그대로 사용
    $page = $_GET[page];
}else{
    // 그 외의 경우는 현재 페이지를 1로 설정
    $page = 1;
}

// 6.페이지 기본 설정
// 한 페이지에 보일 글 수
$page_row = 10;
// 한줄에 보여질 페이지 수
$page_scale = 10;


// 7. 전체 글 수 구하기
$query_total = "select count(*) as cnt from multi where 1";
$result_total = mysql_query($query_total, $connect);
$data_total = mysql_fetch_array($result_total);
$total_count = $data_total[cnt];

// 8. 전체 페이지 계산
$total_page  = ceil($total_count / $page_row);

// 9. 시작 열을 구함
$from_record = ($page - 1) * $page_row;

// 10. 페이징을 출력할 변수 초기화
$paging_str = "";

// 11. 처음 페이지 링크 만들기
if ($page > 1) {
    $paging_str .= "<a href='".$_SERVER[PHP_SELF]."?page=1'>처음</a>";
}

// 12. 페이징에 표시될 시작 페이지 구하기
$start_page = ( (ceil( $page / $page_scale ) - 1) * $page_scale ) + 1;

// 13. 페이징에 표시될 마지막 페이지 구하기
$end_page = $start_page + $page_scale - 1;
if ($end_page >= $total_page) $end_page = $total_page;

// 14. 이전 페이징 영역으로 가는 링크 만들기
if ($start_page > 1){
    $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".($start_page - 1)."'>이전</a>";
}

// 15. 페이지들 출력 부분 링크 만들기
if ($total_page > 1) {
    for ($i=$start_page;$i<=$end_page;$i++) {
        // 현재 페이지가 아니면 링크 걸기
        if ($page != $i){
            $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".$i."'><span>$i</span></a>";
        // 현재페이지면 굵게 표시하기
        }else{
            $paging_str .= " &nbsp;<b>$i</b> ";
        }
    }
}

// 16. 다음 페이징 영역으로 가는 링크 만들기
if ($total_page > $end_page){
    $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".($end_page + 1)."'>다음</a>";
}

// 17. 마지막 페이지 링크 만들기
if ($page < $total_page) {
    $paging_str .= " &nbsp;<a href='".$_SERVER[PHP_SELF]."?page=".$total_page."'>맨끝</a>";
}
// 여기까지 페이징

// 18.쿼리문 작성
$query = "select * from multi where 1 order by score desc limit ".$from_record.", ".$page_row;

// 19.쿼리문 적용하여 $result 에 대입
$result = mysql_query($query, $connect);

// 20.데이터 갯수 체크를 위한 변수 설정
$i = 0;

// 21.데이터가 있을 동안 반복해서 값을 한 줄씩 읽기
while($data = mysql_fetch_array($result)){
?>
    <tr>
        <td height="20" align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[number]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[name]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[score]?></td>
        <td align="center" valign="middle" style="border:1px #CCCCCC solid;"><?=$data[grade]?></td>        
    </tr>
<?
    // 22.데이터 갯수 체크를 위한 변수를 1 증가시킴
    $i++;
}

// 23.데이터가 하나도 없으면 
if($i == 0){
?>
    <tr>
        <td height="50" align="center" valign="middle" colspan="5" style="border:1px #CCCCCC solid;">자료가 하나도 없습니다.</td>
    </tr>
<?
}

// 24. 페이징 출력
if($paging_str){
?>
    <tr>
        <td height="30" align="center" valign="middle" colspan="5" style="border:1px #CCCCCC solid;"><?=$paging_str?></td>
    </tr>
<?
}

// 25.디비 닫기
mysql_close($connect);
?>

</table>


</body>
</html>