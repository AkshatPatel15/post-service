# posts-services

Simple overview of use/purpose.

## Build image
Image is build using the Google jib plugin
```
<plugin>
 <groupId>com.google.cloud.tools</groupId>
 <artifactId>jib-maven-plugin</artifactId>
 <version>3.4.0</version>
 <configuration>
  <to>
   <image>registry.hub.docker.com/sakthiece08/${artifactId}:${version}</image>
  </to>
 </configuration>
</plugin>
```
Build and push the image to a container registry:
```
mvn compile jib:build
```
Optionally, if you have Docker installed, you can build to your local Docker installation, so you can inspect or run the image as any other local container:
```
mvn compile jib:dockerBuild
```
## Description

An in-depth paragraph about your project and overview of use.

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing program.
* ex. Windows 10

### Installing

* How/where to download your program
* Any modifications needed to be made to files/folders

### Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## Help

In spring data jpa the save method is used for both persist and merge activity, when the save method is called with an entity without an Id the entity manager will call the persist operation, otherwise (with an existent id) the merge operation will be called.

**PostEntity**

 @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
 private List<CommentEntity> comments;

FetchType.EAGER is being used to load both Parent and Child entities at the same time. The below JOIN query would be used only fro "findById" method and not for custom find methods (findByName)

findById
 *From the logs:*
  Hibernate: select p1_0.id,p1_0.content,p1_0.created_date_time,p1_0.name,c1_0.post_id,c1_0.id,c1_0.created_date_time,c1_0.review from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?

findByName

Hibernate: select p1_0.id,p1_0.content,p1_0.created_date_time,p1_0.name from post p1_0 where p1_0.id=?
Hibernate: select c1_0.post_id,c1_0.id,c1_0.created_date_time,c1_0.review,c1_0.status from comment c1_0 where c1_0.post_id=?


FetchType.LAZY

Child table query will be executed only while accessing child object from Parent object, applicable for all find methods.

@GetMapping("/postWithCommentsCount")

DTO projection is useful when you want to retrieve specific fields or calculated values from your entities without fetching the entire entity. It helps optimize database queries and reduce the overhead of transferring unnecessary data.


## Version History
