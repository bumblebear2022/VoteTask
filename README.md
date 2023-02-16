# VoteTaskGroup2
 ```sh
Lappo Statkevich Truskouski
  ```
### Project Link: [current version](http://128.199.46.151/VoteTask-2.5.0/)
### Project Link: [beta hibernate version](http://128.199.46.151/VoteTask-2.6.H/)



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
To create, update or delete genre use respective POST queries:
 ```sh
 (POST)http://host:port/WarFileName/genres?create=(genre_name)
 (POST)http://host:port/WarFileName/genres?update=(genre_id)&name=(new_genre_name)
 (POST)http://host:port/WarFileName/genres?delete=(genre_id)
  ```
### /performers
To get a list of performers:
 ```sh
 (GET)http://host:port/WarFileName/performers
  ```
To create, update or delete performers use respective POST queries:
 ```sh
 (POST)http://host:port/WarFileName/performers?create=(performer_name)
 (POST)http://host:port/WarFileName/performers?update=(performer_id)&name=(new_performer_name)
 (POST)http://host:port/WarFileName/performers?delete=(performer_id)
  ```
### /vote (1 vote for performer, 3-5 votes for genre)
 ```sh
 (POST)http://host:port/WarFileName/
 vote?performer=(performer_id)&genre=(genre_id)&about=(about)&email=(email)
  ```
### /vote_Result(typically redirected to)
 ```sh
 (GET)http://host:port/WarFileName/vote_result
  ```

## JSON examples of DTOs

### Performer
 ```sh
 {
"nickName": "example"
}
  ```

### Genre
 ```sh
{
"title": "example"
}
  ```


### Vote
 ```sh
{
"voiceForPerformer": 1,
"voicesForGenres": [1,2,3],
"about": "example",
"email": "example"
}
  ```



