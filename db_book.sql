/*
 Navicat Premium Data Transfer

 Source Server         : local_conn
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : db_book

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 10/12/2021 10:06:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
BEGIN;
INSERT INTO `t_admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2021-12-10 09:26:57');
COMMIT;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '所属类别',
  `name` varchar(100) NOT NULL COMMENT '书名',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `price` double DEFAULT NULL COMMENT '价格',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面图',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `stock` int DEFAULT NULL COMMENT '存量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_book
-- ----------------------------
BEGIN;
INSERT INTO `t_book` VALUES (1, 1, '挪威的森林', '村上春树', 88, 'https://img3.doubanio.com/view/subject/s/public/s1221930.jpg', '这是一部动人心弦的、平缓舒雅的、略带感伤的恋爱小说。小说主人公渡边以第一人称展开他同两个女孩间的爱情纠葛。', 9);
INSERT INTO `t_book` VALUES (2, 1, '我只喜欢你', '乔一', 33.3, 'https://img1.doubanio.com/view/subject/s/public/s29169287.jpg', '他温柔地握住我的手，“但我知道，一想到能和你共度余生，我就对余生充满期待。”', 8);
INSERT INTO `t_book` VALUES (3, 1, '最好的我们', '八月长安', 55, 'https://img9.doubanio.com/view/subject/s/public/s26720726.jpg', '你还记得高中时的同桌吗？那个少年有世界上最明朗的笑容，那个女生有世界上最好看的侧影。高中三年，两个人的影子和粉笔灰交织在一起，黑白分明，在记忆里面转圈。本书以怀旧的笔触讲述了女主角耿耿和男主角余淮同桌三年的故事，耿耿余淮，这么多年一路走过的成长故事极为打动人心，整个故事里有的都是在成长过程中细碎的点点滴滴，将怀旧写到了极致，将记忆也写到了极致。', 7);
INSERT INTO `t_book` VALUES (8, 2, '从你的全世界路过', '张嘉佳', 34.5, 'https://img9.doubanio.com/view/subject/s/public/s27102925.jpg', '《从你的全世界路过》是微博上最会写故事的人张嘉佳献给你的心动故事。', 3);
INSERT INTO `t_book` VALUES (9, 2, '朝花惜时', '左小翎', 23.6, 'https://img2.doubanio.com/view/subject/s/public/s29470723.jpg', '天真少女安可乐第一次来到大城市，在学校里结实了形形色色的新朋友：态度恶劣的后桌同学叶陵、心地善良却经常受到嫉妒和欺凌的校花文浅浅、曾经暗恋的学长白一然……还在人生第一次去夜店时遇到了极具神秘感的服务生言述。在努力适应崭新生活环境的过程中，她发现叶陵与言述之间似乎有着千丝万缕的联系……', 2);
INSERT INTO `t_book` VALUES (28, 3, '那些回不去的年少时光', '桐华', 23.8, 'https://img3.doubanio.com/view/subject/s/public/s4130990.jpg', '最值得珍藏的怀旧读物，写给年少自己的书，纪念我们共同的青春和成长', 1);
INSERT INTO `t_book` VALUES (29, 3, '少年巴比伦', '路内', 22, 'https://img2.doubanio.com/view/subject/m/public/s3251672.jpg', '上个世纪90年代的戴城，路小路在一家化工厂上班，他是个愣头青，不知道未来和生活目标在哪里。跟着一个叫“老牛逼”的师傅混，没学会半点技术。在机修班，除了拧螺丝之外什么都不会，在电工班，就只会换灯泡。除此之外，还喜欢打游戏、翻工厂的院墙，打架。当然还追女人，他与一个叫白蓝的厂医产生了爱情，最终因为白蓝考上了研究生而离开了他。', 9);
INSERT INTO `t_book` VALUES (30, 4, '余生，请多指教', '柏林石匠', 32, 'https://img9.doubanio.com/view/subject/m/public/s29049325.jpg', '曾经以为，自己这辈子都等不到了——世界这么大，我又走得这么慢，要是遇不到良人要怎么办？', 8);
INSERT INTO `t_book` VALUES (35, 4, '何以笙箫默', '顾漫', 15, 'https://img9.doubanio.com/view/subject/m/public/s2529195.jpg', '一段年少时的爱恋，牵出一生的纠缠。大学时代的赵默笙阳光灿烂，对法学系大才子何以琛一见倾心，开朗直率的她拔足倒追，终于使才气出众的他为她停留驻足。然而，不善表达的他终于使她在一次伤心之下远走他乡……', 7);
INSERT INTO `t_book` VALUES (52, 6, 'Java编程思想', 'Bruce Eckel ', 88, 'https://img9.doubanio.com/view/subject/m/public/s27243455.jpg', '本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。', 6);
INSERT INTO `t_book` VALUES (57, 5, '经济学原理', '[美] 曼昆 ', 88, 'https://img9.doubanio.com/view/subject/m/public/s3802186.jpg', '此《经济学原理》的第3版把较多篇幅用于应用与政策，较少篇幅用于正规的经济理论。书中主要从供给与需求、企业行为与消费者选择理论、长期经济增长与短期经济波动以及宏观经济政策等角度深入浅出地剖析了经济学家们的世界观。', 4);
INSERT INTO `t_book` VALUES (58, 6, '浪潮之巅', '[美] 吴军 ', 55, 'https://img9.doubanio.com/view/subject/m/public/s6807265.jpg', '近一百多年来，总有一些公司很幸运地、有意识或无意识地站在技术革命的浪尖之上。在这十几年间，它们代表着科技的浪潮，直到下一波浪潮的来临。\n\n从一百年前算起，AT&T 公司、IBM 公司、苹果公司、英特尔公司、微软公司、思科公司、雅虎公司和Google公司都先后被幸运地推到了浪尖。虽然，它们来自不同的领域，中间有些已经衰落或正在衰落，但是它们都极度辉煌过。本书系统地介绍了这些公司成功的本质原因及科技工业一百多年的发展。', 3);
INSERT INTO `t_book` VALUES (61, 5, '创业维艰', '本·霍洛维茨', 49, 'https://img9.doubanio.com/view/subject/m/public/s28007966.jpg', '在《创业维艰》中，本·霍洛维茨从自己的创业经历讲起，以自己在硅谷近20余年的创业、管理和投资经验，对创业公司（尤其是互联网技术公司）的创立、经营、人才选拔、企业文化、销售、CEO与董事会的关系等方方面面，毫无保留地奉上自己的经验之谈。他还谈到了与比尔·坎贝尔、安迪·拉切列夫、迈克尔·奥维茨等硅谷顶级CEO和投资人的交往经历，从他们身上学到的宝贵经验，以及他和马克·安德森这对绝佳拍档为何能够一起奋斗18年还能合作得这么好。', 9);
INSERT INTO `t_book` VALUES (63, 1, '碎片', '埃莱娜·费兰特', 69, 'https://img2.doubanio.com/view/subject/s/public/s33734103.jpg', '《碎片》既是深入费兰特的文学世界的指引，同时也是一份智性、鲜明而坚定的文学宣言。', 100);
COMMIT;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_category
-- ----------------------------
BEGIN;
INSERT INTO `t_category` VALUES (1, '文学');
INSERT INTO `t_category` VALUES (2, '流行');
INSERT INTO `t_category` VALUES (3, '文化');
INSERT INTO `t_category` VALUES (4, '生活');
INSERT INTO `t_category` VALUES (5, '经管');
INSERT INTO `t_category` VALUES (6, '科技');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `avatar` varchar(300) DEFAULT NULL COMMENT '头像',
  `identity` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '学生' COMMENT '角色',
  `department` varchar(50) DEFAULT NULL COMMENT '部门',
  `create_date` date DEFAULT NULL COMMENT '加入时间',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_index` (`mobile`) USING BTREE COMMENT '手机号唯一主索引'
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, '13976763333', 'e10adc3949ba59abbe56e057f20f883e', 'yanzh@niit.edu.cn', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/1.jpg', '教师', '计算机与软件学院', '2021-01-01', '闫泽华');
INSERT INTO `t_user` VALUES (2, '15100998877', 'e10adc3949ba59abbe56e057f20f883e', 'meisy@niit.edu.cn', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/2.jpg', '教师', '电气工程学院', '2020-09-06', '梅拾璎');
INSERT INTO `t_user` VALUES (3, '13987876554', 'e10adc3949ba59abbe56e057f20f883e', 'az@niit.edu.cn', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/3.jpg', '学生', '经济管理学院', '2020-10-01', '阿栈');
INSERT INTO `t_user` VALUES (4, '13144336565', 'e10adc3949ba59abbe56e057f20f883e', 'huitd@niit.edu.cn', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/4.jpg', '学生', '艺术设计学院', '2021-12-04', '灰土豆');
INSERT INTO `t_user` VALUES (5, '18011113333', 'e10adc3949ba59abbe56e057f20f883e', 'sanwy@niit.edu.cn', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/5.jpg', '教师', '计算机与软件学院', '2021-12-01', '三儿王屿');
INSERT INTO `t_user` VALUES (6, '18900008989', 'e10adc3949ba59abbe56e057f20f883e', 'wangb@niit.edu.cn', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/6.jpg', '教师', '计算机与软件学院', '2020-11-29', '汪波');
INSERT INTO `t_user` VALUES (13, '13951905171', 'e10adc3949ba59abbe56e057f20f883e', 'mqxu@gmail.com', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/7.jpg', '教师', '计算机与软件学院', '2021-12-08', 'mqxu');
INSERT INTO `t_user` VALUES (15, '13877776666', 'e10adc3949ba59abbe56e057f20f883e', 'aaa@qq.com', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/8.jpg', '学生', '航空工程学院', '2021-12-01', 'aaa');
INSERT INTO `t_user` VALUES (18, '13988880000', 'e10adc3949ba59abbe56e057f20f883e', 'test@qq.com', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/9.jpg', '学生', '计算机与软件学院', '2021-11-29', 'test');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
