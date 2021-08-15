1. mvn generate:archetype (not valid command anymore) ====> mvn archetype:generate
2. maven-archetype-quickstart
	-> pilin yg maven

3. programmer-zaman-now
4. study-kasus-java-database
5. programmer.zaman.now




Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 111: maven-quickstart
Choose archetype:
   Your filter doesn't match any archetype (hint: enter to return to initial list)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : maven-archetype-quickstart
Choose archetype:
1: remote -> com.github.ywchang:maven-archetype-quickstart (Provide up-to-date java quickstart archetype)
2: remote -> com.haoxuer.maven.archetype:maven-archetype-quickstart (a simple maven archetype)
3: remote -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 3: 3
Choose org.apache.maven.archetypes:maven-archetype-quickstart version: 
1: 1.0-alpha-1
2: 1.0-alpha-2
3: 1.0-alpha-3
4: 1.0-alpha-4
5: 1.0
6: 1.1
7: 1.3
8: 1.4
Choose a number: 8: 
Define value for property 'groupId': programmer-zaman-now
Define value for property 'artifactId': study-kasus-java-database
Define value for property 'version' 1.0-SNAPSHOT: : 
Define value for property 'package' programmer-zaman-now: : programmer.zaman.now.todolist
Confirm properties configuration:
groupId: programmer-zaman-now
artifactId: study-kasus-java-database
version: 1.0-SNAPSHOT
package: programmer.zaman.now.todolist
 Y: : y
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: maven-archetype-quickstart:1.4
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: programmer-zaman-now
[INFO] Parameter: artifactId, Value: study-kasus-java-database
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: package, Value: programmer.zaman.now.todolist
[INFO] Parameter: packageInPathFormat, Value: programmer/zaman/now/todolist
[INFO] Parameter: package, Value: programmer.zaman.now.todolist
[INFO] Parameter: groupId, Value: programmer-zaman-now
[INFO] Parameter: artifactId, Value: study-kasus-java-database
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Project created from Archetype in dir: /data/myworks/trash/programmerZamanNow/studyDatabase/study-kasus-java-database
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:34 min
[INFO] Finished at: 2021-08-10T16:27:53+07:00
[INFO] ------------------------------------------------------------------------
rianday@rianday-revolve-G2:/data/myworks/trash/programmerZamanNow/studyDatabase$ 





create table on mysql;

   create user on mysql :
      mysql> create user 'dev'@'localhost' IDENTIFIED BY 'dev';
      Query OK, 0 rows affected (0,02 sec)

      mysql> grant all privileges on *.* to 'dev'@'localhost';
      Query OK, 0 rows affected (0,01 sec)

      mysql> flush privileges;
      Query OK, 0 rows affected (0,02 sec)

      mysql> exit
      Bye
      rianday@rianday-revolve-G2:~$ mysql -u dev -p
      Enter password: 

   login as dev :
      rianday@rianday-revolve-G2:~$ mysql -u dev -p
      Enter password: 
      mysql> create table todolist( id INT NOT NULL AUTO_INCREMENT, todo VARCHAR(255)
               NOT NULL, PRIMARY KEY(id) ) ENGINE = InnoDB;
      mysql> alter table todolist modify todo varchar(255) not null unique;
      
