# VoteTaskGroup2
 ```sh
Lappo Statkevich Truskouski
  ```

### Project Link: [spring RESTful version](http://128.199.46.151/VoteTask-3.0.R/)

## Endpoints
* /genres
* /performers
* /vote
* /vote-result

## Examples

### /genres
To get a list of genres:
 ```sh
 (GET)http://host:port/WarFileName/genres
  ```
To create: 
 ```sh
 Send POST request on http://host:port/WarFileName/genres
 
 And pass following in the body of request in JSON format:
 {
"title": "example"
}
  ```
To update:
 ```sh
 Send POST request on http://host:port/WarFileName/genres/{id}/version/{version}
 
 And pass following in the body of request in JSON format:
 {
"title": "example"
}
  ```

To delete:
 ```sh
 Send DELETE request on http://host:port/WarFileName/genres/{id}
  ```

### /performers
To get a list of performers:
 ```sh
 (GET)http://host:port/WarFileName/performers
  ```
To create:
 ```sh
 Send POST request on http://host:port/WarFileName/performers
 
 And pass following in the body of request in JSON format:
 {
"nickName": "example"
}
  ```
To update:
 ```sh
 Send POST request on http://host:port/WarFileName/performers/{id}/version/{version}
 
 And pass following in the body of request in JSON format:
 {
"nickName": "example"
}
  ```

To delete:
 ```sh
 Send DELETE request on http://host:port/WarFileName/performers/{id}
  ```

### /vote (1 vote for performer, 3-5 votes for genre)
 ```sh
Send POST request on http://host:port/WarFileName/vote
 
 And pass following in the body of request in JSON format:
{
"voiceForPerformer": 1,
"voicesForGenres": [1,2,3],
"about": "example",
"email": "example"
}
  ```

### /vote-result
 ```sh
 Send GET request http://host:port/WarFileName/vote-result
  ```




