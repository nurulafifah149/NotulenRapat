<?php
	/* ===== www.dedykuncoro.com ===== */
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "root"; //sesuaikan username
	$password	= ""; //sesuaikan password
	$database	= "notulen"; //sesuaikan target databese
	
$connect = mysqli_connect($server, $user, $password, $database) or die ("Koneksi gagal!");
	/* ====== UNTUK MENGGUNAKAN MYSQLI DI UNREMARK YANG INI, YANG MYSQL_CONNECT DI REMARK ======= */
	// $con = mysqli_connect($server, $user, $password, $database);
	 //if (mysqli_connect_errno()) {
	//	echo "Gagal terhubung MySQL: " . mysqli_connect_error();
	//}

?>