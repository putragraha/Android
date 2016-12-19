-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2016 at 06:38 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `imageuploader`
--

-- --------------------------------------------------------

--
-- Table structure for table `imagetable`
--

CREATE TABLE IF NOT EXISTS `imagetable` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `imagetable`
--

INSERT INTO `imagetable` (`id`, `image`) VALUES
(14, 'uploads/34.jpg'),
(15, 'uploads/22.jpg'),
(16, 'uploads/23.jpg'),
(17, 'uploads/34.jpg'),
(18, 'uploads/42.jpg'),
(19, 'uploads/34.jpg'),
(20, 'uploads/23.jpg'),
(21, 'uploads/42.jpg'),
(22, 'uploads/.jpg'),
(23, 'uploads/43.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
