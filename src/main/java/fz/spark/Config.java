package fz.spark;

public class Config {
	
	public void removeSparkInfoout() {
		/*
		 * �޸�log4j.properties�ļ������� 
��������һ�е�log4j.rootCategory=INFO, console�ĳ�log4j.rootCategory=ERROR, console��ֻ��ʾERROR�������־��
		 */
	}
public String getMavenSetting() {
	return "";
			/*
			Maven�ǵ�ǰ���е���Ŀ�����ߣ����ٷ��Ŀ��ڹ��⾭�������ϣ�����Ҳ�����ٶȺ���������oschina��maven����������֮ǰ�͹��ˡ����췢�ְ����Ƶ�һ������ֿ⣬�ײ���á�

			1 <mirror>
			2     <id>alimaven</id>
			3     <mirrorOf>central</mirrorOf>
			4     <name>aliyun maven</name>
			5     <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
			6 </mirror>
			�����޸�${maven.home}/conf����${user.home}/.m2�ļ����µ�settings.xml�ļ�����<mirrors>��ǩ�¼����������ݼ��ɡ����£�

			�� Ctrl+C ���ƴ���

			<?xml version="1.0" encoding="UTF-8"?>
			<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
			          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
			    <mirrors>
			        <!-- �����Ʋֿ� -->
			        <mirror>
			            <id>alimaven</id>
			            <mirrorOf>central</mirrorOf>
			            <name>aliyun maven</name>
			            <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
			        </mirror>
			    
			        <!-- ����ֿ�1 -->
			        <mirror>
			            <id>repo1</id>
			            <mirrorOf>central</mirrorOf>
			            <name>Human Readable Name for this Mirror.</name>
			            <url>http://repo1.maven.org/maven2/</url>
			        </mirror>
			    
			        <!-- ����ֿ�2 -->
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
��Apache Spark��������Spark 2.1.0��װ���� 
���ص�ַ��http://spark.apache.org/downloads.html 

JDK 8
Java 8�ṩ�˶�Lambda���ʽ��֧�֣��Ƽ��������°汾��JDK�� 

Eclipse IDE
���µ�Eclipse IDE�汾��Neon.2���Ƽ�����Eclipse IDE for Java EE Developers�ķ��а汾���� for Java Developers�Ķ���һЩ��Ҫ��Package���������߶��Ѿ�������Apache Maven Plugin�����Զ��ڿ����򵥵�Spark JavaӦ�õ���˵������û���κ�����

winutils
Windows������Hadoop/Spark��Ҫhadoop.dll��winutils.exe�����ǹ����ṩ��binary�в��������������ļ�������Դ�����������������ǡ��������Ȥ����Դ����������ԣ���Ȼ�����ʡ�µĻ��������Ѿ��������˱���õ��ļ��ɹ�����ʹ�á�

����Spark���л���
���û�������
����SPARK_HOME�������� 
ϵͳ����->����������->�½� 

��Spark�����ļ����뵽ϵͳ��������Path�� 
ϵͳ����->����������->˫����Path���������� 

����HADOOP_HOME�������� 
ϵͳ����->����������->�½� 


����Spark���л���
D:\run-example SparkPi

����Eclipse IDE
�½���Ŀ
1) File�˵�->New->Other
�½�Maven��Ŀ
��ѡ��Create a simple project��
��дMaven project�������Ϣ
������Ŀ
�༭pom.xml
��Eclipse��Ŀ�ļ���˫��pom.xml�ļ���ѡ��pom.xml����ǩҳ��
����pom.xml�ļ�֮��Maven��ʼ�Զ���ȡ�������������������λ���ڡ�%USERPROFILE%.m2\repository��Ŀ¼�¡�
	 */
}

}
