<?php
$conn = mysql_connect("localhost:port","root","password");
if (!$conn) die("1");

$mysql=mysql_select_db("notice",$conn)
  or die ("2");

   mysqli_query($conn,"SELECT * FROM board");  // board table

   session_start();

   // ������ ����
   $sql = "select * from board";

   // ���� ���� ����� $result�� ����
   $result = mysql_query($sql, $conn);

   // ��ȯ�� ��ü ���ڵ� �� ����.
   $total_record = mysql_num_rows($result);
 
   // JSONArray �������� ����� ���ؼ�...
   echo "{\"status\":\"OK\",\"num_results\":\"$total_record\",\"results\":[";
 
   // ��ȯ�� �� ���ڵ庰�� JSONArray �������� �����.
   for ($i=0; $i < $total_record; $i++)                    
   {
      // ������ ���ڵ�� ��ġ(������) �̵�  
      mysql_data_seek($result, $i);       
        
      $row = mysql_fetch_array($result);
   echo "{\"number\":$row[number],\"title\":\"$row[title]\",\"content\":\"$row[content]\"}";
 
   // ������ ���ڵ� ������ ,�� ���δ�. �׷��� ������ ������ �Ǵϱ�.  
   if($i<$total_record-1){
      echo ",";
   }
    
   }
   // JSONArray�� ������ �ݱ�
   echo "]}";
?>