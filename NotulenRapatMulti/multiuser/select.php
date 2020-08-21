<?php 
	include "koneksi.php";
	
	$query = "SELECT * FROM rapat ";
	$result = mysqli_query($connect, $query);
	
	$json = array();

	while($row = mysqli_fetch_assoc($result)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysql_close($connect);
	
?>