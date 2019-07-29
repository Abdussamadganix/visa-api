# Dump of table log
# ------------------------------------------------------------

CREATE TABLE `log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Autoincrement Primary Key',
  `system` varchar(32) DEFAULT NULL COMMENT 'Name of system',
  `request_body` text COMMENT 'Raw request',
  `response_body` text COMMENT 'Raw response',
  `response_code` varchar(4) DEFAULT NULL COMMENT 'HTTP status code of response',
  `status` varchar(16) DEFAULT NULL COMMENT 'HTTP status of response',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'When log was created',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT 'Last time log was updated',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Dump of table user
# ------------------------------------------------------------

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Autoincrement primary key',
  `unique_key` varchar(32) NOT NULL DEFAULT '' COMMENT 'Unique key to refer to user',
  `first_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'First firstName of user',
  `last_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'Last firstName of user',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT 'Email address of user',
  `phone` varchar(16) DEFAULT NULL COMMENT 'Phone number of user',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT 'Password of user',
  `status` varchar(16) NOT NULL DEFAULT '' COMMENT 'Status of user',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'When the user was created',
  `updated_at` timestamp NULL DEFAULT NULL  COMMENT 'Last time user was udpated',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNQ-user-unique_key` (`unique_key`),
  UNIQUE KEY `UNQ-user-email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Dump of table token
# ------------------------------------------------------------

CREATE TABLE `token` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(32) NOT NULL DEFAULT '' COMMENT 'Unique key of user',
  `token` varchar(64) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'When the token was created',
  `updated_at` timestamp NULL DEFAULT NULL ,
  `expires_at` timestamp NULL DEFAULT NULL COMMENT 'When the token expires',
  `status` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNQ-token-token` (`token`),
  KEY `FK-token-user_key-user-unique_key` (`user`),
  CONSTRAINT `FK-token-user_key-user-unique_key` FOREIGN KEY (`user`) REFERENCES `user` (`unique_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `payment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Autoincrement Primary Key',
  `unique_key` varchar(32) NOT NULL COMMENT 'Unique key of payment',
  `merchant_id` varchar(32) NOT NULL COMMENT 'Unique key of payment',
  `terminal_id` varchar(32) NOT NULL COMMENT 'The user in the organisation who initiates the payment ',
  `bill_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'Unique key of organisation',
  `bill_id_format` varchar(32) DEFAULT NULL COMMENT 'Unique key of organisation',
  `ref_id` varchar(32) DEFAULT NULL COMMENT 'Unique key of organisation',
  `other_phone_number` varchar(32) DEFAULT NULL  COMMENT 'Unique key of organisation',
  `other_email_address` varchar(32) DEFAULT NULL  COMMENT 'Unique key of organisation',
  `enc_merchant_pan` varchar(32) DEFAULT NULL COMMENT 'Unique key of organisation bank',
  `enc_consumer_name` varchar(32) DEFAULT NULL COMMENT 'Unique key of recipient',
  `amount` varchar(20)  DEFAULT NULL COMMENT 'Amount paid in cents',
  `transaction_fee_amount` varchar(20) DEFAULT NULL COMMENT 'Amount paid in cents',
  `transaction_amount` varchar(20) DEFAULT NULL COMMENT 'Amount paid in cents',
  `currency` varchar(10) DEFAULT NULL COMMENT 'Currency of payment',
  `message_type` varchar(50)  NULL COMMENT 'message type of payment',
  `auth_id_response` varchar(50)  NULL COMMENT 'auth id response of payment',
  `response_code` varchar(50)  NULL COMMENT 'auth id response of payment',
  `rejection_code` varchar(50)  NULL COMMENT 'auth id response of payment',
  `enc_consumer_pan` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `transmission_date_time` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `system_trace_audit_number` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `local_transaction_time` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `local_transaction_date` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `originator_country_code` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `originator_bin` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `retrieval_reference_number` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `transaction_currency_code` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `visa_transaction_id` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `decimal_position_indicator` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `enc_agent_name` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `agent_city` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `velocity_limit_indicator` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `decimal_postion_indicator` varchar(50) DEFAULT NULL COMMENT 'auth id response of payment',
  `status` varchar(16) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'When the payment was created',
  `updated_at` timestamp NULL DEFAULT NULL  COMMENT 'Last time payment was updated',
  PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
