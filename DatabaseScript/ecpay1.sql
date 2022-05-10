-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2022 at 07:00 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecpay1`
--

-- --------------------------------------------------------

--
-- Table structure for table `ecpay1`
--

CREATE TABLE `ecpay1` (
  `payID` int(255) NOT NULL,
  `payCardType` varchar(30) CHARACTER SET latin1 NOT NULL,
  `payCardNO` varchar(50) CHARACTER SET latin1 NOT NULL,
  `payExpiryDate` date NOT NULL,
  `payCVV` varchar(10) CHARACTER SET latin1 NOT NULL,
  `payDate` date NOT NULL,
  `payTotalAmount` decimal(10,2) NOT NULL,
  `payAmount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ecpay1`
--

INSERT INTO `ecpay1` (`payID`, `payCardType`, `payCardNO`, `payExpiryDate`, `payCVV`, `payDate`, `payTotalAmount`, `payAmount`) VALUES
(1, 'Master Card', '23456789', '2024-12-12', '345', '2021-07-14', '5600.00', '5400.00'),
(6, 'VISA', '456789', '2014-09-08', '678', '2022-06-05', '23000.00', '20000.00'),
(9, 'Master Card', '23445567', '2026-12-12', '122', '2021-12-12', '8700.00', '8400.00'),
(10, 'Master Card', '456788', '2014-09-08', '700', '2022-06-05', '23000.00', '20000.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ecpay1`
--
ALTER TABLE `ecpay1`
  ADD PRIMARY KEY (`payID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ecpay1`
--
ALTER TABLE `ecpay1`
  MODIFY `payID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
