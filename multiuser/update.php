<?php
	include "koneksi.php";
	
		$id 	= $_POST['id'];
		$tanggal = $_POST ['tanggal'];
		$waktu = $_POST ['waktu'];
		$lokasi = $_POST ['lokasi'];
		$kehadiran = $_POST ['kehadiran'];
		$topik = $_POST ['topik'];
		$judul = $_POST ['judul'];
		$isi = $_POST ['isi'];
	
	class emp{}
	
	if (empty($id) || empty($tanggal) || empty($waktu) || empty($lokasi) || empty($kehadiran) || empty($topik) || empty($judul) || empty($isi)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = "UPDATE rapat SET tanggal='".$tanggal."', waktu='".$waktu."',lokasi='".$lokasi."', kehadiran='".$kehadiran."', topik='".$topik."', judul='".$judul."', isi='".$isi."' WHERE id='".$id."'";
		$result = mysqli_query($connect, $query);
		
		if ($result) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di update";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error update Data";
			die(json_encode($response)); 
		}	
	}
?>