# posts-services

Simple overview of use/purpose.

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
**PostEntity**

 @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
 private List<CommentEntity> comments;

FetchType.EAGER is being used to load both Parent and Child entities at the same time.

 *From the logs:*
  Hibernate: select p1_0.id,p1_0.content,p1_0.created_date_time,p1_0.name,c1_0.post_id,c1_0.id,c1_0.created_date_time,c1_0.review from post p1_0 left join comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?



## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History
