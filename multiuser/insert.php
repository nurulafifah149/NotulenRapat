<?php
	include "koneksi.php";
		//$id = $_POST['id'];
		$tanggal = $_POST ['tanggal'];
		$waktu = $_POST ['waktu'];
		$lokasi = $_POST ['lokasi'];
		$kehadiran = $_POST ['kehadiran'];
		$topik = $_POST ['topik'];
		$judul = $_POST ['judul'];
		$isi = $_POST ['isi'];
	
	class emp{}
	
	if (empty($tanggal) || empty($waktu) || empty($lokasi) || empty($kehadiran) || empty($topik) || empty($judul) || empty($isi)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		
		$query = "INSERT INTO rapat (`id`, `tanggal`, `waktu`, `lokasi`, `kehadiran`, `topik`, `judul`, `isi`) VALUES(0,'".$tanggal."', '".$waktu."', '".$lokasi."', '".$kehadiran."', '".$topik."', '".$judul."', '".$isi."')";

		$result = mysqli_query($connect, $query);
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
?>