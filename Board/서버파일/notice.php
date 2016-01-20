<?php
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("notice",$conn)
  or die ("2");

   mysqli_query($conn,"SELECT * FROM board");  // board table

   session_start();

   // 쿼리문 생성
   $sql = "select * from board";

   // 쿼리 실행 결과를 $result에 저장
   $result = mysql_query($sql, $conn);

   // 반환된 전체 레코드 수 저장.
   $total_record = mysql_num_rows($result);
 
   // JSONArray 형식으로 만들기 위해서...
   echo "{\"status\":\"OK\",\"num_results\":\"$total_record\",\"results\":[";
 
   // 반환된 각 레코드별로 JSONArray 형식으로 만들기.
   for ($i=0; $i < $total_record; $i++)                    
   {
      // 가져올 레코드로 위치(포인터) 이동  
      mysql_data_seek($result, $i);       
        
      $row = mysql_fetch_array($result);
   echo "{\"number\":$row[number],\"title\":\"$row[title]\",\"content\":\"$row[content]\"}";
 
   // 마지막 레코드 이전엔 ,를 붙인다. 그래야 데이터 구분이 되니깐.  
   if($i<$total_record-1){
      echo ",";
   }
    
   }
   // JSONArray의 마지막 닫기
   echo "]}";
?>