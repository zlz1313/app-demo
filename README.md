# app-demo
web应用脚手架
jdk21

## maven-app-demo-archetype

### Getting Started
1. mvn archetype:create-from-project -D"rchetype.properties=archetype.properties"
2. Open target/generated-sources/archetype in Terminal
3. mvn clean install
4. Create Project with archetype
5. Using archetype-id with
   <groupId>com.opensource</groupId>
   <artifactId>app-demo-archetype</artifactId>
   <version>1.0-SNAPSHOT</version>
6. add Additional Properties
package=com.opensource.xxx
7. replace __gitignore -> .gitignore