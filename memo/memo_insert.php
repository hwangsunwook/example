<?
include "memo.php";
?>
<br/>
<form name="iForm" method="post" action="memo_insert_update.php" style="margin:0px;">
<table style="width:500px;height:50px;border:5px #CCCCCC solid;">
    <tr>
        <td align="center" valign="middle" style="font-zise:15px;font-weight:bold;">�޸� �ۼ��ϱ�</td>
    </tr>
</table>
<table style="width:500px;height:50px;border:0px;">
    <tr>
        <td align="center" valign="middle" style="width:100px;height:50px;">�̸�</td>
        <td align="center" valign="middle" style="width:400px;height:50px;"><input type="text" name="m_name" style="width:380px;"></td>
    </tr>
    <tr>
        <td align="center" valign="middle" style="width:100px;height:50px;">�̸���</td>
        <td align="center" valign="middle" style="width:400px;height:50px;"><input type="text" name="m_email" style="width:380px;"></td>
    </tr>
    <tr>
        <td align="center" valign="middle" style="width:100px;height:50px;">��й�ȣ</td>
        <td align="center" valign="middle" style="width:400px;height:50px;"><input type="password" name="m_pass" style="width:380px;"></td>
    </tr>
    <tr>
        <td align="center" valign="middle" style="width:100px;height:50px;">����</td>
        <td align="center" valign="middle" style="width:400px;height:50px;"><input type="text" name="m_memo" style="width:380px;"></td>
    </tr>
    <tr>
        <td align="center" valign="middle" colspan="2"><input type="submit" value="����"></td>
    </tr>
</table>
</form>