-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2020 at 12:51 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `notulen`
--

-- --------------------------------------------------------

--
-- Table structure for table `kepala`
--

CREATE TABLE `kepala` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kepala`
--

INSERT INTO `kepala` (`id`, `username`, `password`, `email`) VALUES
(1, 'kepala', 'kepala', ''),
(2, 'labor', 'labor', ''),
(3, 'k1', 'k1', ''),
(4, 'lea', 'lea', ''),
(5, 'keplabor', 'keplabor', '');

-- --------------------------------------------------------

--
-- Table structure for table `rapat`
--

CREATE TABLE `rapat` (
  `id` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` time NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `kehadiran` varchar(70) NOT NULL,
  `topik` varchar(50) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `isi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rapat`
--

INSERT INTO `rapat` (`id`, `tanggal`, `waktu`, `lokasi`, `kehadiran`, `topik`, `judul`, `isi`) VALUES
(1, '2020-02-02', '18:10:10', 'lijdiw', '69 org', 'rapat bulanan', 'tanah', 'ini lisi'),
(6, '2020-08-19', '09:09:00', 'kkkk', 'haduie', 'tttoopp', 'jjuudd', 'isss'),
(8, '2020-08-03', '09:12:09', 'padang', 'hadir 10', 'bulanan', 'judull', 'isisiisisi');

-- --------------------------------------------------------

--
-- Table structure for table `sekretaris`
--

CREATE TABLE `sekretaris` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sekretaris`
--

INSERT INTO `sekretaris` (`id`, `username`, `password`, `email`) VALUES
(1, 'sekre', 'sekre', 's@gmail.com'),
(2, 's1', 's1', ''),
(3, 'sekretaris', 'sekretaris', ''),
(4, 'sek', 'sek', ''),
(5, 'sekretaris2', 'sekretaris2', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(1, 'saya', '1234', 'saya@gmail.com'),
(2, 'admin1', 'admin1', 'admin1@gmail.com'),
(3, 'admin2', 'admin2', ''),
(4, 'admin', 'admin', 'admin@gmail.com'),
(5, 'admin4', 'admin4', ''),
(6, 'user', 'user', 'user'),
(7, 'kita', 'kita', 'kita'),
(8, 'a1', 'a1', ''),
(9, 'satu', 'satu', ''),
(10, 'anggotaa', 'anggotaa', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kepala`
--
ALTER TABLE `kepala`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rapat`
--
ALTER TABLE `rapat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sekretaris`
--
ALTER TABLE `sekretaris`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kepala`
--
ALTER TABLE `kepala`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `rapat`
--
ALTER TABLE `rapat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `sekretaris`
--
ALTER TABLE `sekretaris`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
