--
-- Database: `library`
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
-- Populate one Admin User: user/pass - admin/admin
--
INSERT INTO `users`(`username`, `password`, `first_name`, `last_name`, `email`, `role`, `status`)
VALUES ('admin','$2a$10$oMBvbuqBRgsamQYLVvXgsempbOV8d879sc.HyKYquJTxeGY1qNpCS', 'Adminec','Adminov','admin@softserveinc.com', 'ADMIN', 'ACTIVE');

--
-- Table structure for table `book_ratings`
--
CREATE TABLE `book_ratings` (
`rating_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`rating` BIGINT UNSIGNED NOT NULL,
`book_id` BIGINT UNSIGNED NOT NULL,
`user_id` BIGINT UNSIGNED NOT NULL,
CONSTRAINT `book_ratings_author_books` FOREIGN KEY (`book_id`) REFERENCES `author_books`(`book_id`) ON DELETE CASCADE,
CONSTRAINT `book_ratings_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET=utf8;


--
-- Table structure for table `messages`
--
CREATE TABLE `messages` (
	`message_id` int(6) NOT NULL AUTO_INCREMENT,
	`header` varchar(60) NOT NULL,
	`body` text NOT NULL,
	`date` DATETIME NOT NULL DEFAULT NOW(),
	`sender_id` bigint UNSIGNED NOT NULL,
	`receiver_id` bigint UNSIGNED NOT NULL,
	`is_new` TINYINT(1) NOT NULL,
	`in_reply_to` int(6) NOT NULL,
	PRIMARY KEY (`message_id`),
	CONSTRAINT `fk_sender_message` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
	CONSTRAINT `fk_receiver_message` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET=utf8;

--
-- Table structure for table `comments`
--
CREATE TABLE COMMENTS (
	comment_id int(6) NOT NULL AUTO_INCREMENT,
	book_id bigint UNSIGNED NOT NULL,
	user_id bigint UNSIGNED NOT NULL,
	comment varchar(200) NOT NULL,
	PRIMARY KEY (comment_id),
	CONSTRAINT fk_book_comments FOREIGN KEY (book_id) REFERENCES author_books (book_id) ON DELETE CASCADE,
	CONSTRAINT fk_comments_user FOREIGN KEY (user_id) REFERENCES USERS (user_id) ON DELETE CASCADE
)ENGINE = InnoDB CHARACTER SET=utf8;


CREATE TABLE `items` (
`itemId` 	BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`itemName`	VARCHAR(50) NOT NULL,
`itemContent` 	LONGBLOB NOT NULL,
`book_id` bigint UNSIGNED NOT NULL,
CONSTRAINT  `items_book` FOREIGN KEY (`book_id`) REFERENCES `author_books`(`book_id`) ON DELETE CASCADE
) ENGINE = InnoDB CHARSET=utf8;


