-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2017 at 05:36 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rock_the_vote`
--

-- --------------------------------------------------------

--
-- Table structure for table `admindetails`
--

CREATE TABLE `admindetails` (
  `Aid` int(4) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `age` varchar(10) DEFAULT NULL,
  `occupation` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admindetails`
--

INSERT INTO `admindetails` (`Aid`, `username`, `email`, `password`, `age`, `occupation`) VALUES
(1, 'charan', 'charan@gmail.com', '123456', '', ''),
(15, 'kiran', 'kiran@gmail.com', '12345678', '35', 'student'),
(14, 'gurpreet', 'gur@g.com', '12345678', NULL, NULL),
(13, 'simran', 'sdilbag672@gmail.com', '47231', '14', 'student'),
(2, 'manveen', 'mnvnsandhu@gmail.com', 'manveen12', NULL, NULL),
(3, 'gurman', 'gd@gmail.com', '12345678', '22', 'null'),
(4, 'gurpreet', 'gurpreet@gmail.com', '12345', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `admin_result`
--

CREATE TABLE `admin_result` (
  `A_Rid` int(5) NOT NULL,
  `poll_id` int(11) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `answer` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_result`
--

INSERT INTO `admin_result` (`A_Rid`, `poll_id`, `user_id`, `answer`) VALUES
(1, 3, 'charan@gmail.com', 'yes'),
(16, 35, 'sdilbag672@gmail.com', 'medicine'),
(2, 1, 'gd@gmail.com', 'game'),
(14, 3, 'charan@gmail.com', 'no'),
(15, 1, 'sdilbag672@gmail.com', 'game'),
(13, 2, 'charan@gmail.com', 'Bill Clinton'),
(3, 1, 'charan@gmail.com', 'fruit'),
(4, 3, 'charan@gmail.com', 'no'),
(17, 35, 'charan@gmail.com', 'no'),
(18, 35, 'charan@gmail.com', 'no'),
(19, 35, 'charan@gmail.com', 'no'),
(20, 35, 'charan@gmail.com', 'no'),
(21, 35, 'charan@gmail.com', 'no'),
(22, 35, 'charan@gmail.com', 'no'),
(23, 35, 'charan@gmail.com', 'no'),
(24, 35, 'charan@gmail.com', 'no'),
(25, 35, 'charan@gmail.com', 'no'),
(26, 36, 'charan@gmail.com', 'yes'),
(27, 36, '1', 'yes'),
(28, 36, '1', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `c_id` int(10) NOT NULL,
  `Pid` int(10) NOT NULL,
  `email_id` varchar(40) DEFAULT NULL,
  `comment` varchar(150) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`c_id`, `Pid`, `email_id`, `comment`) VALUES
(1, 3, 'demo@gmail.com', 'excellent'),
(2, 3, 'charanghumman@gmail.com', 'good'),
(3, 3, 'charan@gmail.com', 'well posted poll...!!ðŸ˜Š'),
(9, 1, 'charan@gmail.com', 'nice ...'),
(10, 1, 'charan@gmail.com', 'hello'),
(11, 1, 'sdilbag672@gmail.com', 'kushvi'),
(12, 35, 'sdilbag672@gmail.com', 'it is basically a subject that deals with living organisms');

-- --------------------------------------------------------

--
-- Table structure for table `polldetails`
--

CREATE TABLE `polldetails` (
  `Pid` int(5) NOT NULL,
  `Aid` int(5) DEFAULT NULL,
  `date` varchar(10) NOT NULL,
  `time_from` varchar(10) NOT NULL,
  `time_to` varchar(20) NOT NULL,
  `poll_question` varchar(100) NOT NULL,
  `poll_o1` varchar(50) NOT NULL,
  `poll_o2` varchar(50) NOT NULL,
  `poll_o3` varchar(50) NOT NULL,
  `poll_o4` varchar(50) NOT NULL,
  `poll_type` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `polldetails`
--

INSERT INTO `polldetails` (`Pid`, `Aid`, `date`, `time_from`, `time_to`, `poll_question`, `poll_o1`, `poll_o2`, `poll_o3`, `poll_o4`, `poll_type`) VALUES
(1, NULL, '2017-05-11', '15:29', '17:29', 'what is football?', 'fruit', 'country', 'game', 'school', 'candidate'),
(35, NULL, '2017-05-11', '0:14', '16:14', 'what is biology', 'subject', 'game', 'medicine', '', 'candidate'),
(36, 1, '2017-05-11', '15:58', '17:58', 'what is question', '', '', '', '', 'opinion'),
(2, 1, '2017-05-08', '12:45', '16:45', 'who is the president of america?', 'Brack Obama', 'Donald Trump', 'Bill Clinton', 'Pranab Mukharjee', 'candidate'),
(3, NULL, '28/4/2017', '13:23', '14:23', 'Today is monday', '', '', '', '', 'opinion');

-- --------------------------------------------------------

--
-- Table structure for table `poll_user`
--

CREATE TABLE `poll_user` (
  `P_Uid` int(5) NOT NULL,
  `Pid` int(5) NOT NULL,
  `Uid` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `Rate Id` int(5) NOT NULL,
  `stars` varchar(10) NOT NULL,
  `meaning` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userdetails`
--

CREATE TABLE `userdetails` (
  `Uid` int(5) NOT NULL,
  `username` varchar(13) NOT NULL,
  `email` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `age` varchar(10) DEFAULT NULL,
  `occupation` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userdetails`
--

INSERT INTO `userdetails` (`Uid`, `username`, `email`, `password`, `age`, `occupation`) VALUES
(1, 'manveen', 'mnvn@gmail.com', '12345678', '22', 'students'),
(2, 'kiran', 'kiran@gmail.com', '12345', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_result`
--

CREATE TABLE `user_result` (
  `U_Rid` int(5) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `poll_id` varchar(10) NOT NULL,
  `answer` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_result`
--

INSERT INTO `user_result` (`U_Rid`, `user_id`, `poll_id`, `answer`) VALUES
(1, 'mnvn@gmail.com', '11', 'yes'),
(8, '1', '36', 'yes'),
(7, 'mnvn@gmail.com', '36', 'yes'),
(2, 'kiran@gmail.com', '29', 'game');

-- --------------------------------------------------------

--
-- Table structure for table `viewresults`
--

CREATE TABLE `viewresults` (
  `Rid` int(5) NOT NULL,
  `poll_question` varchar(50) NOT NULL,
  `results` varchar(10) NOT NULL,
  `date` varchar(20) NOT NULL,
  `user_id` varchar(30) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `viewresults`
--

INSERT INTO `viewresults` (`Rid`, `poll_question`, `results`, `date`, `user_id`) VALUES
(3, 'what is apple?', 'fruit', '2/4/2017', NULL),
(4, 'who is president of america?', 'Trump', '5/4/2017', NULL),
(5, 'today is monday', 'no', '28/4/2017', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admindetails`
--
ALTER TABLE `admindetails`
  ADD PRIMARY KEY (`Aid`);

--
-- Indexes for table `admin_result`
--
ALTER TABLE `admin_result`
  ADD PRIMARY KEY (`A_Rid`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `polldetails`
--
ALTER TABLE `polldetails`
  ADD PRIMARY KEY (`Pid`);

--
-- Indexes for table `poll_user`
--
ALTER TABLE `poll_user`
  ADD PRIMARY KEY (`P_Uid`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`Rate Id`);

--
-- Indexes for table `userdetails`
--
ALTER TABLE `userdetails`
  ADD PRIMARY KEY (`Uid`);

--
-- Indexes for table `user_result`
--
ALTER TABLE `user_result`
  ADD PRIMARY KEY (`U_Rid`);

--
-- Indexes for table `viewresults`
--
ALTER TABLE `viewresults`
  ADD PRIMARY KEY (`Rid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admindetails`
--
ALTER TABLE `admindetails`
  MODIFY `Aid` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `admin_result`
--
ALTER TABLE `admin_result`
  MODIFY `A_Rid` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `c_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `polldetails`
--
ALTER TABLE `polldetails`
  MODIFY `Pid` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `poll_user`
--
ALTER TABLE `poll_user`
  MODIFY `P_Uid` int(5) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `Rate Id` int(5) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `userdetails`
--
ALTER TABLE `userdetails`
  MODIFY `Uid` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user_result`
--
ALTER TABLE `user_result`
  MODIFY `U_Rid` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `viewresults`
--
ALTER TABLE `viewresults`
  MODIFY `Rid` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
