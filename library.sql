--
-- Database: `library`  U KNOW, JUST FOR THE TEST
--
DROP DATABASE IF EXISTS `library`;
CREATE DATABASE `library` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `library`;

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--
CREATE TABLE `authors` (
`author_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL,
`country` VARCHAR(50) NOT NULL
) ENGINE = InnoDB CHARSET=utf8;

--
-- Sample data for table `author`
--
INSERT INTO `authors` (`author_id`, `name`, `country`) VALUES
(1, 'Ivan Vazov', 'Bulgaria'),
(2, 'Hristo Botev', 'Bulgaria'),
(3, 'Mario Puzo', 'USA'),
(4, 'Svetlin Nakov', 'Bulgaria'),
(5, 'Napoleon Hill', 'USA');

-- --------------------------------------------------------

--
-- Table structure for table `author_books`
--
CREATE TABLE `author_books` (
`book_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`author_id` BIGINT UNSIGNED NOT NULL,
`title` VARCHAR(50) NOT NULL,
`status` VARCHAR(30) NOT NULL,
CONSTRAINT `author_books_author` FOREIGN KEY (`author_id`)
REFERENCES `authors`(`author_id`) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET=utf8;

--
-- Sample data for table `author_books`
--
INSERT INTO `author_books` (`book_id`, `author_id`, `title`, `status`) VALUES
(1, 1, 'Pod Igoto', 'Available'),
(2, 3, 'The Godfather', 'Loaned'),
(3, 4, 'Introduction to programming with Java', 'Loaned'),
(4, 1, 'O,Shipka!', 'Loaned'),
(5, 3, 'The Sicilian', 'Available');

--
-- Table structure for table `users`
--
create table `users` (
   `user_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `username` VARCHAR(30) NOT NULL,
   `password` VARCHAR(60) NOT NULL,
   `first_name` VARCHAR(30) NOT NULL,
   `last_name`  VARCHAR(30) NOT NULL,
   `email` VARCHAR(30) NOT NULL,
   `role` VARCHAR(30) NOT NULL,
   `status` VARCHAR(30) NOT NULL,
   UNIQUE (username)
);

--
-- Populate one Admin User which will further create other users for the application using GUI
--
INSERT INTO `users`(`username`, `password`, `first_name`, `last_name`, `email`, `role`, `status`)
VALUES ('Zhivko','$2a$10$p0uWigFGA/eNtsP5j.b73.gp9ZFlYvlMH2ea7J11g9Oh2Kb6Ze6j.', 'Zhivko','Georgiev','zhivko@softserve.bg', 'ADMIN', 'ACTIVE');

CREATE TABLE `book_ratings` (
`rating_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`rating` BIGINT UNSIGNED NOT NULL,
`book_id` BIGINT UNSIGNED NOT NULL,
`user_id` BIGINT UNSIGNED NOT NULL,
CONSTRAINT `book_ratings_author_books` FOREIGN KEY (`book_id`) REFERENCES `author_books`(`book_id`) ON DELETE CASCADE,
CONSTRAINT `book_ratings_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET=utf8;
