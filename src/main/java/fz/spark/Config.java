package fz.spark;

public class Config {
	
	public void removeSparkInfoout() {
		/*
		 * 修改log4j.properties文件的内容 
　　将第一行的log4j.rootCategory=INFO, console改成log4j.rootCategory=ERROR, console，只显示ERROR级别的日志。
		 */
	}
public String getMavenSetting() {
	return "";
			/*
			Maven是当前流行的项目管理工具，但官方的库在国外经常连不上，连上也下载速度很慢。国内oschina的maven服务器很早之前就关了。今天发现阿里云的一个中央仓库，亲测可用。

			1 <mirror>
			2     <id>alimaven</id>
			3     <mirrorOf>central</mirrorOf>
			4     <name>aliyun maven</name>
			5     <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
			6 </mirror>
			　　修改${maven.home}/conf或者${user.home}/.m2文件夹下的settings.xml文件，在<mirrors>标签下加入上述内容即可。如下：

			按 Ctrl+C 复制代码

			<?xml version="1.0" encoding="UTF-8"?>
			<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
			          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
			    <mirrors>
			        <!-- 阿里云仓库 -->
			        <mirror>
			            <id>alimaven</id>
			            <mirrorOf>central</mirrorOf>
			            <name>aliyun maven</name>
			            <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
			        </mirror>
			    
			        <!-- 中央仓库1 -->
			        <mirror>
			            <id>repo1</id>
			            <mirrorOf>central</mirrorOf>
			            <name>Human Readable Name for this Mirror.</name>
			            <url>http://repo1.maven.org/maven2/</url>
			        </mirror>
			    
			        <!-- 中央仓库2 -->
			        <mirror>
			            <id>repo2</id>
			            <mirrorOf>central</mirrorOf>
			            <name>Human Readable Name for this Mirror.</name>
			            <url>http://repo2.maven.org/maven2/</url>
			        </mirror>
			    </mirrors> 
			</settings>
			*/
}

public String getPopXML() {
	return "";
	/*
	 *<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>fz.spark</groupId>
  <artifactId>spark-examples</artifactId>
  <version>0.0.1</version>
  <name>SparkExamples</name>
  <packaging>jar</packaging>

  <dependencies>
    <dependency>
      <groupId>org.apache.spark</groupId>
      <artifactId>spark-core_2.11</artifactId>
      <version>2.1.0</version>
    </dependency>

    <dependency> 
      <groupId>org.apache.spark</groupId>
      <artifactId>spark-launcher_2.11</artifactId>
      <version>2.1.0</version>
    </dependency>

    <dependency> 
      <groupId>org.apache.spark</groupId>
      <artifactId>spark-sql_2.11</artifactId>
      <version>2.1.0</version>
    </dependency>    
  </dependencies>  

</project>

	 *
	 **/
}
public void setSpark() {
	/*
	 * Apache Spark
从Apache Spark官网下载Spark 2.1.0安装包。 
下载地址：http://spark.apache.org/downloads.html 

JDK 8
Java 8提供了对Lambda表达式的支持，推荐下载最新版本的JDK。 

Eclipse IDE
最新的Eclipse IDE版本是Neon.2，推荐下载Eclipse IDE for Java EE Developers的发行版本，比 for Java Developers的多了一些必要的Package。不过两者都已经捆绑了Apache Maven Plugin，所以对于开发简单的Spark Java应用的来说，两者没有任何区别。

winutils
Windows上运行Hadoop/Spark需要hadoop.dll和winutils.exe，但是官网提供的binary中并不包括这两个文件，利用源代码编译可以生成它们。如果感兴趣可以源代码编译试试，当然如果想省事的话，网上已经有其他人编译好的文件可供下载使用。

配置Spark运行环境
设置环境变量
配置SPARK_HOME环境变量 
系统属性->环境变量…->新建 

将Spark运行文件加入到系统环境变量Path中 
系统属性->环境变量…->双击”Path”环境变量 

配置HADOOP_HOME环境变量 
系统属性->环境变量…->新建 


测试Spark运行环境
D:\run-example SparkPi

设置Eclipse IDE
新建项目
1) File菜单->New->Other
新建Maven项目
勾选”Create a simple project”
填写Maven project的相关信息
生成项目
编辑pom.xml
在Eclipse项目文件中双击pom.xml文件，选择“pom.xml”标签页。
保存pom.xml文件之后，Maven开始自动获取依赖包。依赖包保存的位置在“%USERPROFILE%.m2\repository”目录下。
	 */
}

}
