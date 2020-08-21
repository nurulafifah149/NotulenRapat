<?php
	include "koneksi.php";
	
	$id 	= $_POST['id'];
	
	class emp{}
	
	if (empty($id)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= "SELECT * FROM rapat WHERE id='".$id."'";
		$result = mysqli_query($connect, $query);
		$row 	= mysqli_fetch_array($result);
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->id = $row["id"];
			$response->tanggal = $row["tanggal"];
			$response->waktu = $row["waktu"];
			$response->lokasi = $row["lokasi"];
			$response->kehadiran = $row["kehadiran"];
			$response->topik = $row["topik"];
			$response->judul = $row["judul"];
			$response->isi = $row["isi"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>