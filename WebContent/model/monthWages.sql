DELIMITER $$

USE `test`$$

DROP VIEW IF EXISTS `职工工资表`$$

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `职工工资表` AS (
SELECT
  `w`.`id`                 AS `id`,
  `w`.`职员编号`       AS `职员编号`,
  `k`.`部门ID`           AS `部门ID`,
  `k`.`公司`             AS `公司`,
  `k`.`部门`             AS `部门`,
  `k`.`岗位`             AS `岗位`,
  `k`.`小组`             AS `小组`,
  `w`.`姓名`             AS `姓名`,
  `w`.`身份证号码`    AS `身份证号码`,
  `w`.`开户行`          AS `开户行`,
  `w`.`工资总额`       AS `工资总额`,
  `w`.`职工帐号`       AS `职工帐号`,
  `k`.`出勤天数`       AS `出勤天数`,
  `k`.`转正差额天数` AS `转正差额天数`,
  `k`.`迟到应扣天数` AS `迟到应扣天数`,
  `w`.`通讯补贴`       AS `通讯补贴`,
  `w`.`保密补贴`       AS `保密补贴`,
  `w`.`午餐补贴`       AS `午餐补贴`,
  `k`.`事假小时数`    AS `事假小时数`,
  `k`.`病假小时数`    AS `病假小时数`,
  `k`.`旷工小时数`    AS `旷工小时数`
  `w`.`病假累计`       AS `病假累计`,
  `w`.`年假累计`       AS `年假累计`,
  `w`.`累计带薪病假` AS `累计带薪病假`,
  `w`.`基本工资`       AS `基本工资`,
  `w`.`代扣养老保险` AS `代扣养老保险`,
  `w`.`代扣住房保险` AS `代扣住房保险`,
  `w`.`代扣医疗保险` AS `代扣医疗保险`,
  `w`.`代扣失业保险` AS `代扣失业保险`,
  `w`.`公司生育保险` AS `公司生育保险`,
  `w`.`公司养老保险` AS `公司养老保险`,
  `w`.`公司住房保险` AS `公司住房保险`,
  `w`.`公司工伤保险` AS `公司工伤保险`,
  `w`.`公司医疗保险` AS `公司医疗保险`,
  `w`.`公司失业保险` AS `公司失业保险`,
  `k`.`丧家天数`       AS `丧家天数`,
  `k`.`婚假天数`       AS `婚假天数`,
  `k`.`年假天数`       AS `年假天数`,
  `k`.`产假天数`       AS `产假天数`,
  `w`.`公司名称`       AS `公司名称`,
  `w`.`户口性质`       AS `户口性质`,
  `w`.`内部编号`       AS `内部编号`,
  `w`.`基数`             AS `基数`,
  `w`.`绩效工资`       AS `绩效工资`,
  `w`.`岗位工资`       AS `岗位工资`,
  `w`.`备注`             AS `备注`,
  `w`.`工资编号`       AS `工资编号`,
  `w`.`办公电话`       AS `办公电话`,
  `w`.`入保时间`       AS `入保时间`,
  `k`.`入职时间`       AS `入职时间`,
  `k`.`入职报表`       AS `入职报表`,
  `k`.`离职时间`       AS `离职时间`,
  `k`.`离职报表`       AS `离职报表`,
  `k`.`转正时间`       AS `转正时间`,
  `k`.`转正报表`       AS `转正报表`,
  `k`.`考勤备注`       AS `考勤备注`,
FROM (`职员工资分布表` `w`
   LEFT JOIN `考勤表` `k`
     ON ((`w`.`职员编号` = `k`.`userId`))))$$

DELIMITER ;