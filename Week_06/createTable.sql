-- 货物表
CREATE TABLE `ES_GOODS` (
  `goodId` bigint NOT NULL,
  `goodName` varchar(50) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `goodURL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`goodId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


-- 订单表
CREATE TABLE `ES_ORDER` (
  `orderId` bigint NOT NULL,
  `userid` bigint DEFAULT NULL,
  `goodid` bigint DEFAULT NULL,
  `orderCode` bigint DEFAULT NULL,
  `realCost` bigint DEFAULT NULL,
  `orderdescId` bigint DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


-- 用户表
CREATE TABLE `ES_USER` (
  `userid` bigint NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `displayName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci