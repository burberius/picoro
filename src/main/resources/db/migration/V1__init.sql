CREATE TABLE `roastings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `origin` varchar(100) DEFAULT NULL,
  `bean_name` varchar(200) DEFAULT NULL,
  `roast_profile_id` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `burn_in` float DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `roasting_values` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roasting_id` bigint(20) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `input_temperature` float DEFAULT NULL,
  `roast_temperature` float DEFAULT NULL,
  `fan` int(11) DEFAULT NULL,
  `heating` int(11) DEFAULT NULL,
  `crack` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_roasting_values_1_idx` (`roasting_id`),
  CONSTRAINT `fk_roasting_values_1` FOREIGN KEY (`roasting_id`) REFERENCES `roastings` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
