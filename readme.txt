提交记录：

2019-05-07 —>【20190428_MyBatis插件原理与Spring集成】：
               自己编写插件：
                1、当我们传入RowBounds做翻页查询的时候，使用limit物理分页，代替原来的逻辑分页基于mybatis-standalone
                    MyBatisTest.java —— testSelectByRowBounds()
                   ---详见代码：
                 （1）java准备类：src/main/java/com/kellystudy/
                 （2）resuorce:src/main/resources/
                 （3）翻页插件类：src/main/java/com/kellystudy/interceptor/KYPageInterceptor.java
                 （4）test类：src/test/java/com/kellystudy/MyBatisPluginTest.java

                2、在未启用日志组件的情况下，输出执行的SQL，并且统计SQL的执行时间（先实现查询的拦截）（自己思考，自己动手）
                  ---详见代码：
                （1）java准备类：src/main/java/com/kellystudy/
                （2）resuorce:src/main/resources/
                （3）统计SQL执行时间插件类：src/main/java/com/kellystudy/interceptor/KYSQLExecuteTimeInterceptor.java
                （4）test类：src/test/java/com/kellystudy/MyBatisPluginTest.java
							
2019-05-04 —>【20190427_MyBatis体系结构与工作原理】：
                1、跟踪update()流程，绘制每一步的时序图（4个）
                  ----详见【时序图】/
                2、总结：MyBatis里面用到了哪些设计模式
                  -----详见 【文字作业.MD中的MyBatis-20190428】
2019-04-29 —>【20190424_MyBatis应用分析与最佳实践】
              以下问题答案详见 【文字作业.MD中的MyBatis-20190424】
			   1、resultType（属性）和resultMap（标签引用）的区别？
			   2、collection和association的区别？
		       3、Statement和PreparedStatement的区别？