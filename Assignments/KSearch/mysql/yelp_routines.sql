-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: yelp
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping routines for database 'yelp'
--
/*!50003 DROP PROCEDURE IF EXISTS `randomBusiness` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `randomBusiness`()
begin
	declare num int;
  declare tmp varchar(255);
  set num = 0;

  delete from business;
  delete from attributes;
  delete from hours;
  delete from category;

  while num < 20 do
		insert into yelp.business (business_name, address, city, state, postal_code, latitude, longitude, stars, review_count, is_open)
		values (concat('Business Test', num), concat('Address Test', num), concat('City Test', num), concat('State Test', num), '123456', 100, 100, 0, 0, 1);
		set @business_id = (select business_id from business where business_name = concat('Business Test', num));
		insert into yelp.hours (business_id, day, start_time, close_time) values (@business_id, 'Monday', '9:00', '21:00');
		insert into yelp.hours (business_id, day, start_time, close_time) values (@business_id, 'Tuesday', '9:00', '21:00');
		insert into yelp.hours (business_id, day, start_time, close_time) values (@business_id, 'Wednesday', '9:00', '21:00');
		insert into yelp.hours (business_id, day, start_time, close_time) values (@business_id, 'Thursday', '9:00', '21:00');
		insert into yelp.hours (business_id, day, start_time, close_time) values (@business_id, 'Friday', '9:00', '21:00');
		insert into yelp.hours (business_id, day, start_time, close_time) values (@business_id, 'Saturday', '8:00', '22:00');
		insert into yelp.hours (business_id, day, start_time, close_time) values (@business_id, 'Sunday', '8:00', '22:00');
		insert into yelp.category values (@business_id, 'Test1');
		insert into yelp.category values (@business_id, 'Test2');
		insert into yelp.category values (@business_id, 'Test3');
		insert into yelp.attributes (business_id, name, value) values (@business_id, 'Name1', 'Value1');
		insert into yelp.attributes (business_id, name, value) values (@business_id, 'Name2', 'Value2');
		insert into yelp.attributes (business_id, name, value) values (@business_id, 'Name3', 'Value3');
    set num = num + 1;
	end while;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `randomFriends` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `randomFriends`()
begin
  declare num int;
  delete from friends;
  set num = 0;
  while num < 40 do
    set @user_id1 = (select user_id from user order by rand() limit 1);
    set @user_id2 = (select user_id from user order by rand() limit 1);
    set @cnt = (select count(*) from friends where user_id = @user_id1 and friend_id = @user_id2);
    if @user_id1 != @user_id2 and @cnt = 0 then
      insert into friends values (@user_id1, @user_id2);
    end if;
    set num = num + 1;
  end while;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `randomUserAndReview` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `randomUserAndReview`()
begin
  declare num int;
  declare review_cnt int;

  delete from user;
  delete from elite;
  delete from review;

  set num = 0;
  while num < 10 do
    set review_cnt = floor(1 + rand() * 9);
    insert into user (user_name, email, password, review_count, yelping_since, useful, funny, cool, fans, average_stars)
    values (concat('Username', num), concat('Email', num, '@yelp.com'), '123456', review_cnt, now(), 0, 0, 0, 0, 0);

    set @tmp = 0;
    while @tmp < floor(0 + rand() * 2) do
      set @userId = (select user_id from user where user_name = concat('Username', num));
      insert into elite values (@userId, floor(2010 + rand() * 9));
      set @tmp = @tmp + 1;
    end while;

    set @tmp = 0;
    set @total_star = 0.0;
    while @tmp < review_cnt do
      set @userId = (select user_id from user where user_name = concat('Username', num));
      set @businessId = (select business_id from business order by rand() limit 1);
      set @star = floor(1 + rand() * 4);
      set @total_star = @total_star + @star;
      insert into review (user_id, business_id, stars, date, text, useful, funny, cool)
      values (@userId, @businessId, @star, now(), concat('Review Text', @tmp), 0, 0, 0);
      update business set review_count = review_count + 1, stars = (stars * review_count + @star) / (review_count + 1) where business_id = @businessId;
      set @tmp = @tmp + 1;
    end while;
    update user set average_stars = @total_star / review_count where user_name = concat('Username', num);
    set num = num + 1;
  end while;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-24  4:04:01
