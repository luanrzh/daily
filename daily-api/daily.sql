-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2019-12-19 08:27:09
-- 服务器版本： 5.6.44-log
-- PHP 版本： 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `daily`
--

-- --------------------------------------------------------

--
-- 表的结构 `TASK`
--

CREATE TABLE `TASK` (
  `ID` int(10) NOT NULL COMMENT '主键',
  `USER_ID` int(10) NOT NULL COMMENT '用户外键',
  `STATUS` int(1) NOT NULL DEFAULT '0' COMMENT '状态（0:已创建 1:已完成）',
  `CONTENT` varchar(512) NOT NULL COMMENT '内容',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `DEADLINE_TIME` datetime DEFAULT NULL COMMENT '截至时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `TASK`
--

INSERT INTO `TASK` (`ID`, `USER_ID`, `STATUS`, `CONTENT`, `CREATE_TIME`, `DEADLINE_TIME`) VALUES
(117, 2, 1, '国内改升所有场景自测', '2019-09-05 13:48:51', '2019-09-12 23:59:59'),
(124, 2, 1, 'OOM与性能优化学习', '2019-09-13 14:08:06', '2019-09-15 23:59:59'),
(126, 2, 1, '完成xmlmock项目编码工作', '2019-09-13 14:39:02', '2019-09-15 23:59:59'),
(127, 2, 1, 'WebService流水线完善', '2019-09-16 10:36:11', '2019-09-16 23:59:59'),
(128, 2, 1, 'XmlMock联调', '2019-09-18 09:06:24', '2019-09-18 23:59:59'),
(129, 2, 1, 'Docker学习', '2019-09-18 18:14:46', '2019-09-18 23:59:59'),
(130, 2, 1, 'jvm学习', '2019-09-22 12:45:38', '2019-09-22 23:59:59'),
(132, 2, 0, 'xmlapi开发任务', '2019-09-23 16:48:58', '2019-09-23 23:59:59'),
(134, 2, 1, 'WebService自动部署', '2019-09-23 17:02:29', '2019-09-23 23:59:59'),
(135, 2, 1, 'xmlmock开发', '2019-09-23 17:05:11', '2019-09-23 23:59:59'),
(137, 2, 0, 'Daily项目开发', '2019-09-24 20:31:23', '2019-09-24 23:59:59'),
(138, 2, 1, 'xmlmock联调', '2019-09-24 20:32:27', '2019-09-24 23:59:59'),
(139, 2, 1, '部门任务', '2019-09-29 20:12:11', '2019-09-30 10:59:59'),
(140, 2, 0, 'WebService实例化需求', '2019-09-29 20:13:43', '2019-09-30 10:59:59'),
(141, 2, 0, 'WebService自动化部署', '2019-09-29 20:13:59', '2019-09-30 10:59:59'),
(142, 2, 0, 'kf-panda开发', '2019-09-29 20:14:33', '2019-09-30 10:59:59'),
(143, 1, 0, 'update task', '2019-10-08 09:25:22', '2019-10-15 10:59:59');

-- --------------------------------------------------------

--
-- 表的结构 `TASK_STEP`
--

CREATE TABLE `TASK_STEP` (
  `ID` int(10) NOT NULL COMMENT '主键',
  `TASK_ID` int(10) NOT NULL COMMENT 'TASK外键',
  `STATUS` int(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `CONTENT` varchar(512) NOT NULL COMMENT '内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务步骤（子任务）';

--
-- 转存表中的数据 `TASK_STEP`
--

INSERT INTO `TASK_STEP` (`ID`, `TASK_ID`, `STATUS`, `CONTENT`) VALUES
(105, 117, 1, '单程-1成人-全部改升'),
(106, 117, 1, '单程-2成人1婴儿-改升1成人'),
(107, 117, 1, '单程-2成人1儿童-改升1成人'),
(108, 117, 1, '单程-同舱改期检查'),
(109, 117, 1, '单程-2成人1儿童-改升2成人'),
(110, 117, 1, '往返-1成人-改升去程'),
(111, 117, 1, '往返-1成人-改升回程'),
(112, 117, 0, '往返-1成人-去程已使用改升回程'),
(113, 117, 0, '往返-1成人-去程已改升改升回程'),
(118, 124, 1, '学习OOM排查分析实践'),
(119, 124, 1, '分析8380端口CUP占用300%的原因'),
(120, 126, 1, '一层for循环替换'),
(121, 126, 1, '多层for循环替换'),
(122, 127, 1, '优化deploy.sh脚本,重构日志相关代码'),
(123, 127, 1, '根据XML配置管理,理清Jenkins需要哪些job'),
(124, 127, 1, '完成代码分支管理与流水线配置管理规范的文档编写工作'),
(125, 128, 1, '本地环境跑通b2baop'),
(126, 130, 1, '编译安装open-jdk-7'),
(127, 132, 1, '合并改升生成订单与改升支付出票'),
(129, 134, 1, 'Jenkins自动上传jar包至maven仓库'),
(130, 135, 1, 'xmlmock开发手册编写'),
(131, 132, 1, 'PNR入库功能开发'),
(132, 137, 0, '\"任务完成\"功能开发'),
(133, 137, 0, '\"任务修改\"功能开发'),
(134, 138, 1, '与张良艳联调'),
(135, 139, 1, '完成内部审计'),
(136, 139, 1, '写月报'),
(137, 142, 1, 'API设计'),
(142, 141, 0, 'Jenkins自动上传至Maven库'),
(143, 141, 0, '解决sshpass首次使用失败的问题'),
(144, 141, 0, '整体迁移脚本编写'),
(145, 143, 0, 'update taskStep'),
(146, 143, 1, '步骤2'),
(147, 143, 0, '步骤3');

-- --------------------------------------------------------

--
-- 表的结构 `USER`
--

CREATE TABLE `USER` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `PHONE_NUMBER` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `USER`
--

INSERT INTO `USER` (`ID`, `USERNAME`, `PASSWORD`, `EMAIL`, `PHONE_NUMBER`) VALUES
(1, 'daily', 'daily', NULL, NULL),
(2, 'luanrzh', '0', NULL, NULL);

--
-- 转储表的索引
--

--
-- 表的索引 `TASK`
--
ALTER TABLE `TASK`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `TASK_ibfk_1` (`USER_ID`);

--
-- 表的索引 `TASK_STEP`
--
ALTER TABLE `TASK_STEP`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `TASK_STEP_ibfk_1` (`TASK_ID`);

--
-- 表的索引 `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`ID`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `TASK`
--
ALTER TABLE `TASK`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=144;

--
-- 使用表AUTO_INCREMENT `TASK_STEP`
--
ALTER TABLE `TASK_STEP`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=148;

--
-- 使用表AUTO_INCREMENT `USER`
--
ALTER TABLE `USER`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 限制导出的表
--

--
-- 限制表 `TASK`
--
ALTER TABLE `TASK`
  ADD CONSTRAINT `TASK_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `TASK_STEP`
--
ALTER TABLE `TASK_STEP`
  ADD CONSTRAINT `TASK_STEP_ibfk_1` FOREIGN KEY (`TASK_ID`) REFERENCES `TASK` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
